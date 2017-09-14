package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phoneNoValidator")
public class PhoneNoValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		Long phNo = (Long) arg2;
		if (!phNo.toString().matches("^[1][3578][0-9]{9}$")) {
			FacesMessage message = new FacesMessage();
			message.setDetail("��������ȷ���й��ֻ���");
			message.setSummary("��������ȷ���й��ֻ���");
			throw new ValidatorException(message);
		}
		
	}

}
