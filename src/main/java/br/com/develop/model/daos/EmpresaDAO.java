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

import br.com.develop.model.entities.Empresa;

public class EmpresaDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public EmpresaDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void addEmpresa(Empresa empresa) {
		//manager.persist(editora);
		this.guardar(empresa);
	}

	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public Empresa guardar(Empresa empresa) {
		return this.manager.merge(empresa);
	}

	public void remover(Empresa empresa) {
		this.manager.remove(empresa);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido com sucesso!", null));
	}

	public List<Empresa> todas() {
		TypedQuery<Empresa> query = manager.createQuery("FROM Empresa", Empresa.class);
		return query.getResultList();
	}
	
	public Empresa identificarEmpresa(String username, String password) {
		Query query = manager
				.createQuery("SELECT u FROM Empresa u WHERE u.username = :username AND u.senha = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		Empresa result = null;
		try {
			result = (Empresa) query.getSingleResult();
		} catch (NoResultException e) {
			// no result found
		}
		return result;
	}
}