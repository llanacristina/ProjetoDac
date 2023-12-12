package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Aluno;

public class AlunoDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public AlunoDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void addAluno(Aluno aluno) {
		this.manager.persist(aluno);
	}

	public Aluno porId(Long id) {
		return manager.find(Aluno.class, id);
	}

	public Aluno guardar(Aluno aluno) {
		return this.manager.merge(aluno);
	}

	public void remover(Aluno aluno) {
		this.manager.remove(aluno);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido com sucesso!", null));
	}

	public List<Aluno> todas() {
		TypedQuery<Aluno> query = manager.createQuery("FROM Aluno", Aluno.class);
		return query.getResultList();
	}

	public Aluno identificarAluno(String username, String password) {
		Query query = manager
				.createQuery("SELECT u FROM Aluno u WHERE u.username = :username AND u.senha = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		Aluno result = null;
		try {
			result = (Aluno) query.getSingleResult();
		} catch (NoResultException e) {
			// no result found
		}
		return result;
	}

}