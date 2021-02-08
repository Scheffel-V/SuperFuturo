package com.ufrgs.superfuturo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.ufrgs.superfuturo.model.*;

public class StockRemoteRepository {
    private Firestore db;
	
    public StockRemoteRepository(final Firestore db) {
		this.db = db;
	}
    
	private CollectionReference collection() {
        return this.db.collection("produtos");
    }
	
	public List<Product> getProducts() throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> query = collection().get();
		QuerySnapshot querySnapshot = query.get();
		
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		List<Product> stock = new ArrayList<Product>();
		
		for (QueryDocumentSnapshot document : documents) {
			Boolean available = document.getBoolean("available");
			if (available == false) { continue; }
			String inputName = document.getId();
			String name = document.getString("name");
			Long quantity = document.getLong("quantity");
			Double price = document.getDouble("price");
			
			for (int i = 0; i < quantity; i++) {
				Product obj = new Product(name, inputName, price);
				stock.add(obj);
			}
		}
		return stock;
	}
	
	public void setProducts(List<Product> products) {
		CollectionReference colRef = collection();
		Map<String, Long> productsMap = new HashMap<>();
		for (Product product : products) {
			Long qtt = productsMap.getOrDefault(product.getInputName(), (long) 0);
			productsMap.put(product.getInputName(), qtt + 1);
		}
		
		for (String productInputName : productsMap.keySet()) {
			Map<String, Object> data = new HashMap<>();
			String id = productInputName;
			
			Product product = products.stream().filter(n -> { return n.getInputName() == productInputName; }).findFirst().get();
			data.put("name", product.getName());
			data.put("quantity", productsMap.get(productInputName));
			data.put("price", product.getPrice());
			data.put("available", true);
			
			DocumentReference docRef = colRef.document(id);
			docRef.set(data);
		}
	}
	
	public void updateStockQuantity(String productInputName, Long productQuantity) {
		CollectionReference colRef = collection();
		Map<String, Object> data = new HashMap<>();
		DocumentReference docRef = colRef.document(productInputName);
		data.put("quantity", productQuantity);
		docRef.set(data);
	}
}
