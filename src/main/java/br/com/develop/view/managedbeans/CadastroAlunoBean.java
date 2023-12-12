package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.AlunoService;
import br.com.develop.model.daos.AlunoDAO;
import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.entities.Orientador;

@Named
@ViewScoped
public class CadastroAlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;

	@Inject
	private AlunoDAO alunoDAO;
	@Inject
	private OrientadorDAO orientadorDAO;

	private Aluno aluno = new Aluno();
	private List<Aluno> alunos;
	private List<Orientador> orientador;
	private Empresa empresaSelecionada;
	private Orientador orientadorSelecionado;

	public void prepararCadastro() {
		this.orientador = this.orientadorDAO.todos();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.aluno.setOrientador(orientadorSelecionado);
			this.aluno.setEmpresa(empresaSelecionada);
			this.alunoService.salvar(this.aluno);
			this.aluno= new Aluno();
			context.addMessage(null, new FacesMessage("Aluno criado com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void listarAlunos() {
		 this.alunos = alunoDAO.todas();
	} 

	public Aluno getAluno() {
		return aluno;
	}

	public void setALuno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Orientador> getOrientadores() {
		return orientador;
	}
	
	public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }
    
    public Orientador getOrientadorSelecionado() {
		return this.orientadorSelecionado;
	}
	
	public void setOrientadorSelecionado(Orientador orientador) {
		this.orientadorSelecionado = orientador;
	}
}
