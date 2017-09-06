package web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import resource.Factory;
import resource.MyShopLogger;
import beans.Login;


@ManagedBean
@SessionScoped
public class LoginBean {
	private String userId;
	private String password;
	private Integer attempts;
	private String message;
	private String styleClass;

	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAttempts() {
		return attempts;
	}
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
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

	
	public LoginBean() {
		
		this.attempts = 0;
	}
	public String doLogin() {
		String path = "";
		this.styleClass = "";
		this.message = null;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		String id = (String) session.getAttribute("tryId");
		try {
			if (id == null || !(id.equals(userId))){
				attempts = 0;
				}
			session.setAttribute("tryId", userId);			
		
			Login login = new Login();
			login.setUserId(userId);
			login.setPassword(password);
			Login log = Factory.createLoginService().doLogin(login);
							
			path ="index.jsp";
			attempts = 0;
			session.setAttribute("userId", log.getUserId());	
		
			return path;
		} catch (Exception exception) {
			path = "";
//			styleClass="errorMsg";
			session.setAttribute("userId", null);
			MyShopLogger.logError(this.getClass().getName(), "doLogin",
					exception.toString());
			
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "errorPage.jsp";
				
			}else if (exception.getMessage().contains("密码错误")) {
				attempts++;
				if (attempts >= 3) {			
					try {
						Factory.createLoginService().freezeAccount(userId);
					} catch (Exception e) {					
						e.printStackTrace();
					}
					this.message = "您的账户已被冻结，请尝试忘记密码或联系管理员";
					
				}else {
					this.message = "密码错误";
				}
				
			}
			else{
				this.message = exception.getMessage();
			}
			
			return path;
		}
	}

	/****
	 * @description This method make user to logout and invalidate the session
	 * @return String
	 * @catch Exception
	 */
//	public String doLogout() {
//		String path = "";
//		this.message = null;
//		this.styleClass = "";
//		try {
//			FacesContext ftx = FacesContext.getCurrentInstance();
//			ExternalContext etx = ftx.getExternalContext();
//			HttpSession session = (HttpSession) etx.getSession(true);
//			String userId = (String) session.getAttribute("userId");
//			Factory.createLoginService().logout(userId);
//			session.invalidate();
//			this.message = MyShopConfig.getMessageFromProperties("LoginBean.LOGOUT_SUCCESS");
//			path = "/pages/logout";
//			this.styleClass = "successMsg";
//			
//		} catch (Exception exception) {
//			MyShopLogger.logError(this.getClass().getName(), "doLogout",
//					exception.toString());
//			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
//		
//				path = "/pages/login.jsp";
//			}
//			this.message = MyShopConfig.getMessageFromProperties(exception.getMessage());
//				
//		}
//		return path;
//	}
//	
//	public String forgetPassword(){
//		String path = "";
//		this.styleClass = "";
//		this.message = null;
//		
//		try {	
//			
//			ForgotPosswordService service = Factory.createForgotPasswordService();
//			service.checkUserId(userId);			
//			
//			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//			session.setAttribute("userId", userId);
//			
//			path = "forgotpassword";
//			
//		} catch (Exception exception) {
//
//			MyShopLogger.logError(this.getClass().getName(), "forgetPassword",
//					exception.getMessage());
//			path = "";
//			styleClass="errorMessage";
//
//		}		
//		
//		return path;	
//	}
	
}