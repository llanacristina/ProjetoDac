package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.develop.model.entities.Status;

@FacesConverter(forClass = Status.class, value="statusConverter")
public class StatusConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return Status.valueOf(value); // Converte a String para o enum Status
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Status) {
            return ((Status) value).name(); // Converte o enum Status para String
        }
        return null;
    }
}