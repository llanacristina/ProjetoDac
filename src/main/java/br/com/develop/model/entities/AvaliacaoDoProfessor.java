package br.com.develop.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AvaliacaoDoProfessor")
public class AvaliacaoDoProfessor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @OneToOne
    private Aluno aluno;
    @OneToOne
    private Orientador orientador;
    
    private String assiduidade;
    private String disciplina;
    private String sociabilidade;
    private String responsabilidade;
    private String iniciativa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Aluno getAluno() {
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Orientador getOrientador() {
        return this.orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }
    	
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AvaliacaoDoProfessor other = (AvaliacaoDoProfessor) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getAssiduidade() {
		return assiduidade;
	}

	public void setAssiduidade(String assiduidade) {
		this.assiduidade = assiduidade;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSociabilidade() {
		return sociabilidade;
	}

	public void setSociabilidade(String sociabilidade) {
		this.sociabilidade = sociabilidade;
	}

	public String getResponsabilidade() {
		return responsabilidade;
	}

	public void setResponsabilidade(String responsabilidade) {
		this.responsabilidade = responsabilidade;
	}

	public String getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(String iniciativa) {
		this.iniciativa = iniciativa;
	}
}