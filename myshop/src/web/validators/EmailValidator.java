package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("emailValidator")
public class EmailValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String email = (String) arg2;
//		"\\w+([-\\.]\\w+)*@\\w+([\\.-]\\w+)*\\.\\w{2,4}$"				
		if(!email.matches("[A-Za-z0-9_]+@[A-Za-z0-9]+.[A-Za-z]{2,4}")){
			FacesMessage message = new FacesMessage();
			message.setDetail("�����ʽΪ��abc@163.com");
			message.setSummary("�����ʽΪ��abc@163.com");
			throw new ValidatorException(message);
		}
		
	}

}
