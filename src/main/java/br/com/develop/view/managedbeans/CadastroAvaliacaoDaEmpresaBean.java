package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.AvaliacaoDaEmpresaService;
import br.com.develop.model.daos.AvaliacaoDaEmpresaDAO;
import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.entities.AvaliacaoDaEmpresa;
import br.com.develop.model.entities.Empresa;

@Named
@ViewScoped
public class CadastroAvaliacaoDaEmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoDaEmpresaService avaliacaoDaEmpresaService;

	@Inject
	private AvaliacaoDaEmpresaDAO avaliacaoDaEmpresaDAO;
	@Inject
	private EmpresaDAO empresaDAO;

	private AvaliacaoDaEmpresa avaliacaoDaEmpresa = new AvaliacaoDaEmpresa();
	private List<AvaliacaoDaEmpresa> avaliacoesDaEmpresa;
	private List<Empresa> empresas;
	
	private Aluno alunoSelecionado;
	private Empresa empresaSelecionado;


	public void prepararCadastro() {
		this.empresas = this.empresaDAO.todas();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.avaliacaoDaEmpresa.setAluno(this.alunoSelecionado);
			this.avaliacaoDaEmpresa.setEmpresa(this.empresaSelecionado);
			this.avaliacaoDaEmpresaService.salvar(this.avaliacaoDaEmpresa);
			this.avaliacaoDaEmpresa = new AvaliacaoDaEmpresa();
			context.addMessage(null, new FacesMessage("Avaliação criada com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void listarAvaliacaoProfessores() {
		 this.avaliacoesDaEmpresa = avaliacaoDaEmpresaDAO.todas();
	}

	public AvaliacaoDaEmpresa getAvaliacaoDaEmpresa() {
		return avaliacaoDaEmpresa;
	}

	public void setAvaliacaoDoProfessor(AvaliacaoDaEmpresa avaliacao) {
		this.avaliacaoDaEmpresa = avaliacao;
	}

	public List<AvaliacaoDaEmpresa> getEmpresas() {
		return avaliacoesDaEmpresa;
	}

	public void setEmpresa(List<AvaliacaoDaEmpresa> avaliacoes) {
		this.avaliacoesDaEmpresa = avaliacoes;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}
	
	public Aluno getAlunoSelecionado() {
		return this.alunoSelecionado;
	}
	
	public void setEmpresaSelecionado(Empresa empresa) {
		this.empresaSelecionado = empresa;
	}
	
	public Empresa getEmpresaSelecionado() {
		return this.empresaSelecionado;
	}
	
}
