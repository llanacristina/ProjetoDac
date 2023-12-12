package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.controller.services.EmpresaService;
import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.entities.Empresa;

@Named
@ViewScoped
public class ConsultaEmpresasBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Empresa> empresas;
    private Empresa empresaSelecionada;

    @Inject
    private EmpresaService empresaService;

    @Inject
    private EntityManager manager;

    @Inject
    private EmpresaDAO empresaDAO;

    @PostConstruct
    public void init() {
        this.empresas = empresaDAO.todas();
		
		if (!empresas.isEmpty()) {
            empresaSelecionada = empresas.get(0);
        }
    }

    public void remover(Empresa empresa) {
        empresa = manager.merge(empresa);
        manager.remove(empresa);
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            empresaService.remover(empresa);
            this.empresas = empresaDAO.todas();
            context.addMessage(null, new FacesMessage("Empresa removida com sucesso!"));
        } catch (BusinessException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void iniciarEdicao(Empresa empresa) {
        this.empresaSelecionada = empresa;
    }

    public void salvarEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            empresaService.salvar(empresaSelecionada);
            this.empresaSelecionada = null; // Limpa a empresa selecionada após a edição
            this.empresas = empresaDAO.todas();
            context.addMessage(null, new FacesMessage("Empresa editada com sucesso!"));
        } catch (BusinessException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }
}
