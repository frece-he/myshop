package web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator(value="fileUploadValidator")
public class FileUploadValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		
		Part file = (Part) arg2;
		if (file==null || file.getSize()<=0 || file.getContentType().isEmpty() ){
			FacesMessage message=new FacesMessage();
			message.setDetail("��ѡ��ͼƬ�ļ�");
			message.setSummary("��ѡ��ͼƬ�ļ�");
			throw new ValidatorException(message);
		}    
		String fileType = file.getContentType();
		if (!(fileType.endsWith("jpg") || fileType.endsWith("png") || fileType.endsWith("jpeg"))) {
			FacesMessage message=new FacesMessage();
			message.setDetail("��ѡ�� .jpg �� .png �ļ�");
			message.setSummary("��ѡ�� .jpg �� .png �ļ�");
			throw new ValidatorException(message);
		}    		
    	if (file.getSize()>2000000){    		
    		FacesMessage message=new FacesMessage();
			message.setDetail("ͼƬ��С���ܳ��� 2MB��");
			message.setSummary("ͼƬ��С���ܳ��� 2MB��");
			throw new ValidatorException(message);
    	}
    		 
		
	}

}
