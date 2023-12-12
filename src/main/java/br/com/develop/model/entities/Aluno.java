package br.com.develop.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String name;

    @NotNull 
	@Size(max = 40) 
	@Column(length = 40, nullable = false)
	private String username;
	@NotNull 
	@Size(max = 40) 
	@Column(length = 40, nullable = false)
	private String senha;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Orientador orientador;

    public Long getId() {
        return matricula;
    }

    public void setId(Long matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmpresa(Empresa emp) {
        this.empresa = emp;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setOrientador(Orientador ori) {
        this.orientador = ori;
    }

    public Orientador getOrientador() {
        return this.orientador;
    }

    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null) {
				return false;
			}
		} else if (!matricula.equals(other.matricula)) {
			return false;
		}
		return true;
	}
}