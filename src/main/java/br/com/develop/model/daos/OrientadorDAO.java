package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Orientador;

public class OrientadorDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public OrientadorDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void addOrientador(Orientador orientador) {
		this.guardar(orientador);
	}

	public Orientador porId(Long id) {
		return this.manager.find(Orientador.class, id);
	}

	public Orientador guardar(Orientador orientador) {
		return manager.merge(orientador);
	}

	public Orientador buscarPorId(Long id) {
		return manager.find(Orientador.class, id);
	}

	public void remover(Orientador orientador) {
		this.manager.remove(orientador);
	}

	public List<Orientador> todos() {
		TypedQuery<Orientador> query = manager.createQuery("FROM Orientador", Orientador.class);
		return query.getResultList();
	}
	
	public Orientador identificarOrientador(String username, String password) {
		Query query = manager
				.createQuery("SELECT u FROM Orientador u WHERE u.username = :username AND u.senha = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		Orientador result = null;
		try {
			result = (Orientador) query.getSingleResult();
		} catch (NoResultException e) {
			// no result found
		}
		return result;
	}
}