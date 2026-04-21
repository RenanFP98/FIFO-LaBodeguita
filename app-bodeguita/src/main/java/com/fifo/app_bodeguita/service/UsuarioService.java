package com.fifo.app_bodeguita.service;

import com.fifo.app_bodeguita.model.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public String guardarUsuario(Usuario usuario) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        // Guardamos en la colección "usuarios"
        ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection("usuarios").add(usuario);
        return collectionsApiFuture.get().getId(); // Retorna el ID generado por Firebase
    }
}