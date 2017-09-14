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
			message.setDetail("�����������֡���ĸ���»��ߡ�@");
			message.setSummary("�����������֡���ĸ���»��ߡ�@");
			throw new ValidatorException(message);
		}
		if(str.length() <6){
			FacesMessage message = new FacesMessage();
			message.setDetail("���Ȳ���С��6���ַ�");
			message.setSummary("���Ȳ���С��6���ַ�");
			throw new ValidatorException(message);
		}
		if(str.length() > 20){
			FacesMessage message = new FacesMessage();
			message.setDetail("���Ȳ��ܴ���20���ַ�");
			message.setSummary("���Ȳ��ܴ���20���ַ�");
			throw new ValidatorException(message);
		}
		
	}

}
