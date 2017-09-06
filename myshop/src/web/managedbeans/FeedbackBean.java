package web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beans.Feedback;
import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;


@ManagedBean
@SessionScoped
public class FeedbackBean {
	private String feedbackComment;
	private String userId;	
	private String message;
	private Feedback feedback;


	public String getFeedbackComment() {
		return feedbackComment;
	}
	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}
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
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public FeedbackBean(){
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			this.userId = (String) session.getAttribute("userId");
			this.feedbackComment = null;

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " FeedbackBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public String SubmitFeedBack(){
		try {
			if(userId ==null){
				throw new Exception("ÇëÏÈµÇÂ¼");
			}
			feedback = new Feedback();
			feedback.setFeedbackComment(feedbackComment);
			feedback.setUserId(userId);
			Factory.createFeedbackService().submitFeedback(feedback);
			this.feedbackComment = null;
			this.message = "¸ÐÐ»ÄúµÄ±¦¹óÒâ¼û";
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " SubmitFeedBack", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "successPage";		
	}
	
	public String goToFeedback(){
		this.userId = null;
		this.message = null;
		this.feedback = null;
		this.feedbackComment = null;
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(userId ==null){
				throw new Exception("ÇëÏÈµÇÂ¼");
			}
			this.feedbackComment = null;

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " goToFeedback", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
			return "";
		}
		return "feedback.jsp";		
	}
}
