package br.com.develop.controller.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.develop.view.managedbeans.AlunoBean;

@WebFilter("*.xhtml")
public class AuthorizingFilter implements Filter {

	@Inject
	private AlunoBean login;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		//HttpSession session = request.getSession();

		//usuario = (Usuario) session.getAttribute("usuario");

		if (login.getNomeUsuario() == null && !request.getRequestURI().endsWith("/login.xhtml")
				&& !request.getRequestURI().contains("/javax.faces.resource/")) {
			response.sendRedirect(request.getContextPath() + "/login.xhtml");
		} else {
			chain.doFilter(req, res);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
