package br.com.rastreioencomendas.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.rastreioencomendas.util.CpfCnpjUtil;

public class CpfCnpjValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String cpfCnpj = (String)value;

        if (!CpfCnpjUtil.isValid(cpfCnpj)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF/CNPJ inválido.", "CPF/CNPJ inválido."));
        }
	}

}
