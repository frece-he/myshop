package web.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import resource.MyShopLogger;


@FacesConverter("dateConverter")
public class DateConverter implements Converter {


@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Date date = null;
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(arg2 != null){
				date = simpleDate.parse(arg2);
			}
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getAsObject", e
					.toString());	
		}
		return date;
	}

@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = null;
		try {
			if(arg2 != null){
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
				Date date = (Date)arg2;
				
				str = simpleDate.format(date);
			}
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getAsString", e
					.toString());
		}
		return str;
	}
}
