package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.AvaliacaoDoProfessorService;
import br.com.develop.model.daos.AvaliacaoDoProfessorDAO;
import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.entities.AvaliacaoDoProfessor;
import br.com.develop.model.entities.Orientador;

@Named
@ViewScoped
public class CadastroAvaliacaoProfessorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoDoProfessorService avaliacaoDoProfessorService;

	@Inject
	private AvaliacaoDoProfessorDAO avaliacaoDoProfessorDAO;
	@Inject
	private OrientadorDAO orientadorDAO;

	private AvaliacaoDoProfessor avaliacaoDoProfessor = new AvaliacaoDoProfessor();
	private List<AvaliacaoDoProfessor> avaliacoesDoProfessor;
	private List<Orientador> orientador;
	
	private Aluno alunoSelecionado;
	private Orientador orientadorSelecionado;


	public void prepararCadastro() {
		this.orientador = this.orientadorDAO.todos();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.avaliacaoDoProfessor.setAluno(this.alunoSelecionado);
			this.avaliacaoDoProfessor.setOrientador(this.orientadorSelecionado);
			this.avaliacaoDoProfessorService.salvar(this.avaliacaoDoProfessor);
			this.avaliacaoDoProfessor = new AvaliacaoDoProfessor();
			context.addMessage(null, new FacesMessage("Avaliação criada com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void listarAvaliacaoProfessores() {
		 this.avaliacoesDoProfessor = avaliacaoDoProfessorDAO.todas();
	}

	public AvaliacaoDoProfessor getAvaliacaoDoProfessor() {
		return avaliacaoDoProfessor;
	}

	public void setAvaliacaoDoProfessor(AvaliacaoDoProfessor avaliacao) {
		this.avaliacaoDoProfessor = avaliacao;
	}

	public List<AvaliacaoDoProfessor> getEmpresas() {
		return avaliacoesDoProfessor;
	}

	public void setEmpresa(List<AvaliacaoDoProfessor> avaliacoes) {
		this.avaliacoesDoProfessor = avaliacoes;
	}

	public List<Orientador> getOrientadores() {
		return orientador;
	}

	public void setOrientadores(List<Orientador> orientadores) {
		this.orientador = orientadores;
	}
	
	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}
	
	public Aluno getAlunoSelecionado() {
		return this.alunoSelecionado;
	}
	
	public void setOrientadorSelecionado(Orientador orientador) {
		this.orientadorSelecionado = orientador;
	}
	
	public Orientador getOrientadorSelecionado() {
		return this.orientadorSelecionado;
	}
	
}
