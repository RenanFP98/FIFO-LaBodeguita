package com.fifo.app_bodeguita.service;

import com.fifo.app_bodeguita.dto.UsuarioDTO;
import com.fifo.app_bodeguita.model.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    
    private final String COLLECTION = "usuarios";

    // 1. Crear o Guardar un usuario nuevo
    public String guardarUsuario(Usuario usuario) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> docRef = db.collection(COLLECTION).add(usuario);
        return docRef.get().getId();
    }

    // 2. Listar todos los usuarios como MODELO (Para uso interno del Backend, ej: Login)
    public List<Usuario> listarUsuarios() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        return documents.stream()
                .map(doc -> doc.toObject(Usuario.class))
                .collect(Collectors.toList());
    }

    // 3. Listar todos los usuarios como DTO (Para enviar a Angular de forma segura)
    public List<UsuarioDTO> listarUsuariosDTO() throws Exception {
        List<Usuario> listaModelos = this.listarUsuarios();

        return listaModelos.stream().map(u -> {
            return new UsuarioDTO(u.getId(), u.getNombre(), u.getUsername(), u.getRol(), u.isActivo());
        }).collect(Collectors.toList());
    }

    // 4. Actualizar el estado (Activo/Inactivo) para bloqueo instantáneo
    public String actualizarEstado(String id, boolean nuevoEstado) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION).document(id).update("activo", nuevoEstado);
        return "Estado actualizado correctamente";
    }

    // 5. EDITAR USUARIO: Permite cambiar Nombre, Username, Rol y CONTRASEÑA
    public String editarUsuario(String id, Usuario nuevosDatos) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        
        db.collection(COLLECTION).document(id).update(
            "nombre", nuevosDatos.getNombre(),
            "username", nuevosDatos.getUsername(),
            "rol", nuevosDatos.getRol(),
            "password", nuevosDatos.getPassword()
        );
        
        return "Datos de " + nuevosDatos.getUsername() + " actualizados con éxito";
    }
}