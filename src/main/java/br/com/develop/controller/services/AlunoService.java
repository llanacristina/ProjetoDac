package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.AlunoDAO;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.utils.Transactional;

public class AlunoService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoDAO alunoDAO;

	@Transactional
    public Aluno loginAluno(String username, String password){
        return this.alunoDAO.identificarAluno(username, password);
    }
	
	@Transactional
	public void salvar(Aluno aluno) throws BusinessException {
		if (aluno == null) {
			throw new BusinessException("Não foi possível salvar o Aluno.");
		}
		this.alunoDAO.guardar(aluno);
	}
	
	@Transactional
	public void remover(Aluno aluno) throws BusinessException {
		if (aluno == null) {
			throw new BusinessException("Não é possível remover a Empresa!");
		}
		this.alunoDAO.remover(aluno);
	}

}