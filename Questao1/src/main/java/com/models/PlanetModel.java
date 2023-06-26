package com.models;

public class PlanetModel {
	private int id;
	private String nome;
	private String clima;
	private String terreno;
	private int numAparicao;
	
	public PlanetModel(int newId) {
		id = newId;
		nome = "";
		clima = "";
		terreno = "";
		numAparicao = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int newId) {
		id = newId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String newNome) {
		nome = newNome;
	}

	public String getClima() {
		return clima;
	}
	
	public void setClima(String newClima) {
		clima = newClima;
	}
	
	public String getTerreno() {
		return terreno;
	}
	
	public void setTerreno(String newTerreno) {
		terreno = newTerreno;
	}
	
	public int getNumAparicao() {
		return numAparicao;
	}
	
	public void setNumAparicao(int newNumApa) {
		numAparicao = newNumApa;
	}
}
