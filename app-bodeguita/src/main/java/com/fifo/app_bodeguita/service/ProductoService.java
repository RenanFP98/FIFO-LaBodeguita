package com.fifo.app_bodeguita.service;

import com.fifo.app_bodeguita.model.Producto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    public String guardarProducto(Producto producto) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        // Firebase creará la colección "productos" automáticamente al primer envío
        ApiFuture<DocumentReference> docRef = dbFirestore.collection("productos").add(producto);
        return docRef.get().getId();
    }

public List<Producto> listarPorUsuario(String usuarioId) throws Exception {
    Firestore dbFirestore = FirestoreClient.getFirestore();
    
    // Hacemos la consulta filtrando por el ID del usuario
    ApiFuture<QuerySnapshot> future = dbFirestore.collection("productos")
                                                 .whereEqualTo("usuarioId", usuarioId)
                                                 .get();
    
    // Obtenemos la lista de documentos
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();
    List<Producto> lista = new ArrayList<>();
    
    for (QueryDocumentSnapshot doc : documents) {
        // Convertimos el documento de Firebase directamente a nuestra clase Producto
        Producto p = doc.toObject(Producto.class);
        lista.add(p);
    }
    
    return lista;
}

}