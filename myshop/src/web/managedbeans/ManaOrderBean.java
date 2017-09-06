package web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.Order;

@ManagedBean
@SessionScoped
public class ManaOrderBean {
	List<Order>orderList = new ArrayList<Order>();
	List<Order>unfinishedOrderList = new ArrayList<Order>();
	List<Order>finishedOrderList = new ArrayList<Order>();
	List<SelectItem> statusItem ;
	private String status;
	private String styleClass;
	private String message;
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public List<Order> getUnfinishedOrderList() {
		return unfinishedOrderList;
	}
	public void setUnfinishedOrderList(List<Order> unfinishedOrderList) {
		this.unfinishedOrderList = unfinishedOrderList;
	}
	public List<Order> getFinishedOrderList() {
		return finishedOrderList;
	}
	public void setFinishedOrderList(List<Order> finishedOrderList) {
		this.finishedOrderList = finishedOrderList;
	}
	public List<SelectItem> getStatusItem() {
		return statusItem;
	}
	public void setStatusItem(List<SelectItem> statusItem) {
		this.statusItem = statusItem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ManaOrderBean(){

		try {
			this.orderList = new ArrayList<Order>();
			statusItem = new ArrayList<SelectItem>();
			this.status = "未完成";
			statusItem.add(new SelectItem("未完成", "查看未完成订单"));
			statusItem.add(new SelectItem("已完成", "查看已完成订单"));

			this.unfinishedOrderList = Factory.createOrderService().checkAllUnfinishedOrders();
			this.finishedOrderList = Factory.createOrderService().checkAllFinishedOrders();
			
				this.orderList = this.unfinishedOrderList;
		
		} catch (Exception e) {
			e.printStackTrace();
			MyShopLogger.logError(this.getClass().getName(), " ManaOrderBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public String viewOrder(){
		try {
			this.message = null;
			this.status = "未完成";
			this.refreshOrder();
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " viewOrder", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		
		return "manaOrder.jsp";		
	}
	
	public void refreshOrder(){
		this.orderList = new ArrayList<Order>();
		this.unfinishedOrderList = new ArrayList<Order>();
		this.finishedOrderList = new ArrayList<Order>();
		try {
			this.unfinishedOrderList = Factory.createOrderService().checkAllUnfinishedOrders();
			this.finishedOrderList = Factory.createOrderService().checkAllFinishedOrders();
			
			if (status == "未完成") {
				this.orderList = this.unfinishedOrderList;
			}
			else if (status == "已完成") {
				this.orderList = this.finishedOrderList;
			}
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " refreshOrder", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public void selectStatus(ValueChangeEvent vce){
		this.status = (String) vce.getNewValue();
		this.refreshOrder();
		if (status.equals("未完成") ) {
			this.orderList = this.unfinishedOrderList;
		}
		else if (status.equals("已完成")) {
			this.orderList = this.finishedOrderList;
		}
	}
	
	public void changeToSent(ActionEvent ae){
		try {
			Map<String, Object> map = ae.getComponent().getAttributes();
			String orderId = (String) map.get("orderId");
			Factory.createOrderService().changeToSent(orderId);
			this.status = "未完成";
			this.refreshOrder();
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " changeToSent", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	public void closeOrder(ActionEvent ae){
		try {
			Map<String, Object> map = ae.getComponent().getAttributes();
			String orderId = (String) map.get("orderId");
//			System.out.println(orderId);
			Order order = null;
			for (Order or : unfinishedOrderList) {
				if(or.getOrderId().equals(orderId)){
					order = or;
				}
			}
			if(order != null){
				Factory.createOrderService().closeOrder(order);
			}
			this.status = "未完成";
			this.refreshOrder();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " closeOrder", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
	}
	
	
}
