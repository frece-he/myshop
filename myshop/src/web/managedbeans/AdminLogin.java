package web.managedbeans;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import resource.Factory;
import resource.MyShopLogger;
import beans.Admin;

@ManagedBean
@SessionScoped
public class AdminLogin {
	private String adminId;
	private String password;
	private String message;
	private String styleClass;
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public AdminLogin(){
		
	}
	
	public String adminLogin() {
		String path = "";
		this.styleClass = "";
		this.message = null;
		
		try {
			
			Admin admin = new Admin();
			admin.setAdminId(adminId);
			admin.setPassword(password);
			String valid = Factory.createLoginService().adminLogin(admin);
			if(valid.equals("success")){
				path = "manaAccount.jsp";
			}

		} catch (Exception exception) {
			path = "";
			styleClass="errorMsg";
			MyShopLogger.logError(this.getClass().getName(), "adminLogin",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}
			this.message = exception.getMessage();
		}
		return path;
	}
}
