package web.managedbeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.Registration;

@ManagedBean
@SessionScoped
public class PersonalInfoBean {
	private String userId;
	private String customerName;
	private String securityQuestion;
	private String securityAnswer;
	private String gender;
	private Long phoneNumber;
	private String emailAddress;
	private String address;
	private Date dateOfRegist;
	private String certification;
	private String dateOfBirth;
	private Registration registration;
	private String message;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateOfRegist() {
		return dateOfRegist;
	}
	public void setDateOfRegist(Date dateOfRegist) {
		this.dateOfRegist = dateOfRegist;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public PersonalInfoBean(){
		try {
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(this.userId == null){
				throw new Exception("���ȵ�¼");
			}
			this.refreshInfo();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " PersonalInfoBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public void refreshInfo(){
		try {
			this.registration = Factory.createRegistrationService().getProfileDetails(userId);

			this.address = this.registration.getAddress();
			this.certification = this.registration.getCertification();
			this.customerName = this.registration.getCustomerName();
			if(this.registration.getDateOfBirth() != null){
				this.dateOfBirth =  new SimpleDateFormat("yyyy-MM-dd").format(this.registration.getDateOfBirth());
			}			
			this.dateOfRegist = this.registration.getDateOfRegist();
			this.emailAddress = this.registration.getEmailAddress();			
			this.gender = this.registration.getGender();
			this.phoneNumber = this.registration.getPhoneNumber();
			this.securityAnswer = this.registration.getSecurityAnswer();
			this.securityQuestion = this.registration.getSecurityQuestion();
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " refreshInfo", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		
	}
	
	public String updateInfo(){
		this.message = null;
		try {
			registration.setSecurityAnswer(securityQuestion);
			registration.setSecurityAnswer(securityAnswer);
			registration.setCustomerName(customerName);
			registration.setPhoneNumber(phoneNumber);
			registration.setAddress(address);
			registration.setGender(gender);
			registration.setEmailAddress(emailAddress);
			if(this.dateOfBirth != null && !this.dateOfBirth.equals("")){
				registration.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth));				
			}
			if(gender != null && dateOfBirth != null && !dateOfBirth.equals("") && emailAddress != null){
				registration.setCertification("��");
			}	
			Factory.createRegistrationService().editProfileDetails(registration);
			this.refreshInfo();
//			this.message = "��Ϣ�޸ĳɹ���";
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " updateInfo", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();				
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "successPage.jsp";		
	}
	public String viewMyInfo(){
//		System.out.println("PersonalInfoBean");
		this.message = null;
		try {
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(this.userId == null){
				throw new Exception("���ȵ�¼");
			}
//			System.out.println(userId);
			this.refreshInfo();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " viewMyInfo", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "changeInfo.jsp";
	}
	
	
}
