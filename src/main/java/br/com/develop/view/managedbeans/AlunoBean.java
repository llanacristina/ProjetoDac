package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.develop.controller.services.EmpresaService;
import br.com.develop.controller.services.OrientadorService;
import br.com.develop.model.entities.Aluno;
import br.com.develop.model.entities.Empresa;
import br.com.develop.model.entities.Orientador;
import br.com.develop.model.utils.CriptografarSenha;

@Named
@SessionScoped
public class AlunoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private OrientadorService orientadorService;
	@Inject
	private EmpresaService empresaService;
	@Inject
	private Aluno aluno;

	private Orientador orientador = new Orientador();
	private Empresa empresa = new Empresa();

	private String nomeUsuario;
	private String senha;
	private Date dataLogin;
	
	private boolean isEmpresa = false;
	

	public String login() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String pswd = CriptografarSenha.encripta(senha);
		this.orientador = orientadorService.loginOrientador(nomeUsuario, pswd);
		this.empresa = empresaService.loginEmpresa(nomeUsuario, pswd);

		if (this.orientador != null && this.orientador.getUsername().equals(nomeUsuario) && this.orientador.getSenha().equals(pswd)) {
			this.setDataLogin(new Date());
			session.setAttribute("usuario", this.orientador);
			return "/listagemLayout?faces-redirect=true";
		} else if (this.empresa != null && this.empresa.getUsername().equals(nomeUsuario) && this.empresa.getSenha().equals(pswd)) {
			this.setDataLogin(new Date());
			this.isEmpresa = true;
			session.setAttribute("usuario", this.empresa);
			return "/listagemLayout?faces-redirect=true";
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		return null;

	}

	public String sair() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.orientador = new Orientador();
		this.empresa = new Empresa();
		return "/login?faces-redirect=true";
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
	
	public boolean getIsEmpresa() {
		return this.isEmpresa;
	}

	
}
