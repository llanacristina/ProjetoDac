package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.AvaliacaoDoProfessor;

public class AvaliacaoDoProfessorDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public AvaliacaoDoProfessorDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void addAvaliacaoDoProfessor(AvaliacaoDoProfessor avaliacao) {
		//manager.persist(editora);
		this.guardar(avaliacao);
	}

	public AvaliacaoDoProfessor porId(Long id) {
		return manager.find(AvaliacaoDoProfessor.class, id);
	}

	public AvaliacaoDoProfessor guardar(AvaliacaoDoProfessor avaliacao) {
		return this.manager.merge(avaliacao);
	}

	public void remover(AvaliacaoDoProfessor avaliacao) {
		this.manager.remove(avaliacao);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido com sucesso!", null));
	}

	public List<AvaliacaoDoProfessor> todas() {
		TypedQuery<AvaliacaoDoProfessor> query = manager.createQuery("FROM AvaliacaoDoProfessor", AvaliacaoDoProfessor.class);
		return query.getResultList();
	}
}