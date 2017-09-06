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
			message.setDetail("���Ȳ��ܴ���1000���ַ�");
			message.setSummary("���Ȳ��ܴ���1000���ַ�");
			throw new ValidatorException(message);
		}
		
	}

}
