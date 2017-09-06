package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.OrderEntity;
import resource.Factory;
import resource.HibernateUtility;
import resource.MyShopLogger;
import beans.Order;
import beans.Product;

public class OrderDAOImpl implements OrderDAO{
	SessionFactory sessionFactory = HibernateUtility.createSessionFactory();

	@Override
	public void addToCart(List<Product> prolist, String userId) throws Exception {
		Session session = null;
		OrderEntity oe = null;
		Double totalPrice = 0.0;
		String products = "";
		try {			
			Factory.createProductDAO().addProductsToCart(prolist);
			session = sessionFactory.openSession();
			Query q = session.createQuery("select c from OrderEntity c where c.userId = ? and c.status = ?");
			q.setParameter(0, userId);
			q.setParameter(1, "未生成");
			List<OrderEntity> newOrder = q.list();
//			System.out.println("q.list  success");
			if(newOrder == null || newOrder.isEmpty()){

				
				oe = new OrderEntity();
//				System.out.println("产品个数： " + prolist.size());				
				for (Product product : prolist) {
					totalPrice += product.getPrice();
					products += product.getProId() + ",";
				}
				oe.setUserId(userId);
				oe.setProducts(products);
				oe.setTotalNum(prolist.size());
				oe.setTotalPrice(totalPrice);					
				oe.setOrderTime(new Date());				
				oe.setStatus("未生成");
				session.beginTransaction();
				session.save(oe);				
			}
			else {
				oe = newOrder.get(0);				
				for (Product product : prolist) {
					totalPrice += product.getPrice();
					products += product.getProId() + ",";
				}
				session.beginTransaction();
				oe.setOrderTime(new Date());
				oe.setProducts(oe.getProducts() + products);
				oe.setTotalNum(oe.getTotalNum() + prolist.size());
				oe.setTotalPrice(oe.getTotalPrice() + totalPrice);
				session.update(oe);				
			}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"addToCart", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.getTransaction().commit();
			session.close();

		}
	}
	
