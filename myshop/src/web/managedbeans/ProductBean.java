package web.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Product;
import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;

@ManagedBean
@SessionScoped
public class ProductBean {
	
	private String userId;
	private String proName;
	private String proCategory;
	private Double price;
	private String imgUrl;
	private String proDes;
	private Boolean selected;
	private String styleClass;
	private String message;
	private Integer purNum;
	private Integer stock;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProCategory() {
		return proCategory;
	}
	public void setProCategory(String proCategory) {
		this.proCategory = proCategory;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getProDes() {
		return proDes;
	}
	public void setProDes(String proDes) {
		this.proDes = proDes;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}	
	public String getSytleClass() {
		return styleClass;
	}
	public void setSytleClass(String sytleClass) {
		this.styleClass = sytleClass;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getPurNum() {
		return purNum;
	}
	public void setPurNum(Integer purNum) {
		this.purNum = purNum;
	}	
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	public ProductBean(){
		this.selected = false;
		this.purNum = 1;
	}
	
	public String doPurchase(){
		String path = "";
//		String orderId = "";
		try {		
			if(this.purNum > this.stock){
				throw new Exception("库存不足");
			}
			ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) eContext.getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(userId != null){			
//				System.out.println("执行购买方法 userID ！= null");
				List<Product> prolist = Factory.createProductService().getProductsWithId(proName, purNum);
//				orderId = Factory.createProductService().doPurchase(prolist, userId);
				Factory.createProductService().doPurchase(prolist, userId);
				this.message = "购买成功，您可在订单管理页面中查看到产品订单。";
				path = "purchaseSuccess.jsp";
			}
			else {
				throw new Exception("请先登录！");
			}
			this.stock = Factory.createProductService().getStock(proName);
		}  catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " doPurchase", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}	
		return path;
	}
	public String addToCart(){				
		String path = "";
		try {		
			if(this.purNum > this.stock){
				throw new Exception("库存不足");
			}
			ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) eContext.getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(userId != null){			
				List<Product> prolist = Factory.createProductService().getProductsWithId(proName, purNum);
				Factory.createProductService().addToCart(prolist, userId);
				this.message = "添加到购物车成功，您可在购物车页面中查看到购物车信息";
			}
			else {
				throw new Exception("请先登录！");
			}
			this.stock = Factory.createProductService().getStock(proName);
		}  catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " addToCart", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}	
		return path;
	}
	
	public String addone(){
		this.purNum ++;
		return"";
	}
	public String minusone(){
		if(this.purNum >1){
			this.purNum--;
		}
		return"";
	}
	
	public void selectProduct(ValueChangeEvent vce){
		this.selected = (Boolean) vce.getNewValue();
		this.styleClass = "selectedBtn";
	}
	
	public String goToUrl(){		
		this.selected = false;
		this.purNum = 1;
		this.styleClass = null;
		this.message = null;
		try {			
			ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) eContext.getSession(true);
			this.userId = (String) session.getAttribute("userId");
			HttpServletRequest request = (HttpServletRequest) eContext.getRequest();
			this.proName =  request.getParameter("proName");	
//			System.out.println("in goToUrl  userID:  " + userId);
//			System.out.println("in goToUrl  goToUrl:  " + proName);
			Product pro = Factory.createProductService().getNewProduct(proName);
//			System.out.println("PRO NAME" + pro.getProName());
			this.proCategory = pro.getProCategory();
			this.price = pro.getPrice();
			this.proDes = pro.getProDes();
			this.imgUrl = pro.getImgUrl();
			this.stock = Factory.createProductService().getStock(proName);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " goToUrl", e.toString());
				ErrorRedirect.reDirect();
		}	
	
		return "product.jsp";
		
	}
	
}
