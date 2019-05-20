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
		String cpfCnpjFormatados = cpfCnpj.trim().replace(".", "").replace("-", "").replace("/","");
		
        if (!CpfCnpjUtil.isValid(cpfCnpj) && cpfCnpjFormatados.length() == 11) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido.", "CPF inválido."));
        }
        
        if (!CpfCnpjUtil.isValid(cpfCnpj) && cpfCnpjFormatados.length() == 14) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CNPJ inválido.", "CNPJ inválido."));
        }
	}

}
