package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("noSpecialChar")
public class NoSpecialChar implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String str = (String) arg2;
		if(!str.matches("^([\u4e00-\u9fa5?？]+|[a-zA-Z@_0-9]+)$")){
			FacesMessage message = new FacesMessage();
			message.setDetail("不能输入除\"@\",\"?\"与\"_\"的特殊字符");
			message.setSummary("不能输入除\"@\",\"?\"与\"_\"的特殊字符");
			throw new ValidatorException(message);
		}
		if(str.contains("？")){
			FacesMessage message = new FacesMessage();
			message.setDetail("符号应为英文格式");
			message.setSummary("符号应为英文格式");
			throw new ValidatorException(message);
		}
		
	}

}
