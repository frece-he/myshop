package web.managedbeans;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beans.Registration;
import resource.Factory;
import resource.MyShopLogger;


@ManagedBean
@SessionScoped
public class ForgotPasswordBean {
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext eContext = context.getExternalContext();
	HttpSession session = (HttpSession) eContext.getSession(true);
	private String userId;	
	private String securityQuestion;	
	private String securityAnswer;
	private String answer;	
	private Integer count;	
	private String newPassword;
	private String confirmPass;
	private String changePassPanl;
	private String message;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public String getChangePassPanl() {
		return changePassPanl;
	}

	public void setChangePassPanl(String changePassPanl) {
		this.changePassPanl = changePassPanl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ForgotPasswordBean(){
		this.userId = null;
		this.message = null;
		this.count = 0;
		try {


			String id = (String) session.getAttribute("userId");
			if (id == null || !(id.equals(userId)))
			{
				this.count = 0;

			}
			this.userId = id;
			session.setAttribute("userId", userId);

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "ForgotPasswordBean",
					e.toString());

			this.message=e.getMessage();

		}
	}

	public String getProfileDetails(){
		this.message = null;
		try {
			Factory.createForgotPasswordService().checkUserId(userId);
			Registration reg = Factory.createRegistrationService().getProfileDetails(userId);		
			this.userId = reg.getUserId();
			this.securityQuestion = reg.getSecurityQuestion();
			this.securityAnswer = reg.getSecurityAnswer();

		} catch (Exception exception) {
			this.userId = null;
			MyShopLogger.logError(this.getClass().getName(), "getProfileDetails",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				return "errorPage.jsp";
			}
			else {
				this.message = exception.getMessage();
			}
		}
		return "";		
	}



	public String validAnswer(){
		String path="";
		this.message="";
		//		this.newPassword = this.customerName + "@123";
		try {
			if (count >= 3) 
			{			
				this.answer = null;
				throw new Exception("�˻��ѱ�����");
			}
			if(!this.answer.equalsIgnoreCase(this.securityAnswer)){
				this.answer = null;
				throw new Exception("��ȫ����𰸴���");
			}
			this.answer = null;
			this.changePassPanl = "display";

		} catch (Exception exception) {
			this.answer = null;
			session.setAttribute("userId", null);
			session.setAttribute("status", null);
			session.setAttribute("proName", null);
			if(exception.getMessage().contains("��ȫ����𰸴���")){
				this.message="������" + 	(2 - this.count) + "�γ��Ի���";				
				this.count++;				
				if(count >= 3){
					try {
						Factory.createForgotPasswordService().lockAccount(userId);
					} catch (Exception e) {					
						e.printStackTrace();
					}
					this.message = "�����˻��ѱ�����������ϵ����Ա����";
				}
			}
			else{
				this.message=exception.getMessage();
			}
			path="";
			MyShopLogger.logError(this.getClass().getName(), "validAnswer",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "errorPage.jsp";
			}

		}
		return path;

	}	

	public String resetPassword(){
		String path = "";
		try {
			if(!this.newPassword.equals(this.confirmPass)){
				throw new Exception("�����������벻һ��");
			}
			List<String> list = new ArrayList<String>();
			list.add(this.userId);
			list.add(this.newPassword);
			Factory.createForgotPasswordService().changePassword(this.userId,this.newPassword);
			List<String> userIdList =  new ArrayList<String>();
			userIdList.add(userId);
			Factory.createLoginService().unlockAccount(userIdList);
			session.setAttribute("userId", userId);
			path = "successPage.jsp";
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "resetPassword",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "errorPage.jsp";
			}
			else {
				this.message = exception.getMessage();
			}
		}
		return path;
	}

	public String forgetPassword(){
		try {
			this.userId = (String) session.getAttribute("userId");
			this.changePassPanl = null;
			this.newPassword = null;
			this.confirmPass = null;		
			this.message = null;
			this.answer = null;
			this.count = 0;		

			if(this.userId != null){
				Registration reg = Factory.createRegistrationService().getProfileDetails(userId);		
				this.securityQuestion = reg.getSecurityQuestion();
				this.securityAnswer = reg.getSecurityAnswer();
			}else {
				this.securityQuestion = null;
				this.securityAnswer = null;
			}

		} catch (Exception exception) {
			this.userId = null;
			MyShopLogger.logError(this.getClass().getName(), "forgetPassword",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				return "errorPage.jsp";
			}
			else {
				this.message = exception.getMessage();
			}
		}

		return "forgotpassword.jsp";		
	}


	//	public String sendMessage(){
	//		String message = "Please unlock my account.";
	//		String senderId = userId;
	//		String recieverId = "Jcart";
	//		String path="";
	//		try {
	//
	//			Factory.createForgotPasswordService().sendMessage(message, recieverId, senderId);
	//			this.style = "successMsg";
	//			this.message= MyShopConfig.getMessageFromProperties("Reset_sendMessageSuccess");
	//			path = "forgotpassword";
	//		} 
	//		catch (Exception exception)	{		
	//			style = "errorMsg";
	//			MyShopLogger.logError(this.getClass().getName(), "sendMessage", exception
	//					.toString());			
	//			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
	//				path = "/pages/errorPage";
	//			}			
	//			else{
	//				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
	//			}
	//		}
	//		
	//		return path;
	//
	//	}


}











