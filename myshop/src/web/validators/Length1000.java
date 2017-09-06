package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("length1000")
public class Length1000 implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String str = arg2 + "";
		if(str.length() > 1000){
			FacesMessage message = new FacesMessage();
			message.setDetail("长度不能大于1000个字符");
			message.setSummary("长度不能大于1000个字符");
			throw new ValidatorException(message);
		}
		
	}

}
