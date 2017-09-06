package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("nameValidator")
public class NameValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		FacesMessage message = new FacesMessage();
		String str = (String) arg2;
		if(str.contains("[0-9]+")){			
			message.setDetail("姓名中不能包含数字");
			message.setSummary("姓名中不能包含数字");
			throw new ValidatorException(message);
		}
//		if(str.length() >35){
//			message.setDetail("姓名不能超过35个字符");
//			message.setSummary("姓名不能超过35个字符");
//			throw new ValidatorException(message);
//		}
		
		if(str.matches("[a-zA-Z@_]+")){
			if(str.length() < 2){
				message.setDetail("英文名不能小于2个字符");
				message.setSummary("英文名不能小于2个字符");
				throw new ValidatorException(message);
			}
			if(str.length() > 45){
				message.setDetail("英文名不能多于45个字符");
				message.setSummary("英文名不能多于45个字符");
				throw new ValidatorException(message);
			}
		}else {
			if(str.length()<2){
				message.setDetail("姓名不能小于2个字符");
				message.setSummary("姓名不能小于2个字符");
				throw new ValidatorException(message);
			}
			if(str.length() > 30){
				message.setDetail("姓名不能多于30个字符");
				message.setSummary("姓名不能多于30个字符");
				throw new ValidatorException(message);
			}
		}
		
	}

}
