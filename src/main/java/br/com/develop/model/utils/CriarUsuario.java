package br.com.develop.model.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.develop.model.entities.Empresa;

public class CriarUsuario {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("atividadePU");
        EntityManager manager = factory.createEntityManager();

//        Aluno aluno = new Aluno();
//        aluno.setUsername("teste");
//		String pswd = CriptografarSenha.encripta("teste123");
//		aluno.setSenha(pswd);
		
		Empresa empresa = new Empresa();
		empresa.setUsername("Loopis");
		empresa.setName("Loopis");
		empresa.setEndereco("IFPB - campus cajazeiras");
		String pswd2 = CriptografarSenha.encripta("teste123");
		empresa.setSenha(pswd2);
		
//		Orientador orientador = new Orientador();
//		orientador.setUsername("Felipe");
//		orientador.setName("Felipe");
//		String pswd3 = CriptografarSenha.encripta("teste123");
//		orientador.setSenha(pswd3);
//		
//		Orientador orientador2 = new Orientador();
//		orientador2.setUsername("João");
//		orientador2.setName("João");
//		String pswd4 = CriptografarSenha.encripta("teste123");
//		orientador2.setSenha(pswd4);

        manager.getTransaction().begin();
//        manager.persist(aluno);
        manager.persist(empresa);
//        manager.persist(orientador);
//        manager.persist(orientador2);
        manager.getTransaction().commit();
        manager.close();

	}

}