package com.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.controllers.PlanetController;

@Named
@RequestScoped
public class RemovePlanetHandle {

	private PlanetController controller = new PlanetController();

	@PostConstruct
	public void init() throws Exception {
		System.out.println("RemovePlanetHandle executando");
		setResponseMessage("");
	}

	private String responseMessage = "";
	private String id;
	private String nome;
	private boolean renderResult = false;

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

	public boolean isRenderResult() {
		return renderResult;
	}

	public void setRenderResult(boolean renderResult) {
		this.renderResult = renderResult;
	}
	
	public void removePlanet() throws Throwable {
		setRenderResult(false);
		boolean remove = false;

		if (!remove && !id.equals("")) {
			int idInt = Integer.parseInt(id);
			remove = controller.removePlanetById(idInt);
		}
		else if (!remove && !nome.isEmpty()) {
			remove = controller.removePlanetByName(nome);
		}

		if (!remove) {
			System.out.println("Planeta não encontrado ou já removido");
			setResponseMessage("Planeta não encontrado ou já removido");
		} else {
			System.out.println("Planeta excluido com sucesso!");
			setResponseMessage("Planeta excluido com sucesso!");
		}

		setRenderResult(true);
		PrimeFaces.current().ajax().update("response");
	}

}
