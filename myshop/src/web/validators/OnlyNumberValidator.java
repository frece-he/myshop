package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("onlyNumberValidator")
public class OnlyNumberValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String input =  arg2 + "";
		if(!input.matches("[0-9]+")){
			FacesMessage message = new FacesMessage();
			message.setDetail("��������ȷ������");
			message.setSummary("��������ȷ������");
			throw new ValidatorException(message);
		}
	}

}
