package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.controller.services.AlunoService;
import br.com.develop.model.daos.AlunoDAO;
import br.com.develop.model.entities.Aluno;

@Named
@ViewScoped
public class ConsultaAlunosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Aluno> alunos;
    private Aluno alunoSelecionado;
    private String searchText;

    @Inject
    private AlunoService alunoService;

    @Inject
    private EntityManager manager;

    @Inject
    private AlunoDAO alunoDAO;

    @PostConstruct
    public void init() {
        this.alunos = alunoDAO.todas();
		
		if (!alunos.isEmpty()) {
            this.alunoSelecionado = alunos.get(0);
        }
    }

    public void remover(Aluno aluno) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            alunoService.remover(aluno);
            this.alunos.remove(aluno); 
            context.addMessage(null, new FacesMessage("Aluno removido com sucesso!"));
        } catch (BusinessException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }


    public void iniciarEdicao(Aluno aluno) {
        this.alunoSelecionado = aluno;
    }

    public void salvarEdicao() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            alunoService.salvar(alunoSelecionado);
            this.alunoSelecionado = null; 
            this.alunos = alunoDAO.todas();
            context.addMessage(null, new FacesMessage("Aluno editado com sucesso!"));
        } catch (BusinessException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
    public List<Aluno> getAlunos() {
		return alunos;
	}

    public Aluno getAlunosSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunosSelecionado(Aluno aluno) {
        this.alunoSelecionado = aluno;
    }
	public void setAlunoSelecionado(Aluno aluno) {
		this.alunoSelecionado = aluno;
	}
	private List<Aluno> alunosFiltrados;
	   public List<Aluno> getAlunosFiltrados() {
	       if (searchText == null || searchText.trim().isEmpty()) {
	           return alunos;
	       }
	       String searchTermLowerCase = searchText.toLowerCase().trim();
	       return alunos.stream()
	           .filter(aluno ->
	               aluno.getName().toLowerCase().contains(searchTermLowerCase) ||
	               aluno.getName().toLowerCase().startsWith(searchTermLowerCase))
	           .collect(Collectors.toList());
	   }
		public void setAlunosFiltrados(List<Aluno> alunosFiltrados) {
			this.alunosFiltrados = alunosFiltrados;
		}
		public String getSearchText() {
		        return searchText;
		 }
		public void setSearchText(String searchText) {
		        this.searchText = searchText;
		}
			
}