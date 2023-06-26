package com.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.util.*;

import com.models.PlanetModel;

@Named
@RequestScoped
public class PlanetController {
	
	private H2Controller db = new H2Controller();
	
	@PostConstruct
	public void init() throws Throwable {
		System.out.println("PlanetController executando");
		System.out.println("Planetas adicionados");
	}
		
	private SwapiController swapi = new SwapiController();

	public boolean addPlanet(String nome, String clima, String terreno) throws Throwable {
		int id = 1;
		
		List<PlanetModel> planetList = getPlanetList(); 
		if(planetList.size() > 0)
			id = planetList.get(planetList.size() - 1).getId() + 1;
		
		PlanetModel planeta = new PlanetModel(id);
		planeta.setNome(nome);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);
		planeta.setNumAparicao(swapi.getAparicoes(nome));
		
		return db.insert(planeta);	
	}

	public List<PlanetModel> getPlanetList() throws Throwable {
		return db.getAll();
	}
	
	public PlanetModel getPlanetByName(String nome) throws Throwable {
		List<PlanetModel> planetList = getPlanetList();
		
		for(PlanetModel planet : planetList) {
			if(planet.getNome().equals(nome))
				return planet;
		}
		
		return new PlanetModel(0);
	}
	
	public PlanetModel getPlanetById(int id) throws Throwable {
		List<PlanetModel> planetList = getPlanetList();
		
		for(PlanetModel planet : planetList) {
			if(planet.getId() == id)
				return planet;
		}
		
		return new PlanetModel(0);
	}
	
	public boolean removePlanetByName(String nome) throws Throwable {
		return db.removeByNome(nome);
	}
	
	public boolean removePlanetById(int id) throws Throwable {
		return db.removetById(id);
	}
	
}
