package web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import resource.MyShopLogger;

@FacesConverter("phoneNumberConverter")
public class PhoneNumberConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Long phoneNum = null;
		try {						
			if(arg2 != null){
				phoneNum = Long.parseLong(arg2);
			}
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getAsObject", e
					.toString());		
		}
		
		return phoneNum;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = null;
		try {
			if(arg2 != null){
				Long phoneNum = (Long) arg2;
				str = phoneNum + "";
			}
		
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getAsString", e
					.toString());		
		}
		return str;
	}

}
