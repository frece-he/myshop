package web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.Product;
import business.service.ProductService;

@ManagedBean
@SessionScoped
public class CartBean {
	private String userId;
	private List<Product> products = new ArrayList<Product>();	
	private List<Product> settledPro = new ArrayList<Product>();	
	private Double totalAmount;	
	private String message;


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}		
	public List<Product> getSettledPro() {
		return settledPro;
	}
	public void setSettledPro(List<Product> settledPro) {
		this.settledPro = settledPro;
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public CartBean(){
		this.totalAmount = 0.0;
		this.message = null;
		try {
			ExternalContext eContext = FacesContext .getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) eContext.getSession(true);
			this.userId = (String) session.getAttribute("userId");
//			System.out.println(this.userId);
//			this.products = Factory.createOrderService().checkCart(this.userId);
//			if(this.products  != null && !this.products.isEmpty()){
////				System.out.println("购物车不为空");
//				for (Product pro : this.products ) {
//					this.totalAmount += 1.0* pro.getPrice();
//				}
//				Boolean isSame = null;
//				for (Product unsettledPro : products) {
//					isSame = false;
//					for (Product pro : settledPro) {
//						if(pro.getProName().equals(unsettledPro.getProName())){
//							pro.setProNum(pro.getProNum() + 1);
//							isSame = true;
//							break;
//						}
//					}
//					if(isSame){
//						settledPro.add(unsettledPro);
//					}
//				}
//			}else {
//				this.totalAmount = 0.0;
//			}
			for (Product pro : products) {
				pro.setSelected(false);
			}
			this.refreshCart();
			
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " CartBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();				
			}
			else{
				this.message=e.getMessage();
			}
//			e.printStackTrace();
		}

	}

	public void refreshCart(){
		this.message = null;
		this.products = new ArrayList<Product>();
		this.settledPro = new ArrayList<Product>();		
		this.totalAmount = 0.0;
		try {
			this.products = Factory.createOrderService().checkCart(this.userId);

			if(products != null && !products.isEmpty()){
				for (Product product : products) {
					totalAmount += 1.0 * product.getPrice();
				}
				Boolean isSame = null;
				for (Product unsettledPro : products) {
					isSame = false;
					for (Product pro : settledPro) {
						if(pro.getProName().equals(unsettledPro.getProName())){
							pro.setProNum(pro.getProNum() + 1);
							isSame = true;
							break;
						}
					}
					if(isSame == false){
						settledPro.add(unsettledPro);
					}
				}
			}
			else {
				totalAmount = 0.0;
			}
			
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " refreshCart", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public void changeProNum(ValueChangeEvent  vce) {
		this.message = null;
		List<Product> editList = null; 		
		try {
			Integer newNum = (Integer) vce.getNewValue();
			if(newNum == null){
				return;
			}
			Map<String, Object> map = vce.getComponent().getAttributes();
			String proName = (String) map.get("proName");
			Integer oriNum = null;
			for (Product product : settledPro) {
				if(product.getProName().equals(proName)){
					oriNum = product.getProNum();
					break;
				}
			}
			if(newNum == oriNum){
				return;
			}
			else if(newNum > oriNum){
				ProductService service = Factory.createProductService();
				editList = service.getProductsWithId(proName, newNum - oriNum);
				service.addToCart(editList, userId);
			}
			else if(newNum < oriNum){
				editList = new ArrayList<Product>();
				for (Product pro : products) {
					if(pro.getProName().equals(proName)){
						editList.add(pro);
					}
				}
				Factory.createOrderService().removeFromCart(editList, userId);
			}
		
			this.refreshCart();

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " changeProNum", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public void addOne(ActionEvent ae) {
		this.message = null;
		List<Product> addProList = null;
		try {
			Map<String, Object> map = ae.getComponent().getAttributes();
			String proName = (String) map.get("proName");			ProductService service = Factory.createProductService();
			addProList = service.getProductsWithId(proName, 1);
			service.addToCart(addProList, userId);
			this.refreshCart();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " addOne", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	public void deleteOne(ActionEvent ae) {
		this.message = null;
		List<Product> remProList = new ArrayList<Product>();
//		System.out.println("从购物车中移除");
		try {
			Map<String, Object> map = ae.getComponent().getAttributes();
			String proName = (String) map.get("proName");
			
			for (Product pro : products) {
				if(pro.getProName().equals(proName)){
					remProList.add(pro);
					break;
				}
			}
			Factory.createOrderService().removeFromCart(remProList, this.userId);
			this.refreshCart();

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " deleteOne", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	
	

	public String removeItems() {
		this.message = null;
		List<Product> remProList = new ArrayList<Product>();
//		System.out.println("从购物车中移除");
		String path = "";
		try {
			
			System.out.println("CartBean");
			System.out.println("1");
			for (int i = 0; i < products.size(); i++) {
				Product pro = products.get(i);
				if(pro.getSelected() == true){
					remProList.add(pro);
				}
			}
			System.out.println("2");
			if(remProList.isEmpty()){
				this.message = "未选择需要移除的商品";
				return path;
			}
			System.out.println("3");
			Factory.createOrderService().removeFromCart(remProList, this.userId);
			System.out.println("4");
			this.refreshCart();
			System.out.println("5");
		

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " removeItems", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return path;		
	}

	public String clearCart(){
		this.message = null;
		try {
			Factory.createOrderService().clearCart(this.userId);
			this.products.clear();
			this.totalAmount = 0.0;
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " clearCart", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "";

	}
	public String settlementCart(){
		this.message = null;
		try {
			Factory.createOrderService().settlementCart(userId);
			
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " settlementCart", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "purchaseSuccess.jsp";
	}
	public String viewMyCart(){
		this.message = null;
		this.totalAmount = 0.0;
		String path = "";
		try {
			ExternalContext eContext = FacesContext .getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) eContext.getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(this.userId == null){
				return "";
			}

//			this.products = Factory.createOrderService().checkCart(this.userId);
//			if(this.products  != null && !this.products.isEmpty()){
//				for (Product pro : this.products ) {
//					this.totalAmount += 1.0* pro.getPrice();
//				}
//				Boolean isSame = null;
//				for (Product unsettledPro : products) {
//					isSame = false;
//					for (Product pro : settledPro) {
//						if(pro.getProName().equals(unsettledPro.getProName())){
//							pro.setProNum(pro.getProNum() + 1);
//							isSame = true;
//							break;
//						}
//					}
//					if(isSame){
//						settledPro.add(unsettledPro);
//					}
//				}
//			}else {
//				this.totalAmount = 0.0;
//			}
			for (Product pro : products) {
				pro.setSelected(false);
			}
			this.refreshCart();
			
			path = "checkCart.jsp";
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " viewMyCart", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();				
			}
			else{
				this.message=e.getMessage();
			}
			e.printStackTrace();
			path = "errorPage.jsp";
		}
		return path;
	}

}
