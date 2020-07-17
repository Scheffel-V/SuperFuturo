package com.ufrgs.superfuturo.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.ufrgs.superfuturo.model.Product;
import com.ufrgs.superfuturo.model.StockProducts;


public class FrontendClient {
		
	final String removeProductApiUrl = "http://127.0.0.1:3000/Remove";
	final String addProductApiUrl = "http://127.0.0.1:3000/Add";
	final String populateStockApiUrl = "http://127.0.0.1:3000/PopulateStock";
	final String nameParamString = "nome";
	
	public FrontendClient() {
		
	}
	
	public void sendReturnProduct(final Product product) {
		System.out.println("User returned product " + product.getName());
	    
		final HttpClient httpclient = HttpClients.createDefault();
		final HttpPost httppost = new HttpPost(this.removeProductApiUrl);
		final List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair(this.nameParamString, product.getName()));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			System.out.println(new UrlEncodedFormEntity(params, "UTF-8").toString());
			httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to frontend.");
		}
	    
	}
	
	public void sendBuyProduct(final Product product) {
		System.out.println("User bought product " + product.getName());
		
		final HttpClient httpclient = HttpClients.createDefault();
		final HttpPost httppost = new HttpPost(this.addProductApiUrl);
		final List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair(this.nameParamString, product.getName()));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			System.out.println(httppost.toString());
			System.out.println(new UrlEncodedFormEntity(params, "UTF-8").toString());
			httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to frontend.");
		}
	}
	
	public void sendStockProducts(final StockProducts stockProducts) {   
	    try {
			URL url = new URL (this.populateStockApiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			
			Gson g = new Gson();
			System.out.println(stockProducts);
			System.out.println(g.toJson(stockProducts));
			
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = g.toJson(stockProducts).getBytes("utf-8");
			    os.write(input, 0, input.length);
			    os.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to frontend.");
		}
	}
}
