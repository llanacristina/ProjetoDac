package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.AvaliacaoDaEmpresa;

public class AvaliacaoDaEmpresaDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public AvaliacaoDaEmpresaDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void addAvaliacaoDaEmpresa(AvaliacaoDaEmpresa avaliacao) {
		//manager.persist(editora);
		this.guardar(avaliacao);
	}

	public AvaliacaoDaEmpresa porId(Long id) {
		return manager.find(AvaliacaoDaEmpresa.class, id);
	}

	public AvaliacaoDaEmpresa guardar(AvaliacaoDaEmpresa avaliacao) {
		return this.manager.merge(avaliacao);
	}

	public void remover(AvaliacaoDaEmpresa avaliacao) {
		this.manager.remove(avaliacao);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido com sucesso!", null));
	}

	public List<AvaliacaoDaEmpresa> todas() {
		TypedQuery<AvaliacaoDaEmpresa> query = manager.createQuery("FROM AvaliacaoDaEmpresa", AvaliacaoDaEmpresa.class);
		return query.getResultList();
	}
}