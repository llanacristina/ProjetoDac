package br.com.develop.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AvaliacaoDaEmpresa")
public class AvaliacaoDaEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @OneToOne
    private Aluno aluno;
    @OneToOne
    private Empresa empresa;
    
    private String rendimento;
    private String conhecimentos;
    private String cumprimento;
    private String aprendizagem;
    private String desempenho;

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
    
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
		AvaliacaoDaEmpresa other = (AvaliacaoDaEmpresa) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getRendimento() {
		return rendimento;
	}

	public void setRendimento(String rendimento) {
		this.rendimento = rendimento;
	}

	public String getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(String conhecimentos) {
		this.conhecimentos = conhecimentos;
	}

	public String getCumprimento() {
		return cumprimento;
	}

	public void setCumprimento(String cumprimento) {
		this.cumprimento = cumprimento;
	}

	public String getAprendizagem() {
		return aprendizagem;
	}

	public void setAprendizagem(String aprendizagem) {
		this.aprendizagem = aprendizagem;
	}

	public String getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(String desempenho) {
		this.desempenho = desempenho;
	}
}