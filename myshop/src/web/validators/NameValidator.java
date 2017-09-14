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
			message.setDetail("�����в��ܰ�������");
			message.setSummary("�����в��ܰ�������");
			throw new ValidatorException(message);
		}
//		if(str.length() >35){
//			message.setDetail("�������ܳ���35���ַ�");
//			message.setSummary("�������ܳ���35���ַ�");
//			throw new ValidatorException(message);
//		}
		
		if(str.matches("[a-zA-Z@_]+")){
			if(str.length() < 2){
				message.setDetail("Ӣ��������С��2���ַ�");
				message.setSummary("Ӣ��������С��2���ַ�");
				throw new ValidatorException(message);
			}
			if(str.length() > 45){
				message.setDetail("Ӣ�������ܶ���45���ַ�");
				message.setSummary("Ӣ�������ܶ���45���ַ�");
				throw new ValidatorException(message);
			}
		}else {
			if(str.length()<2){
				message.setDetail("��������С��2���ַ�");
				message.setSummary("��������С��2���ַ�");
				throw new ValidatorException(message);
			}
			if(str.length() > 30){
				message.setDetail("�������ܶ���30���ַ�");
				message.setSummary("�������ܶ���30���ַ�");
				throw new ValidatorException(message);
			}
		}
		
	}

}
