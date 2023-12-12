package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.AvaliacaoDaEmpresaDAO;
import br.com.develop.model.entities.AvaliacaoDaEmpresa;
import br.com.develop.model.utils.Transactional;

public class AvaliacaoDaEmpresaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoDaEmpresaDAO avaliacaoDaEmpresaDAO;

	@Transactional
	public void salvar(AvaliacaoDaEmpresa avaliacao) throws BusinessException {
		if (avaliacao == null) {
			throw new BusinessException("Não foi possível salvar a AvaliacaoDaEmpresa.");
		}
		this.avaliacaoDaEmpresaDAO.guardar(avaliacao);
	}

	@Transactional
	public void remover(AvaliacaoDaEmpresa avaliacao) throws BusinessException {
		if (avaliacao == null) {
			throw new BusinessException("Não é possível remover a AvaliacaoDaEmpresa!");
		}
		this.avaliacaoDaEmpresaDAO.remover(avaliacao);
	}

}