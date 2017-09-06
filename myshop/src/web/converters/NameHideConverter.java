package web.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import resource.MyShopLogger;

@FacesConverter("nameHideConverter")
public class NameHideConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = (String) arg2;
		String newStr = null;
		try {
			newStr = str.substring(0,1) + "***" + str.substring(str.length() - 1);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getAsString", e
					.toString());
		}
		return newStr;
	}

}
