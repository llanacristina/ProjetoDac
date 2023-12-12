package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.EmpresaService;
import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.entities.Orientador;

@Named
@ViewScoped
public class CadastroEmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaService empresaService;

	@Inject
	private EmpresaDAO empresaDAO;
	@Inject
	private OrientadorDAO orientadorDAO;

	private Empresa empresa = new Empresa();
	private List<Empresa> empresas;
	private List<Orientador> orientador;


	public void prepararCadastro() {
		this.orientador = this.orientadorDAO.todos();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.empresaService.salvar(this.empresa);
			this.empresa= new Empresa();
			context.addMessage(null, new FacesMessage("Empresa criada com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void listarEmpresas() {
		 this.empresas = empresaDAO.todas();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresa(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Orientador> getOrientadores() {
		return orientador;
	}

	public void setOrientadores(List<Orientador> orientadores) {
		this.orientador = orientadores;
	}
}
