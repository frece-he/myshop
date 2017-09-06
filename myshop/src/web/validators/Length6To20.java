package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("length6To20")
public class Length6To20 implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String str = (String)arg2;
		if(!str.matches("[A-Za-z0-9_@]+")){
			FacesMessage message = new FacesMessage();
			message.setDetail("仅能输入数字、字母、下划线、@");
			message.setSummary("仅能输入数字、字母、下划线、@");
			throw new ValidatorException(message);
		}
		if(str.length() <6){
			FacesMessage message = new FacesMessage();
			message.setDetail("长度不能小于6个字符");
			message.setSummary("长度不能小于6个字符");
			throw new ValidatorException(message);
		}
		if(str.length() > 20){
			FacesMessage message = new FacesMessage();
			message.setDetail("长度不能大于20个字符");
			message.setSummary("长度不能大于20个字符");
			throw new ValidatorException(message);
		}
		
	}

}
