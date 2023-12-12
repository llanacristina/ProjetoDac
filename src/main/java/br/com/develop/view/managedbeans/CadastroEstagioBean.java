package br.com.develop.view.managedbeans;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.develop.controller.services.EstagioService;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.entities.Estagio;
import br.com.develop.model.entities.Orientador;

@ManagedBean
@RequestScoped
public class CadastroEstagioBean  implements Serializable{
	private static final long serialVersionUID = 1L;

    private Estagio estagio = new Estagio();
    
    @Inject
    private EstagioService estagioService;

    @ManagedProperty("#{consultaAlunosBean}")
    private ConsultaAlunosBean consultaAlunosBean;

    @ManagedProperty("#{consultaEmpresasBean}")
    private ConsultaEmpresasBean consultaEmpresasBean;

    @ManagedProperty("#{consultaOrientadoresBean}")
    private ConsultaOrientadoresBean consultaOrientadoresBean;

    public CadastroEstagioBean() {
        this.estagio.setAluno(new Aluno());
        this.estagio.setEmpresa(new Empresa());
        this.estagio.setOrientador(new Orientador());
    }
    private Aluno alunoSelecionado;

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    private Empresa empresaSelecionada;

    public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }

    private Orientador orientadorSelecionado;

    public Orientador getOrientadorSelecionado() {
        return orientadorSelecionado;
    }

    public void setOrientadorSelecionado(Orientador orientadorSelecionado) {
        this.orientadorSelecionado = orientadorSelecionado;
    }

    public void salvarEstagio() {
    	FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.estagioService.salvar(this.estagio);
			this.estagio = new Estagio();
			context.addMessage(null, new FacesMessage("Estagio criado com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
    }


    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public ConsultaAlunosBean getConsultaAlunosBean() {
        return consultaAlunosBean;
    }

    public void setConsultaAlunosBean(ConsultaAlunosBean consultaAlunosBean) {
        this.consultaAlunosBean = consultaAlunosBean;
    }

    public ConsultaEmpresasBean getConsultaEmpresasBean() {
        return consultaEmpresasBean;
    }

    public void setConsultaEmpresasBean(ConsultaEmpresasBean consultaEmpresasBean) {
        this.consultaEmpresasBean = consultaEmpresasBean;
    }

    public ConsultaOrientadoresBean getConsultaOrientadoresBean() {
        return consultaOrientadoresBean;
    }

    public void setConsultaOrientadoresBean(ConsultaOrientadoresBean consultaOrientadoresBean) {
        this.consultaOrientadoresBean = consultaOrientadoresBean;
    }
}
