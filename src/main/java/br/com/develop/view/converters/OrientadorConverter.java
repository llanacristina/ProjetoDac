package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.OrientadorDAO;
import br.com.develop.model.entities.Orientador;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = Orientador.class, value="orientadorConverter")
public class OrientadorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Orientador retorno = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				OrientadorDAO orientador = new OrientadorDAO(manager);
				retorno = orientador.buscarPorId(new Long(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Orientador) value).getId().toString();
		}
		return null;
	}
}