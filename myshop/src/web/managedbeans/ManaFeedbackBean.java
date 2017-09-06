package web.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.Feedback;

@ManagedBean
@RequestScoped
public class ManaFeedbackBean {
	private String userId;	
	private String message;
	private List<Feedback> feedbackList;


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	public ManaFeedbackBean(){
		try {
			this.feedbackList = Factory.createFeedbackService().viewAllFeedback();
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " ManaFeedbackBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public String refreshFeedback(){
		try {
			this.feedbackList = Factory.createFeedbackService().viewAllFeedback();
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " refreshFeedback", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		
		return"";
	}
}
