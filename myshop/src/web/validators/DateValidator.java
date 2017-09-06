package web.validators;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import resource.MyShopLogger;


@FacesValidator("dateValidator")
public class DateValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		FacesMessage message = new FacesMessage();
		String str = (String) arg2;
		
		if(arg2 != null){
			Date givenDate = null;		
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				givenDate = sdf.parse(str);
			} catch (Exception e) {
				MyShopLogger.logError(this.getClass().getName(), "validate", e
						.toString());	
				message.setDetail("��ʽӦΪ:1990-01-01");
				message.setSummary("��ʽӦΪ:1990-01-01");
				throw new ValidatorException(message);
			}

				if (givenDate != null && givenDate.after(new Date())) {
					
					message.setDetail("��������Ӧ���ڵ�ǰ����");
					message.setSummary("��������Ӧ���ڵ�ǰ����");
					throw new ValidatorException(message);
				}
			
		}
		


	}
}
