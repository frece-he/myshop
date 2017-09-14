package web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.ProComment;
import business.service.ProductService;

@ManagedBean
@ViewScoped
public class ProCommentBean {
	private List<ProComment> pcList = new ArrayList<ProComment>();
	private String userId;
	private String commentCon;
	private String message;
	public List<ProComment> getPcList() {
		return pcList;
	}
	public void setPcList(List<ProComment> pcList) {
		this.pcList = pcList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentCon() {
		return commentCon;
	}
	public void setCommentCon(String commentCon) {
		this.commentCon = commentCon;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public ProCommentBean(){
		this.message = null;
		try {
			this.pcList = Factory.createProductService().checkComment();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " ProCommentBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public String submitComment(){
		this.message = null;
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(userId == null){
				throw new Exception("���ȵ�¼");
			}
			ProComment comment = new ProComment();
			comment.setUserId(this.userId);
			comment.setCommentCon(this.commentCon);
			ProductService service = Factory.createProductService();
			service.addComment(comment);
			this.pcList = service.checkComment();
			this.commentCon = null;
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " ProCommentBean", e.toString());
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
