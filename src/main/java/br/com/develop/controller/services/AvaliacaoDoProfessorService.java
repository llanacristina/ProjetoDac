package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.AvaliacaoDoProfessorDAO;
import br.com.develop.model.entities.AvaliacaoDoProfessor;
import br.com.develop.model.utils.Transactional;

public class AvaliacaoDoProfessorService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoDoProfessorDAO avaliacaoDoProfessorDAO;

	@Transactional
	public void salvar(AvaliacaoDoProfessor avaliacao) throws BusinessException {
		if (avaliacao == null) {
			throw new BusinessException("Não foi possível salvar a AvaliacaoDoProfessor.");
		}
		this.avaliacaoDoProfessorDAO.guardar(avaliacao);
	}

	@Transactional
	public void remover(AvaliacaoDoProfessor avaliacao) throws BusinessException {
		if (avaliacao == null) {
			throw new BusinessException("Não é possível remover a AvaliacaoDoProfessor!");
		}
		this.avaliacaoDoProfessorDAO.remover(avaliacao);
	}

}