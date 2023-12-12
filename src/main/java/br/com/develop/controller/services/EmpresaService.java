package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.EmpresaDAO;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.utils.Transactional;

public class EmpresaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaDAO empresaDAO;
	
	@Transactional
    public Empresa loginEmpresa(String username, String password){
        return this.empresaDAO.identificarEmpresa(username, password);
    }

	@Transactional
	public void salvar(Empresa empresa) throws BusinessException {
		if (empresa == null) {
			throw new BusinessException("Não foi possível salvar a Empresa.");
		}
		this.empresaDAO.guardar(empresa);
	}

	@Transactional
	public void remover(Empresa empresa) throws BusinessException {
		if (empresa == null) {
			throw new BusinessException("Não é possível remover a Empresa!");
		}
		this.empresaDAO.remover(empresa);
	}

}