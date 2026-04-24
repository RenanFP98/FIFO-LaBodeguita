package com.fifo.app_bodeguita.service;

import com.fifo.app_bodeguita.dto.EscaneoDetalleDTO;
import com.fifo.app_bodeguita.model.Escaneo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EscaneoService {

    private final String COLLECTION = "escaneos";

    // Traer el historial convertido a DTO para el Dashboard
    public List<EscaneoDetalleDTO> listarHistorialDTO() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION)
                .orderBy("fecha", Query.Direction.DESCENDING)
                .get();
        
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        
        return documents.stream().map(doc -> {
            Escaneo e = doc.toObject(Escaneo.class);
            return new EscaneoDetalleDTO(e.getNombre(), e.getVencimiento(), e.getCantidad(), e.getUsuario());
        }).collect(Collectors.toList());
    }
}