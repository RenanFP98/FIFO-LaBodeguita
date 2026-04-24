package com.fifo.app_bodeguita.service;

import com.fifo.app_bodeguita.dto.ProductoStockDTO;
import com.fifo.app_bodeguita.model.Producto;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final String COLLECTION = "productos";

    // Listar todo el inventario consolidado usando DTO
    public List<ProductoStockDTO> listarStockDTO() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documents = db.collection(COLLECTION).get().get().getDocuments();

        return documents.stream().map(doc -> {
            Producto p = doc.toObject(Producto.class);
            return new ProductoStockDTO(p.getCodigo(), p.getNombre(), p.getCantidad());
        }).collect(Collectors.toList());
    }

    // Buscar un producto específico (uso interno)
    public Producto buscarPorCodigo(String codigo) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        var doc = db.collection(COLLECTION).document(codigo).get().get();
        if (doc.exists()) {
            return doc.toObject(Producto.class);
        }
        return null;
    }
}