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
			message.setDetail("请选择图片文件");
			message.setSummary("请选择图片文件");
			throw new ValidatorException(message);
		}    
		String fileType = file.getContentType();
		if (!(fileType.endsWith("jpg") || fileType.endsWith("png") || fileType.endsWith("jpeg"))) {
			FacesMessage message=new FacesMessage();
			message.setDetail("请选择 .jpg 或 .png 文件");
			message.setSummary("请选择 .jpg 或 .png 文件");
			throw new ValidatorException(message);
		}    		
    	if (file.getSize()>2000000){    		
    		FacesMessage message=new FacesMessage();
			message.setDetail("图片大小不能超过 2MB。");
			message.setSummary("图片大小不能超过 2MB。");
			throw new ValidatorException(message);
    	}
    		 
		
	}

}
