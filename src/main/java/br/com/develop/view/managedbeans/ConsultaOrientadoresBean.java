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
import javax.persistence.EntityManager;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.controller.services.OrientadorService;
import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.entities.Orientador;

@Named
@ViewScoped
public class ConsultaOrientadoresBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrientadorService orientadorService;
	

	  @Inject
	    private EntityManager manager;

	@Inject
	private OrientadorDAO orientadorDAO;
	
	private Orientador orientadorSelecionado;

	@Inject
	private EmpresaDAO empresaDAO;

	private List<Orientador> orientadores = new ArrayList<>();
	private List<Empresa> empresas = new ArrayList<>();
	private Orientador OrientadorSelecionado;

	@PostConstruct
	public void init() {
		this.empresas = empresaDAO.todas();
		this.orientadores = orientadorDAO.todos();
		
		if (!orientadores.isEmpty()) {
            orientadorSelecionado = orientadores.get(0);
        }
	}


	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			orientadorService.salvar(OrientadorSelecionado);
			this.OrientadorSelecionado = new Orientador();
			context.addMessage(null, new FacesMessage("Orientador salvo com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void excluir(Orientador orientador) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    try {
	        orientador = manager.merge(orientador); 
	        this.orientadorService.excluir(orientador);
	        //this.consultar();
	        context.addMessage(null, new FacesMessage("Orientador excluído com sucesso!"));
	    } catch (BusinessException e) {
	        FacesMessage mensagem = new FacesMessage(e.getMessage());
	        mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	        context.addMessage(null, mensagem);
	    }
	}
	
	 public void iniciarEdicao(Orientador orientador) {
	        this.OrientadorSelecionado = orientador;
	    }

	    public void salvarEdicao() {
	        FacesContext context = FacesContext.getCurrentInstance();
	        try {
	            orientadorService.salvar(OrientadorSelecionado);
	            this.OrientadorSelecionado = null; 
	            context.addMessage(null, new FacesMessage("Orientador editado com sucesso!"));
	        } catch (BusinessException e) {
	            FacesMessage mensagem = new FacesMessage(e.getMessage());
	            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	            context.addMessage(null, mensagem);
	        }
	    }

	public List<Orientador> getOrientadores() {
		return orientadores;
	}

	public Orientador getorientadorelecionado() {
		return OrientadorSelecionado;
	}

	public List<Empresa> getEmpresa() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresa) {
		this.empresas = empresa;
	}
	
	public void setOrientadores(List<Orientador> orientadores) {
		this.orientadores = orientadores;
	}
	
	public Orientador getOrientadorSelecionado() {
		return this.orientadorSelecionado;
	}
	
	public void setOrientadorSelecionado(Orientador orientador) {
		this.orientadorSelecionado = orientador;
	}
}
