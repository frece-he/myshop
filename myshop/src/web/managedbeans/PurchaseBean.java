//package web.managedbeans;
//
//import java.util.List;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ValueChangeEvent;
//import javax.servlet.http.HttpSession;
//
//import resource.ErrorRedirect;
//import resource.Factory;
//import resource.MyShopLogger;
//import beans.Product;
//
//@ManagedBean
//@SessionScoped
//public class PurchaseBean {
//	ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
//	private String userId;
//	private String proName;
//	private String proCategory;
//	private Double price;
//	private String imgUrl;
//	private String proDes;
//	private Boolean selected;
//	private String styleClass;
//	private String message;
//	private Integer purNum;
//	
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getProName() {
//		return proName;
//	}
//	public void setProName(String proName) {
//		this.proName = proName;
//	}
//	public String getProCategory() {
//		return proCategory;
//	}
//	public void setProCategory(String proCategory) {
//		this.proCategory = proCategory;
//	}
//	public Double getPrice() {
//		return price;
//	}
//	public void setPrice(Double price) {
//		this.price = price;
//	}
//	public String getImgUrl() {
//		return imgUrl;
//	}
//	public void setImgUrl(String imgUrl) {
//		this.imgUrl = imgUrl;
//	}
//	public String getProDes() {
//		return proDes;
//	}
//	public void setProDes(String proDes) {
//		this.proDes = proDes;
//	}
//	public Boolean getSelected() {
//		return selected;
//	}
//	public void setSelected(Boolean selected) {
//		this.selected = selected;
//	}
//	public String getStyleClass() {
//		return styleClass;
//	}
//	public void setStyleClass(String styleClass) {
//		this.styleClass = styleClass;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
//	public Integer getPurNum() {
//		return purNum;
//	}
//	public void setPurNum(Integer purNum) {
//		this.purNum = purNum;
//	}
//	
//	
//	public PurchaseBean() {
//		try {
//			FacesContext context = FacesContext.getCurrentInstance();
//			ExternalContext eContext = context.getExternalContext();
//			HttpSession session = (HttpSession) eContext.getSession(true);
//			this.userId = (String) session.getAttribute(userId);
//			if(userId == null){
//				throw new Exception("请先登录");
//			}
//			this.proName = (String) session.getAttribute(proName);
////			this.product = Factory.createProductService().
//			this.purNum = 1;
//			
//		} catch (Exception e) {
//			MyShopLogger.logError(this.getClass().getName(), " purchaseBean", e.toString());
//			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
//				ErrorRedirect.reDirect();
//			}
//			else{
//				this.message=e.getMessage();
//			}
//		}		
//		
//	}
//
//
//	public String doPurchase(){
//		System.out.println("in doPurchase  " + userId);
//		System.out.println("in doPurchase  " + purNum);
//		System.out.println(proName);
//		String path = "";
////		String orderId = "";
//		try {		
//			HttpSession session = (HttpSession) eContext.getSession(true);
//			this.userId = (String) session.getAttribute("userId");
//			if(userId != null){			
//				
//				List<Product> prolist = Factory.createProductService().getProductsWithId(proName, purNum);
////				orderId = Factory.createProductService().doPurchase(prolist, userId);
//				Factory.createProductService().doPurchase(prolist, userId);
//				this.message = "购买成功，您可在订单管理页面中查看到产品订单。";
//			}
//			else {
//				throw new Exception("Need Login");
//			}
//			
//		}  catch (Exception e) {
//			MyShopLogger.logError(this.getClass().getName(), " doPurchase", e.toString());
//			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
//				ErrorRedirect.reDirect();
//			}
//			else{
//				this.message=e.getMessage();
//			}
//		}	
//		return path;
//	}
//	public String addToCart(){		
//		System.out.println("in addToCart");
//		System.out.println(userId);
//		System.out.println(purNum);
//		String path = "";
//		try {		
//			HttpSession session = (HttpSession) eContext.getSession(true);
//			this.userId = (String) session.getAttribute("userId");
//			if(userId != null){			
//				
//				
//				List<Product> prolist = Factory.createProductService().getProductsWithId(proName, purNum);
//				Factory.createProductService().doPurchase(prolist, userId);
//				this.message = "添加到购物车成功，您可在购物车页面中查看到购物车信息";
//			}
//			else {
//				throw new Exception("请先登录");
//			}
//			
//		}  catch (Exception e) {
//			MyShopLogger.logError(this.getClass().getName(), " doPurchase", e.toString());
//			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
//				ErrorRedirect.reDirect();
//			}
//			else{
//				this.message=e.getMessage();
//			}
//		}	
//		return path;
//	}
//	
//	public void selectProduct(ValueChangeEvent vce){
//		this.proName = (String) vce.getNewValue();
//		this.styleClass = "selectedBtn";
//	}
//	
//}