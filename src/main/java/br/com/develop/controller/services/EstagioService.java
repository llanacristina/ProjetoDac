package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.EstagioDAO;
import br.com.develop.model.entities.Estagio;
import br.com.develop.model.utils.Transactional;

public class EstagioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EstagioDAO estagioDAO;

	public Estagio search(Long id) {
		return this.estagioDAO.porId(id);
	}

	@Transactional
	public void salvar(Estagio estagio) throws BusinessException {
		if (estagio == null) {
			throw new BusinessException("Não foi possível salvar o Estagio.");
		}
		this.estagioDAO.guardar(estagio);
	}

	@Transactional
	public void excluir(Estagio estagio) throws BusinessException {
		estagio = this.estagioDAO.porId(estagio.getId());
		if (estagio == null) {
			throw new BusinessException("Não é possível excluir o Orientador!");
		}
		this.estagioDAO.remover(estagio);
	}
}