	@Override
	public void removeFromCart(List<Product> prolist, String userId)
			throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.userId = ? and c.status = ?");
			q.setParameter(0, userId);
			q.setParameter(1, "未生成");
			List<OrderEntity> oelist = q.list();
			if(oelist != null && !oelist.isEmpty()){
				OrderEntity oe = oelist.get(0);
				
				List<String> proIdList = new ArrayList<String>();
				for (Product pro : prolist) {
					proIdList.add(pro.getProId().toString());
				}
				Factory.createProductDAO().returnToStock(proIdList);
				
				String products = oe.getProducts();
				String[] proArr = products.split(",");
				
				Double removedPrice = 0.0;
				for (Product product : prolist) {
					removedPrice += product.getPrice();
				}
				String newProducts = "";
				List<String> list = new ArrayList<String>();
				for (String pro : proArr) {
					list.add(pro);
				}
				for (Product pro : prolist) {
					if(list.contains(pro.getProId().toString())){
						list.remove(pro.getProId().toString());
					}			
				}
				if(list == null || list.isEmpty() ){
					session.beginTransaction();
					session.delete(oe);
				}else{
					for (String string : list) {
						newProducts += string + ",";
					}				
					session.beginTransaction();
					oe.setOrderTime(new Date());
					oe.setProducts(newProducts);
					oe.setTotalNum(oe.getTotalNum() - prolist.size());
					oe.setTotalPrice(oe.getTotalPrice() - removedPrice);
					session.update(oe);
				}
				
				
			}
			else {
				throw new Exception("购物车已经为空");
			}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"removeFromCart", exception.getMessage());
			if(exception.toString().contains("不存在的用户名")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			if(session.getTransaction().isActive()){
				session.getTransaction().commit();
			}
			if (session.isOpen()) {
				session.close();
			}
			
			

		}
		
	}

	@Override
	public void clearCart(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.userId = ? and c.status = ?");
			q.setParameter(0, userId);
			q.setParameter(1, "未生成");
			List<OrderEntity> oelist = q.list();
			
			if(oelist != null && !oelist.isEmpty()){
				OrderEntity oe = oelist.get(0);
				String[] proIdArr = oe.getProducts().split(",");				
				List<String> proIdList = new ArrayList<String>();
				for (String proId : proIdArr) {
					proIdList.add(proId);
				}
				Factory.createProductDAO().returnToStock(proIdList);
				session.beginTransaction();
				session.delete(oe);
			}
			else {
				throw new Exception("不存在的用户名");
			}
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"clearCart", exception.getMessage());
			if(exception.toString().contains("不存在的用户名")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			session.getTransaction().commit();
			session.close();

		}
		
	}
	
	@Override
	public void settlementCart(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.userId = ? and c.status = ?");
			q.setParameter(0, userId);
			q.setParameter(1, "未生成");
			List<OrderEntity> oelist = q.list();
			if(oelist != null && !oelist.isEmpty()){
				OrderEntity oe = oelist.get(0);
				session.beginTransaction();
				oe.setOrderTime(new Date());
				oe.setStatus("待发货");
			}
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"settlementCart", exception.getMessage());
				throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.getTransaction().commit();
			session.close();

		}
		
	}

	@Override
	public String generateOrder(List<Product> prolist, String userId) throws Exception {
		Session session = sessionFactory.openSession();
		String orderId = null;	
		try {
			Double totalPrice = 0.0;
			String products = "";
			OrderEntity oe = new OrderEntity();
			for (Product product : prolist) {
				totalPrice += product.getPrice();
				products += product.getProId() + ",";
			}
			oe.setUserId(userId);
			oe.setProducts(products);
			oe.setTotalNum(prolist.size());
			oe.setTotalPrice(totalPrice);					
			oe.setOrderTime(new Date());				
			oe.setStatus("待发货");
			session.beginTransaction();
			orderId = session.save(oe).toString();			

			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"generateOrder", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.getTransaction().commit();
			session.close();

		}
		return orderId;
	}

	@Override
	public void changeToSent(String orderId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.orderId = ?");
			q.setParameter(0, orderId);
			List<OrderEntity> oelist = q.list();
			if(oelist != null && !oelist.isEmpty()){
				OrderEntity oe = oelist.get(0);
				session.beginTransaction();
				oe.setOrderTime(new Date());
				oe.setStatus("已发货");
			}
			else {
				throw new Exception("不存在的用户名");
			}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"changeToSent", exception.getMessage());
			if(exception.toString().contains("不存在的用户名")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			session.getTransaction().commit();
			session.close();

		}
		
	}

	@Override
	public void finishOrder(String orderId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.orderId = ?");
			q.setParameter(0, orderId);
			List<OrderEntity> oelist = q.list();
			if(oelist != null && !oelist.isEmpty()){
				OrderEntity oe = oelist.get(0);
				session.beginTransaction();
				oe.setOrderTime(new Date());
				oe.setStatus("已完成");
				
			}
			else {
				throw new Exception("不存在的用户名");
			}

			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"finishOrder", exception.getMessage());
			if(exception.toString().contains("不存在的用户名")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			session.getTransaction().commit();
			session.close();

		}
		
	}

	@Override
	public void closeOrder(String orderId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.orderId = ?");
			q.setInteger(0, Integer.parseInt(orderId));
			List<OrderEntity> oelist = q.list();
			if(oelist != null && !oelist.isEmpty()){
				OrderEntity oe = oelist.get(0);
				String[] proIdArr = oe.getProducts().split(",");				
				List<String> proIdList = new ArrayList<String>();
				for (String proId : proIdArr) {
					proIdList.add(proId);
				}
				Factory.createProductDAO().returnToStock(proIdList);
				session.beginTransaction();
				oe.setOrderTime(new Date());
				oe.setStatus("已关闭");
			}
			else {
				throw new Exception("不存在的用户名");
			}

			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"closeOrder", exception.getMessage());
			if(exception.toString().contains("不存在的用户名")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			session.getTransaction().commit();
			session.close();

		}
		
	}

	@Override
	public List<Product> checkCart(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		List<Product> prolist = null;
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.userId = ? and c.status = ?");
			q.setParameter(0, userId);
			q.setParameter(1, "未生成");
			List<OrderEntity> oelist = q.list();
			if(oelist != null && !oelist.isEmpty()){
				OrderEntity oe = oelist.get(0);
				String[] proArr =  oe.getProducts().split(",");
				List<String> proIdList = new ArrayList<String>();
				for (String proId : proArr) {
					proIdList.add(proId);
				}
				prolist = Factory.createProductDAO().getproductById(proIdList);
			}
			else {
				prolist = new ArrayList<Product>();
			}
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"checkCart", exception.getMessage());
				throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if(session.isOpen()){
				session.close();
			}			

		}
		return prolist;
	}

	@Override
	public List<Order> checkOrderById(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		 List<Order> orderlist = null;

		try {
			Query q = session.createQuery("select c from OrderEntity c where c.userId = ? and c.status !='未生成' ");
			q.setParameter(0, userId);
			List<OrderEntity>oelist  = q.list();
			if(oelist != null && !oelist.isEmpty()){
				orderlist = new ArrayList<Order>();
				Order order = null;
				for (OrderEntity oe : oelist) {
					order = new Order();
					order.setUserId(oe.getUserId());
					order.setOrderId(oe.getOrderId());
					order.setProducts(oe.getProducts());
					order.setOrderTime(oe.getOrderTime());					
					order.setStatus(oe.getStatus());
					order.setTotalNum(oe.getTotalNum());
					order.setTotalPrice(oe.getTotalPrice());
					orderlist.add(order);
				}
			}else {
				orderlist = new ArrayList<Order>();
			}

			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"checkOrderById", exception.getMessage());			
				throw new Exception("DAO.TECHNICAL_ERROR");			
		} finally {
			session.close();

		}
		return orderlist;
	}

	@Override
	public List<Order> checkAllUnfinishedOrders() throws Exception {
		Session session = sessionFactory.openSession();
		List<Order> orderlist = null;
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.status=? or c.status=? ");			
			q.setParameter(0, "待发货");
			q.setParameter(1, "已发货");
			List<OrderEntity>oelist = q.list();
			 if(oelist != null && !oelist.isEmpty()){
					orderlist = new ArrayList<Order>();
					Order order = null;
					for (OrderEntity oe : oelist) {
						order = new Order();
						order.setUserId(oe.getUserId());
						order.setOrderId(oe.getOrderId());
						order.setProducts(oe.getProducts());
						order.setOrderTime(oe.getOrderTime());					
						order.setStatus(oe.getStatus());
						order.setTotalNum(oe.getTotalNum());
						order.setTotalPrice(oe.getTotalPrice());
						orderlist.add(order);
					}
				}else {
					orderlist = new ArrayList<Order>();
				}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"checkAllUnfinishedOrders", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();

		}
		return orderlist;
	}

	@Override
	public List<Order> checkAllFinishedOrders() throws Exception {
		Session session = sessionFactory.openSession();
		List<Order> orderlist = null;
		
		try {
			Query q = session.createQuery("select c from OrderEntity c where c.status=? ");			
			q.setParameter(0, "已完成");
			List<OrderEntity>oelist = q.list();
			 if(oelist != null && !oelist.isEmpty()){
					orderlist = new ArrayList<Order>();
					Order order = null;
					for (OrderEntity oe : oelist) {
						order = new Order();
						order.setUserId(oe.getUserId());
						order.setOrderId(oe.getOrderId());
						order.setProducts(oe.getProducts());
						order.setOrderTime(oe.getOrderTime());					
						order.setStatus(oe.getStatus());
						order.setTotalNum(oe.getTotalNum());
						order.setTotalPrice(oe.getTotalPrice());
						orderlist.add(order);
					}
				}else {
					orderlist = new ArrayList<Order>();
				}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"checkAllFinishedOrders", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();

		}
		return orderlist;
	}


//	public static void main(String[] args) {
//	OrderDAOImpl aa = new OrderDAOImpl();
//	List<Product> prolist = new ArrayList<Product>();
//	try {	
//		prolist = aa.checkCart("311");
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//}
	

}
