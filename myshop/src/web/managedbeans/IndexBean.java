package web.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beans.Product;
import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;

@ManagedBean
@SessionScoped
public class IndexBean {
	private String pro1Name;
	private String pro2Name;
	private String pro3Name;
	private String pro4Name;
	private String pro5Name;
	private String pro1ImgUrl;
	private String pro2ImgUrl;
	private String pro3ImgUrl;
	private String pro4ImgUrl;
	private String pro5ImgUrl;
	
	
	public String getPro1Name() {
		return pro1Name;
	}


	public void setPro1Name(String pro1Name) {
		this.pro1Name = pro1Name;
	}


	public String getPro2Name() {
		return pro2Name;
	}


	public void setPro2Name(String pro2Name) {
		this.pro2Name = pro2Name;
	}


	public String getPro3Name() {
		return pro3Name;
	}


	public void setPro3Name(String pro3Name) {
		this.pro3Name = pro3Name;
	}


	public String getPro4Name() {
		return pro4Name;
	}


	public void setPro4Name(String pro4Name) {
		this.pro4Name = pro4Name;
	}


	public String getPro5Name() {
		return pro5Name;
	}


	public void setPro5Name(String pro5Name) {
		this.pro5Name = pro5Name;
	}


	public String getPro1ImgUrl() {
		return pro1ImgUrl;
	}


	public void setPro1ImgUrl(String pro1ImgUrl) {
		this.pro1ImgUrl = pro1ImgUrl;
	}


	public String getPro2ImgUrl() {
		return pro2ImgUrl;
	}


	public void setPro2ImgUrl(String pro2ImgUrl) {
		this.pro2ImgUrl = pro2ImgUrl;
	}


	public String getPro3ImgUrl() {
		return pro3ImgUrl;
	}


	public void setPro3ImgUrl(String pro3ImgUrl) {
		this.pro3ImgUrl = pro3ImgUrl;
	}


	public String getPro4ImgUrl() {
		return pro4ImgUrl;
	}


	public void setPro4ImgUrl(String pro4ImgUrl) {
		this.pro4ImgUrl = pro4ImgUrl;
	}


	public String getPro5ImgUrl() {
		return pro5ImgUrl;
	}


	public void setPro5ImgUrl(String pro5ImgUrl) {
		this.pro5ImgUrl = pro5ImgUrl;
	}
	
	public IndexBean(){
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("userId", null);
			session.setAttribute("tryId", null);
			session.setAttribute("status", null);
			session.setAttribute("proName", null);
			List<Product> prolist= 	Factory.createProductService().getIndexProducts();
			pro1Name = prolist.get(0).getProName();
			pro2Name = prolist.get(1).getProName();
			pro3Name = prolist.get(2).getProName();
			pro4Name = prolist.get(3).getProName();
			pro5Name = prolist.get(4).getProName();
			pro1ImgUrl =  prolist.get(0).getImgUrl();
			pro2ImgUrl =  prolist.get(1).getImgUrl();
			pro3ImgUrl =  prolist.get(2).getImgUrl();
			pro4ImgUrl =  prolist.get(3).getImgUrl();
			pro5ImgUrl =  prolist.get(4).getImgUrl();

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " IndexBean", e.toString());
			ErrorRedirect.reDirect();
	}	
		
	}

	public String goToIndex(){
		try {
			List<Product> prolist= 	Factory.createProductService().getIndexProducts();
			pro1Name = prolist.get(0).getProName();
			pro2Name = prolist.get(1).getProName();
			pro3Name = prolist.get(2).getProName();
			pro4Name = prolist.get(3).getProName();
			pro5Name = prolist.get(4).getProName();
			pro1ImgUrl =  prolist.get(0).getImgUrl();
			pro2ImgUrl =  prolist.get(1).getImgUrl();
			pro3ImgUrl =  prolist.get(2).getImgUrl();
			pro4ImgUrl =  prolist.get(3).getImgUrl();
			pro5ImgUrl =  prolist.get(4).getImgUrl();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " goToIndex", e.toString());
			ErrorRedirect.reDirect();
		}
		
		return "index.jsp";
	}
}
