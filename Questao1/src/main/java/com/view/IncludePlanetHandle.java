package com.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.controllers.PlanetController;

@Named
@RequestScoped
public class IncludePlanetHandle {
	
	private PlanetController controller = new PlanetController();
	
	@PostConstruct
	public void init() throws Exception {
		System.out.println("IncludePlanetHandle executando");
		setResponseMessage("");
	}
	
	private String responseMessage = "";
	private String nome;
	private String clima;
	private String terreno;
	private boolean renderResult = false;
	
	public String getResponseMessage() {
		return responseMessage;
	}

	private void setResponseMessage(String newResp) {
		responseMessage = newResp;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public boolean isRenderResult() {
		return renderResult;
	}

	public void setRenderResult(boolean renderResult) {
		this.renderResult = renderResult;
	}
	
	public void addPlanet() throws Throwable {
		setRenderResult(false);
		boolean addResult = controller.addPlanet(nome, clima, terreno);
		
		if(addResult) {
			System.out.println("Planeta registrado com sucesso");
			setResponseMessage("Planeta registrado com sucesso");			
		}
		else {
			System.out.println("Erro ao registrar um planeta");
			setResponseMessage("Erro ao registrar um planeta");
		}
		
		setRenderResult(true);
		PrimeFaces.current().ajax().update("response");
	}

	
}
