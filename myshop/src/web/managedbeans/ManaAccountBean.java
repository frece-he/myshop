package web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.Login;
import business.service.LoginService;

@ManagedBean
@SessionScoped
public class ManaAccountBean {
	private List<Login> lockedAccount;
	private String selectedId;
	private String btnDisplay;
	private String message;
	private List<Login> allAccount;
	private List<String> allAccountId;
	private Login tempLogin;
	
	public List<Login> getLockedAccount() {
		return lockedAccount;
	}
	public void setLockedAccount(List<Login> lockedAccount) {
		this.lockedAccount = lockedAccount;
	}	
	public String getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
	public String getBtnDisplay() {
		return btnDisplay;
	}
	public void setBtnDisplay(String btnDisplay) {
		this.btnDisplay = btnDisplay;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Login> getAllAccount() {
		return allAccount;
	}
	public void setAllAccount(List<Login> allAccount) {
		this.allAccount = allAccount;
	}
	public Login getTempLogin() {
		return tempLogin;
	}
	public void setTempLogin(Login tempLogin) {
		this.tempLogin = tempLogin;
	}
	public List<String> getAllAccountId() {
		return allAccountId;
	}
	public void setAllAccountId(List<String> allAccountId) {
		this.allAccountId = allAccountId;
	}
	public ManaAccountBean(){
		this.message = null;

		try {
			 LoginService service =  Factory.createLoginService();
			this.lockedAccount = service.getUnusualAccount();
			this.allAccount = service.getAllAccount();
			this.allAccountId = new ArrayList<String>();
			for (Login login : allAccount) {
				allAccountId.add(login.getUserId());
			}
			if(lockedAccount != null && !lockedAccount.isEmpty()){
				this.btnDisplay="����ʾ";
			}
			else{
				this.btnDisplay="������ʾ";
			}
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " ManaAccountBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}

	public String searchAccount(){
		this.message = null;
		try {
			Login login = Factory.createLoginService().getLogin(selectedId);
			List<Login> searchedAccount = new ArrayList<Login>();
			searchedAccount.add(login);
			this.lockedAccount = searchedAccount;
			if(login.getStatus().equals("����")){
				this.btnDisplay = "������ʾ";
			}
			else {
				this.btnDisplay = "����ʾ";
			}

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " selectAccount", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "";		
	}
	
	
	
	public void selectAccount(ValueChangeEvent vce){
		String str = (String) vce.getNewValue();
		this.lockedAccount = new ArrayList<Login>();
		for (Login login : allAccount) {
			if(login.getUserId().equals(str)){
				lockedAccount.add(login);
			}
		}
		
	}
	

	public String checklockedAccount(){
		this.message = null;

		try {			
			LoginService service =  Factory.createLoginService();
			this.lockedAccount = service.getUnusualAccount();
			this.allAccount = service.getAllAccount();
			if(lockedAccount != null && !lockedAccount.isEmpty()){
				this.btnDisplay="����ʾ";
			}
			else{
				this.btnDisplay="������ʾ";
			}
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " searchAccount", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "";	
	}


	public void refreshTable(){
		this.message = null;
		this.btnDisplay="����ʾ";
		try {
			this.lockedAccount = Factory.createLoginService().getUnusualAccount();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " refreshTable", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}		
	}

	public void lockAccount(ActionEvent ae){
		try {
			Map<String, Object> map = ae.getComponent().getAttributes();
			String userId = (String) map.get("userId");
			Factory.createLoginService().lockAccount(userId);
			this.searchAccount();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " lockAccount", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}

	public String unlockAccount(){
		try {
			List<String> unlockingId = new ArrayList<String>();
			for (Login log  : lockedAccount) {
				if(log.getSelected()){
					unlockingId.add(log.getUserId());
				}
			}
			Factory.createLoginService().unlockAccount(unlockingId);
			this.refreshTable();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " unlockAccount", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "";		
	}


}
