package web.managedbeans;


import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.Registration;
import business.service.RegistrationService;

@ManagedBean
@RequestScoped
public class RegistrationBean {
	private String userId;
	private String password;
	private String confirmPassword;
	private String customerName;
//	private List<SelectItem> questions;
	private String securityQuestion;
	private String securityAnswer;
	private String gender;
	private Long phoneNumber;
	private String emailAddress;
	private String address;
	private Date dateOfRegist;
	private String certification;
	private Date dateOfBirth;
	private Registration registration;
	private String message;
	public String getUserId() {
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
//	public List<SelectItem> getQuestions() {
//		return questions;
//	}
//	public void setQuestions(List<SelectItem> questions) {
//		this.questions = questions;
//	}
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
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}	
	public Registration getReg() {
		return registration;
	}
	public void setReg(Registration reg) {
		this.registration = reg;
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	public RegistrationBean(){
		try {
			this.securityQuestion = "������ȥ�ĵط���?";
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " RegistrationBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}

	public String registerUser() {
		String path="";
//		Long regId = null;
		try {
			 Factory.createRegistrationService().checkUserId(userId);
			
			if (!this.password.equals(this.confirmPassword)) {
				this.message = "�����������벻һ�£�";
				return path;
			} 
			else 
			{

				Registration reg= new Registration();
				reg.setUserId(userId);
				reg.setPassword(password);				
				reg.setSecurityQuestion(securityQuestion.toString());
				reg.setSecurityAnswer(securityAnswer);
				reg.setCustomerName(customerName);
				reg.setPhoneNumber(phoneNumber);
				reg.setAddress(address);				
				RegistrationService regService = Factory.createRegistrationService();
				reg = regService.registerNewMember(reg);
//				System.out.println("AAA");
//				regId=reg.getRegistrationId();
				
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext eContext = context.getExternalContext();
				HttpSession session = (HttpSession) eContext.getSession(true);
				session.setAttribute("userId", userId);
				path="index.jsp";
			}
		} catch (Exception exception) 
		{
			MyShopLogger.logError(this.getClass().getName(), "registerUser",
					exception.toString());
			this.message = exception.getMessage();
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path = "/pages/errorPage.jsp";
			}
			else {
				this.message = exception.getMessage();
			}
//			else{
//				this.message=MyShopConfig.getMessageFromProperties(exception.getMessage());
//			}
		}
		return path;
	}
	
	public void getDetails(String userId){
		RegistrationService regService = Factory.createRegistrationService();
		try {
			registration = regService.getProfileDetails(userId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "registerUser",
					e.toString());
			this.message = e.getMessage();
			e.printStackTrace();
		}
		
	}
	
	public void checkUserId(ValueChangeEvent vce){
		String userId = (String) vce.getNewValue();
		RegistrationService regService = Factory.createRegistrationService();
		try {
			registration = regService.getProfileDetails(userId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "checkUserId",
					e.toString());
			this.message = e.getMessage();
			e.printStackTrace();
		}
	}
	
	public void addOtherQuestion(ValueChangeEvent vce){
		securityQuestion = (String) vce.getNewValue();
	}
	
//	public static void main(String[] args) {
//		Registration reg = new Registration();
//		reg.setUserId("hewenrui123");
//		reg.setPassword("123456");
//		reg.setPhoneNumber(18721686863L);
//		reg.setCustomerName("������");
//		reg.setSecurityQuestion("��ĳ������ǣ�");
//		reg.setSecurityAnswer("ҽԺ");
//		
//		RegistrationService regService = Factory.createRegistrationService();
//		try {
//			reg = regService.registerNewMember(reg);
//			System.out.println("AAA");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(reg.getRegistrationId());
//	}

}
