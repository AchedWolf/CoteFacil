package com.controllers;

import java.io.*;
import java.net.*;

import javax.annotation.PostConstruct;

import org.primefaces.shaded.json.*;

public class SwapiController {

	
	public SwapiController() { }
	
	@PostConstruct
	public void init() {
		System.out.println("SwapiController executando");
	}
	
	private String urlGlobal = "https://swapi.dev/api/";
	
	private JSONObject httpRequest(String request) throws Exception {
		StringBuilder result = new StringBuilder();
		
	    @SuppressWarnings("deprecation")
		URL url = new URL(urlGlobal + request);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    
	    try (BufferedReader reader = 
	    		new BufferedReader(
	    				new InputStreamReader(conn.getInputStream()))) {
	          for (String line; (line = reader.readLine()) != null; ) {
	              result.append(line);
	          }
	      }
		
	    if(result.equals("404 error")) {
	    	return null;
	    }
	    
		JSONObject json = new JSONObject(result.toString());
		return json;
	}
	
	public int getAparicoes(String name) throws Exception {
		name = name.replace(' ', '+');
		JSONObject planet = httpRequest("planets/?search=" + name);
		
		if(planet.equals(null)) {
			return 0;
		}
		
		JSONObject results = (JSONObject) (planet.getJSONArray("results")).get(0);
		JSONArray films = results.getJSONArray("films");
		
		return films.length();
	}
	
	
	
}
