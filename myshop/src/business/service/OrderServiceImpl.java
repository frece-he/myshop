package business.service;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import resource.Factory;
import resource.MyShopLogger;
import beans.Order;
import beans.Product;

public class OrderServiceImpl implements OrderService {

	@Override
	public List<Product> checkCart(String userId) throws Exception {
		try {
			return Factory.createOrderDAO().checkCart(userId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " checkCart", e.toString());
			throw e;
		}
		
	}

	@Override
	public void removeFromCart(List<Product> prolist, String userId)
			throws Exception {
//		List<Product> remlist = new ArrayList<Product>();
		try {
			OrderDAO dao = Factory.createOrderDAO();
//			List<Product> orgCart = dao.checkCart(userId);
//			for (Product pro : prolist) {
//				if(pro.getProNum() > 1){
//					String pName = pro.getProName();
//					for (Product orgPro : orgCart) {						
//						if(orgPro.getProName().equals(pName)){
//							remlist.add(orgPro);
//						}
//					}				
//				}else {
//					remlist.add(pro);
//				}
//			}
			
			dao.removeFromCart(prolist, userId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " removeFromCart", e.toString());
			throw e;
		}
		
	}

	@Override
	public void clearCart(String userId) throws Exception {
		try {
			 Factory.createOrderDAO().clearCart(userId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " checkCart", e.toString());
			throw e;
		}
		
	}

	@Override
	public String settlementCart(String userId) throws Exception {
		try {
			Factory.createOrderDAO().settlementCart(userId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " settlementCart", e.toString());
			throw e;
		}
		return "success";
	}

	@Override
	public void changeToSent(String orderId) throws Exception {
		try {
			Factory.createOrderDAO().changeToSent(orderId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " changeToSent", e.toString());
			throw e;
		}
		
	}

	@Override
	public void finishOrder(String orderId) throws Exception {
		try {
			Factory.createOrderDAO().finishOrder(orderId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " finishOrder", e.toString());
			throw e;
		}
		
	}

	@Override
	public void closeOrder(Order order) throws Exception {
		List<String>proIdList = new ArrayList<String>();
		try {
			String[] proIdArr = order.getProducts().split(",");
			for (String proId : proIdArr) {
				proIdList.add(proId);
			}
			Factory.createProductDAO().returnToStock(proIdList);
			Factory.createOrderDAO().closeOrder(order.getOrderId());
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " closeOrder", e.toString());
			throw e;
		}
		
	}

	@Override
	public List<Order> checkOrderById(String userId) throws Exception {
		 List<Order> orderList = null;
		try {
			orderList = Factory.createOrderDAO().checkOrderById(userId);
			
			List<String> proIdList = null;
			String[] proIdArr = null;
			List<Product> prolist = null;
			List<String>proNamelist = null;
			for (Order order : orderList) {
				proIdArr = order.getProducts().split(",");
				proIdList = new ArrayList<String>();
				for (String proId : proIdArr) {
					proIdList.add(proId);
				}
				prolist =  Factory.createProductDAO().getproductById(proIdList);
				proNamelist = new ArrayList<String>();
				for (Product pro : prolist) {
					proNamelist.add(pro.getProName());
				}
				order.setProNamelist(proNamelist);
			}
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " checkOrderById", e.toString());
			throw e;
		}
		return orderList;
	}

	@Override
	public List<Order> checkAllUnfinishedOrders() throws Exception {
		 List<Order> orderList = null;
		try {
			orderList = Factory.createOrderDAO().checkAllUnfinishedOrders();
			List<String> proIdList = null;
			String[] proIdArr = null;
			List<Product> prolist = null;
			List<String>proNamelist = null;
			for (Order order : orderList) {
				proIdArr = order.getProducts().split(",");
				proIdList = new ArrayList<String>();
				for (String proId : proIdArr) {
					proIdList.add(proId);
				}
				prolist =  Factory.createProductDAO().getproductById(proIdList);
				proNamelist = new ArrayList<String>();
				for (Product pro : prolist) {
					proNamelist.add(pro.getProName());
				}
				order.setProNamelist(proNamelist);
			}
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " checkAllUnfinishedOrders", e.toString());
			throw e;
		}
		return orderList;
	}

	@Override
	public List<Order> checkAllFinishedOrders() throws Exception {
		 List<Order> orderList = null;
			try {
				orderList = Factory.createOrderDAO().checkAllFinishedOrders();
				List<String> proIdList = null;
				String[] proIdArr = null;
				List<Product> prolist = null;
				List<String>proNamelist = null;
				for (Order order : orderList) {
					proIdArr = order.getProducts().split(",");
					proIdList = new ArrayList<String>();
					for (String proId : proIdArr) {
						proIdList.add(proId);
					}
					prolist =  Factory.createProductDAO().getproductById(proIdList);
					proNamelist = new ArrayList<String>();
					for (Product pro : prolist) {
						proNamelist.add(pro.getProName());
					}
					order.setProNamelist(proNamelist);
				}
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " checkAllFinishedOrders", e.toString());
			throw e;
		}
		return orderList;
	}

}
