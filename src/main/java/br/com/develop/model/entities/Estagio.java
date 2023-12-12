package br.com.develop.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Estagio")
public class Estagio implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private int cargaHoraria;
  private String inicio;
  private String fim;

  @Enumerated(EnumType.STRING)
  private Status status;

  @OneToOne
  private Aluno aluno;

  @OneToOne
  private Orientador orientador;

  @OneToOne
  private Empresa empresa;

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

  public Empresa getEmpresa() {
    return this.empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }

  public int getCargaHoraria() {
    return this.cargaHoraria;
  }

  public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
  }

  public String getInicio() {
    return this.inicio;
  }

  public void setInicio(String inicio) {
    this.inicio = inicio;
  }

  public String getFim() {
    return this.fim;
  }

  public void setFim(String fim) {
    this.fim = fim;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getNome() {
	 return this.nome;
  }

  public void setNome(String nome) {
	 this.nome = nome;
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
		Estagio other = (Estagio) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}