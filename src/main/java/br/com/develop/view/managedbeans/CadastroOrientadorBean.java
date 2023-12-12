package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.OrientadorService;
import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.entities.Orientador;

@Named
@ViewScoped
public class CadastroOrientadorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrientadorService orientadorService;

	@Inject
	private OrientadorDAO orientadorDAO;

	private Orientador orientador = new Orientador();
	private List<Orientador> orientadores = new ArrayList<>();
	private List<Empresa> empresas = new ArrayList<>();
	

	@PostConstruct
	public void init() {
		this.orientadores = this.orientadorDAO.todos();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.orientadorService.salvar(orientador);
			this.orientador = new Orientador();
			context.addMessage(null, new FacesMessage("Orientador salvo com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}


	// public List<String> pesquisarTitulos(String titulo) {
	// 	return this.orientadorService.buscarPorTitulo(titulo);
	// }

	public void listarOrientadores() {
		this.orientadores = this.orientadorDAO.todos();
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	public List<Orientador> getOrientadores() {
		return orientadores;
	}

	public void setOrientadores(List<Orientador> orientadores) {
		this.orientadores = orientadores;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
