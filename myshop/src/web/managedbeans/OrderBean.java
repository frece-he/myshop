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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import beans.Order;
import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;

@ManagedBean
@SessionScoped
public class OrderBean {

	private String userId;
	List<Order>orderList = new ArrayList<Order>();
	List<Order>allOrderList = new ArrayList<Order>();
	List<Order>unfinishedOrderList = new ArrayList<Order>();
	List<Order>finishedOrderList = new ArrayList<Order>();
	List<Order>closedOrderList = new ArrayList<Order>();
	List<SelectItem> statusItem ;
	private String status;
	private String message;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public List<Order> getAllOrderList() {
		return allOrderList;
	}
	public void setAllOrderList(List<Order> allOrderList) {
		this.allOrderList = allOrderList;
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
	public List<Order> getClosedOrderList() {
		return closedOrderList;
	}
	public void setClosedOrderList(List<Order> closedOrderList) {
		this.closedOrderList = closedOrderList;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public OrderBean(){
		try {
			this.orderList = new ArrayList<Order>();
			statusItem = new ArrayList<SelectItem>();
			this.status = "δ���";
			statusItem.add(new SelectItem("δ���", "�鿴δ��ɶ���"));
			statusItem.add(new SelectItem("�����", "�鿴����ɶ���"));
			statusItem.add(new SelectItem("�ѹر�", "�鿴�ѹرն���"));
			ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) eContext.getSession(true);
			this.userId = (String) session.getAttribute("userId");
			
			this.status = "δ���";
			this.refreshOrder();
		} catch (Exception e) {
			e.printStackTrace();
			MyShopLogger.logError(this.getClass().getName(), " OrderBean", e.toString());
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
		
		if (status.equals("δ���") ) {
			this.orderList = this.unfinishedOrderList;
		}
		else if (status.equals("�����")) {
			this.orderList = this.finishedOrderList;
		}
		else if (status.equals("�ѹر�")) {
			this.orderList = this.closedOrderList;
		}
	}

	public void refreshOrder(){
		this.orderList = new ArrayList<Order>();
		this.allOrderList =new ArrayList<Order>();
		this.unfinishedOrderList = new ArrayList<Order>();
		this.finishedOrderList = new ArrayList<Order>();
		this.closedOrderList = new ArrayList<Order>();
		try {
			this.allOrderList = Factory.createOrderService().checkOrderById(this.userId);
			for (Order order : allOrderList) {
				if(order.getStatus().equals("������") || order.getStatus().equals("�ѷ���")){
					this.unfinishedOrderList.add(order);
				}else if (order.getStatus().equals("�����")) {
					this.finishedOrderList.add(order);
				}else if (order.getStatus().equals("�ѹر�")) {
					this.closedOrderList.add(order);
				}				
			}		
			if (status == "δ���") {
				this.orderList = this.unfinishedOrderList;
			}
			else if (status == "�����") {
				this.orderList = this.finishedOrderList;
			}
			else if (status == "�ѹر�") {
				this.orderList = this.closedOrderList;
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


	
	public void finishOrder(ActionEvent ae){
		try {
			Map<String, Object> map = ae.getComponent().getAttributes();
			String orderId = (String) map.get("orderId");
			Factory.createOrderService().finishOrder(orderId);
			this.status = "δ���";
			this.refreshOrder();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " finishOrder", e.toString());
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
			this.status = "δ���";
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

	public String viewMyOrder(){
		this.message = null;
		this.allOrderList =new ArrayList<Order>();
		this.unfinishedOrderList = new ArrayList<Order>();
		this.finishedOrderList = new ArrayList<Order>();
		this.closedOrderList = new ArrayList<Order>();
		try {
			ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) eContext.getSession(true);
			this.userId = (String) session.getAttribute("userId");
			if(this.userId == null){
				return "";
			}else {
				this.status = "δ���";
				this.refreshOrder();
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyShopLogger.logError(this.getClass().getName(), " viewMyOrder", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=e.getMessage();
			}
		}
		return "checkOrder.jsp";		
	}	
}
