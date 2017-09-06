package resource;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorRedirect {

	public static void reDirect1()
	{
		FacesContext ftx = FacesContext.getCurrentInstance();
		ExternalContext etx = ftx.getExternalContext();
		HttpServletRequest req=(HttpServletRequest) etx.getRequest();
		try {
			req.getRequestDispatcher("../../faces/pages/errorPage.jsp").forward(req, ((HttpServletResponse)etx.getResponse()));
		} catch (ServletException e) {
			MyShopLogger.logError("ErrorRedirect", "reDirect1",
					e.toString());
			
		} catch (IOException e) {
			MyShopLogger.logError("ErrorRedirect", "reDirect1",
					e.toString());
			
		}
	}
	public static void reDirect()
	{
		FacesContext ftx = FacesContext.getCurrentInstance();
		ExternalContext etx = ftx.getExternalContext();
//		HttpServletResponse res=(HttpServletResponse)etx.getResponse();
		try {
			etx.redirect("../../faces/pages/errorPage.jsp");
//			res.sendRedirect("/pages/errorPage.jsp");

		}
		 catch (IOException e) {
			 
			 MyShopLogger.logError("ErrorRedirect", "reDirect",
						e.toString());
				
			}
	}
}
