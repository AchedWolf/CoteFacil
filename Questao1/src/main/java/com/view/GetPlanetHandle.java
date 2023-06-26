package com.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.controllers.PlanetController;
import com.models.PlanetModel;

@Named
@RequestScoped
public class GetPlanetHandle {
	private PlanetController controller = new PlanetController();

	@PostConstruct
	public void init() throws Throwable {
		System.out.println("GetPlanetHandle executando");
		setResponseMessage("");
	}

	private String responseMessage = "";
	private String id;
	private String nome;
	private PlanetModel planetSelected = new PlanetModel(0);
	private boolean renderResult = false;
	private boolean renderPlanet = false;

	public String getResponseMessage() {
		return responseMessage;
	}

	private void setResponseMessage(String newResp) {
		responseMessage = newResp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PlanetModel getPlanetSelected() {
		return planetSelected;
	}

	public void setPlanetSelected(PlanetModel planetSelected) {
		this.planetSelected = planetSelected;
	}
	
	public boolean isRenderResult() {
		return renderResult;
	}

	public void setRenderResult(boolean renderResult) {
		this.renderResult = renderResult;
	}
	
	public boolean isRenderPlanet() {
		return renderPlanet;
	}

	public void setRenderPlanet(boolean renderPlanet) {
		this.renderPlanet = renderPlanet;
	}
	
	public void getPlanet() throws Throwable {
		setRenderResult(false);
		setRenderPlanet(false);
		
		PlanetModel planet = new PlanetModel(0);
		
		if(planet.getId() == 0 && !id.equals("")) 
		{
			int idInt = Integer.parseInt(id);
			planet = controller.getPlanetById(idInt); 
		}
		else if(planet.getId() == 0 && !nome.isEmpty())
		{
			planet = controller.getPlanetByName(nome);
		}
		
		if(planet.getId() == 0) {
			System.out.println("Planeta não encontrado");	
			setResponseMessage("Planeta não encontrado");
			setRenderPlanet(false);
			setPlanetSelected(new PlanetModel(0));
		}
		else {
			System.out.println("Planeta " + planet.getNome() + " encontrado com sucesso!");	
			setResponseMessage("Planeta " + planet.getNome() + " encontrado com sucesso!");
			setRenderPlanet(true);
			setPlanetSelected(planet);
		}
		
		setRenderResult(true);
		PrimeFaces.current().ajax().update("response");
	}

	


}
