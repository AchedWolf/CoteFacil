package com.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.controllers.PlanetController;
import com.models.PlanetModel;

@Named
@RequestScoped
public class ListPlanetHandle {
	private PlanetController controller = new PlanetController();

	@PostConstruct
	public void init() throws Exception {
		System.out.println("ListPlanetHandle executando");
		setResponseMessage("");
	}

	private String responseMessage = "";
	
	public String getResponseMessage() {
		return responseMessage;
	}

	private void setResponseMessage(String newResp) {
		responseMessage = newResp;
	}
	
	public List<PlanetModel> getPlanetList() throws Throwable {
		List<PlanetModel> planetList = controller.getPlanetList();
		int size = planetList.size();
		
		if(size == 0) {
			setResponseMessage("Não há planetas cadastrados.");
		}
		else {
			setResponseMessage("Há " + size + " planeta(s) cadastrado(s).");
		}
		
		return planetList;
	}
}
