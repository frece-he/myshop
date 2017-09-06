package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import resource.Factory;
import resource.HibernateUtility;
import resource.MyShopLogger;
import entities.ProductCommentEntity;
import entities.ProductEntity;
import beans.ProComment;
import beans.Product;

public class ProductDAOImpl implements ProductDAO {
	SessionFactory sessionFactory = HibernateUtility.createSessionFactory();

	@Override
	public Product getProductWithoutId(String proName) throws Exception{
		Session s = sessionFactory.openSession();
		Product pro = null;
		try {
			//			System.out.println("open session success");
			String query = "select c from ProductEntity c where c.proName=?";
			Query q = s.createQuery(query);
			//			System.out.println("create query success");
			q.setParameter(0, proName);
			//			System.out.println("set parameter success");
			List<ProductEntity> pelist = q.list();
			//			System.out.println("q.list  success");
			if(pelist.size() > 0){
				ProductEntity pe = pelist.get(0);
				pro = new Product();
				//				pro.setProId(pe.getProId());
				pro.setProName(proName);
				pro.setProCategory(pe.getProCategory());
				pro.setPrice(pe.getPrice());
				pro.setImgUrl(pe.getImgUrl());
				pro.setProDes(pe.getProDes());
				pro.setProLink(pe.getProLink());
			}
			else{
				return null;
			}

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getProductWithoutId", exception.getMessage());
			if (exception.getMessage().contains("不存在的产品名")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			s.close();
		}
		return pro;

	}



	@Override
	public List<Product> getProductsWithId(String proName, Integer proNum) throws Exception {
		Session session = sessionFactory.openSession();
		List<Product>proList = null;
		Product pro = null;
		try {
			//			System.out.println("open session success");
			String query = "select c from ProductEntity c where c.proName=? and c.selledDate is null";
			Query q = session.createQuery(query);
			//			System.out.println("create query success");
			q.setParameter(0, proName);
			//			System.out.println("set parameter success");
			List<ProductEntity> pelist = q.list();
			//			System.out.println("q.list  success");
			if(pelist.size() >= proNum){
				proList = new ArrayList<Product>();
				ProductEntity pe = null;
				for (int i = 0; i < proNum; i++) {
					pe = pelist.get(i);
					pro = new Product();
					pro.setProId(pe.getProId());
					pro.setProName(proName);
					pro.setProCategory(pe.getProCategory());
					pro.setPrice(pe.getPrice());
					pro.setImgUrl(pe.getImgUrl());
					pro.setProDes(pe.getProDes());
					pro.setProLink(pe.getProLink());
					proList.add(pro);
				}				

			}
			else{
				throw new Exception("库存不足");
			}

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getProductsWithId", exception.getMessage());
			if (exception.getMessage().contains("库存不足")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally{
			session.close();
		}
		return proList;
	}




	@Override
	public String doPurchase(List<Product> prolist, String userId) throws Exception {
		String orderId = null;

		try {
			addProductsToCart(prolist);
			orderId = Factory.createOrderDAO().generateOrder(prolist, userId);


		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"doPurchase", exception.getMessage());
			throw exception;
		} 

		return orderId;
	}



	@Override
	public List<Product> getproductById(List<String> proIdList) throws Exception {
		Session session = sessionFactory.openSession();
		List<Product> proList = new ArrayList<Product>();
		List<Integer> intProIdList = new ArrayList<Integer>();
		for (String proId : proIdList) {
			intProIdList.add(Integer.parseInt(proId));
		}
		List<ProductEntity>pelist = null;
		ProductEntity pe = null;
		Product pro = null;
		try {
			Query q = session.createQuery("select c from ProductEntity c where c.proId=?");
			for (Integer proId : intProIdList) {			
				q.setParameter(0, proId);
				pelist = q.list();
				if(pelist != null && !pelist.isEmpty()){
					pe = pelist.get(0);
					pro = new Product();
					pro.setProId(pe.getProId());
					pro.setProName(pe.getProName());
					pro.setProCategory(pe.getProCategory());
					pro.setPrice(pe.getPrice());
					pro.setProDes(pe.getProDes());
					pro.setImgUrl(pe.getImgUrl());
					pro.setProLink(pe.getProLink());
					pro.setSelledDate(pe.getSelledDate());
					pro.setProNum(1);
					proList.add(pro);
				}
				else {
					throw new Exception("不存在的产品ID");
				}
			}


		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getproductById", exception.getMessage());
			if (exception.getMessage().contains("不存在的产品ID")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			session.close();
		}
		return proList;
	}


	@Override
	public void returnToStock(List<String> proIdList) throws Exception {
		Session session = sessionFactory.openSession();
		List<Integer> intProIdList = new ArrayList<Integer>();
		for (String proId : proIdList) {
			intProIdList.add(Integer.parseInt(proId));
		}
		List<ProductEntity>pelist = null;
		ProductEntity pe = null;
		try {
			Query q = session.createQuery("select c from ProductEntity c where c.proId=?");
			for (Integer proId : intProIdList) {				
				q.setParameter(0, proId);
				pelist = q.list();
				if(pelist != null && !pelist.isEmpty()){
					pe = pelist.get(0);
					session.beginTransaction();
					pe.setSelledDate(null);
					session.update(pe);
					session.getTransaction().commit();
				}
				else {
					throw new Exception("不存在的产品ID");
				}
			}

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"returnToStock", exception.getMessage());
			if (exception.getMessage().contains("不存在的产品ID")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} 
	}



	@Override
	public void addProductsToCart(List<Product> prolist) throws Exception {
		Session session = sessionFactory.openSession();
		List<Integer> proIdList = new ArrayList<Integer>();
		for (Product pro : prolist) {
			proIdList.add(pro.getProId());
		}
		try {
			List<ProductEntity>pelist = null;
			ProductEntity pe = null;
			Date today = new  Date();
			Query q = session.createQuery("select c from ProductEntity c where c.proId=?");			
			for (Integer proId : proIdList) {				
				q.setParameter(0, proId);
				pelist = q.list();
				if(pelist != null && !pelist.isEmpty()){
					pe = pelist.get(0);
					session.beginTransaction();
					pe.setSelledDate(today);
					session.update(pe);
					session.getTransaction().commit();
				}
				else {
					throw new Exception("不存在的产品ID");
				}
			}

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"addProducts", exception.getMessage());
			if (exception.getMessage().contains("不存在的产品ID")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}

		} finally {
			session.close();

		}

	}


	@Override
	public void addProductToStock(String proName, Integer proNum)
			throws Exception {
		if(proNum == 0){
			return;
		}
		Product pro = getProductWithoutId(proName);
		Session session  = sessionFactory.openSession();
		try {
			ProductEntity pe = null;
			session.beginTransaction();
			for (int i = 0; i < proNum; i++) {				
				pe = new ProductEntity();
				pe.setImgUrl(pro.getImgUrl());
				pe.setPrice(pro.getPrice());
				pe.setProCategory(pro.getProCategory());
				pe.setProDes(pro.getProDes());
				pe.setProLink(pro.getProLink());
				pe.setProName(pro.getProName());
				pe.setSelledDate(null);
				session.persist(pe);
			}
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"addProducts", exception.getMessage());		
			throw new Exception("DAO.TECHNICAL_ERROR");			
		} finally {
			session.getTransaction().commit();
			session.close();

		}

	}



//	@Override
//	public void releaseNewProduct(List<Product> proList,
//			List<Integer> proNumList) throws Exception {
//		Session session = sessionFactory.openSession();
//		try {
//
//			Query q = session.createQuery("select c from ProductEntity c where c.proId=?");	
//			ProductEntity pe = null;
//			Product pro = null;
//			session.beginTransaction();
//			for (Integer i = 1; i <= 5; i++) {
//				q.setParameter(0, i);
//				//				pe = (ProductEntity) q.list().get(0);
//				pe = new ProductEntity();
//				pro = proList.get(i-1);
//				pe.setProId(i);
//				pe.setProName(pro.getProName());
//				pe.setProCategory(pro.getProCategory());
//				pe.setPrice(pro.getPrice());
//				pe.setImgUrl(pro.getImgUrl());
//				pe.setProDes(pro.getProDes());
//				pe.setProLink(pro.getProLink());
//				pe.setSelledDate(new Date());
//				session.saveOrUpdate(pe);
//			}
//			session.getTransaction().commit();
//			for (int i = 0; i < 5; i++) {
//				addProductToStock(proList.get(i).getProName(), proNumList.get(i));
//			}
//		} catch (Exception exception) {
//			MyShopLogger.logError(this.getClass().getName(),
//					"addProducts", exception.getMessage());		
//			throw new Exception("DAO.TECHNICAL_ERROR");			
//		} finally {
//			session.close();
//
//		}
//	}


	@Override
	public Integer getStock(String proName) throws Exception{
		Session s = sessionFactory.openSession();
		Integer stock = null;
		try {
			String query = "select c from ProductEntity c where c.proName=? and c.selledDate is null";
			Query q = s.createQuery(query);
			q.setParameter(0, proName);
			List<ProductEntity> pelist = q.list();
			stock = pelist.size();

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getProductWithoutId", exception.getMessage());
			if (exception.getMessage().contains("不存在的产品名")){
				throw exception;
			}
			else {
				throw new Exception("DAO.TECHNICAL_ERROR");
			}
		} finally {
			s.close();
		}
		return stock;

	}



	@Override
	public void addStock(String proName, Integer stock) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			String query = "select c from ProductEntity c where c.proName=?";
			Query q = session.createQuery(query);
			q.setParameter(0, proName);
			List<ProductEntity> pelist = q.list();
			ProductEntity pe = pelist.get(0);
			ProductEntity pro = null;
			session.beginTransaction();
			for (int i = 0; i < stock; i++) {
				pro = new ProductEntity();
				//					pro.setProId(pe.getProId());
				pro.setProName(proName);
				pro.setProCategory(pe.getProCategory());
				pro.setPrice(pe.getPrice());
				pro.setImgUrl(pe.getImgUrl());
				pro.setProDes(pe.getProDes());
				pro.setProLink(pe.getProLink());
				session.persist(pro);
			}
			session.getTransaction().commit();

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"addStock", exception.getMessage());

			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();
		}

	}



	@Override
	public List<Product> getIndexProducts() throws Exception {
		List<Product> proList = new ArrayList<Product>();
		Session session = sessionFactory.openSession();
		try {
			String query = "select c from ProductEntity c where c.proId between 1 and 5";
			Query q = session.createQuery(query);
			List<ProductEntity> pelist = q.list();

			Product pro = null;
			session.beginTransaction();
			for (ProductEntity pe : pelist) {
				pro = new Product();
				pro.setProId(pe.getProId());
				pro.setProName(pe.getProName());
				pro.setProCategory(pe.getProCategory());
				pro.setPrice(pe.getPrice());
				pro.setImgUrl(pe.getImgUrl());
				pro.setProDes(pe.getProDes());
				pro.setProLink(pe.getProLink());
				proList.add(pro);
			}			

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getIndexProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		return proList;
	}



	@Override
	public void changeIndexProducts(Integer proNo, Product pro)
			throws Exception {
		Session session = sessionFactory.openSession();
		try {
			String query = "select c from ProductEntity c where c.proId between 1 and 5";
			Query q = session.createQuery(query);
			List<ProductEntity> pelist = q.list();
			ProductEntity pe = pelist.get(proNo - 1);

			session.beginTransaction();
		
				pe.setProId(proNo);
				pe.setProName(pro.getProName());
				pe.setProCategory(pro.getProCategory());
				pe.setImgUrl(pro.getImgUrl());
				pe.setProDes(pro.getProDes());
				pe.setPrice(pro.getPrice());			
				pe.setProLink(pro.getProLink());			
				pe.setSelledDate(new Date());
			session.saveOrUpdate(pe);

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"changeIndexProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}


	@Override
	public List<String> getAllProducts() throws Exception {

		Session session = sessionFactory.openSession();
		List<String> proNameList = null;
		try {
			Query q = session.createQuery("select distinct c.proName from ProductEntity c");
			proNameList = q.list();
				
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getAllProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
//			session.getTransaction().commit();
			session.close();
		}
		return proNameList;
	}



	@Override
	public void releaseNewPro(Product pro, Integer stock) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			String proName = pro.getProName();
			ProductEntity pe = new ProductEntity();
			session.beginTransaction();
//			pe.setProId(pro.getProId());
			pe.setProName(proName);
			pe.setProCategory(pro.getProCategory());
			pe.setImgUrl(pro.getImgUrl());
			pe.setProDes(pro.getProDes());
			pe.setPrice(pro.getPrice());			
			pe.setProLink(pro.getProLink());	
			session.save(pe);
			session.getTransaction().commit();
			addStock(proName, stock -1);
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"releaseNewPro", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}			
		}
	}



	@Override
	public void addComment(ProComment comment) throws Exception {
		Session session = sessionFactory.openSession();
		try {			
			ProductCommentEntity pce = new ProductCommentEntity();
			session.beginTransaction();
			pce.setCommentCon(comment.getCommentCon());
			pce.setUserId(comment.getUserId());
			session.save(pce);
			session.getTransaction().commit();
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"addComment", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}			
		}		
	}



	@Override
	public List<ProComment> checkComment() throws Exception {
		Session session = sessionFactory.openSession();
		List<ProComment> pcList = new ArrayList<ProComment>();
		List<ProductCommentEntity> pcelist = null;
		try {

			Query q = session.createQuery("select c from ProductCommentEntity c");
			pcelist  = q.list();
			ProComment pc = null;
			for (ProductCommentEntity pce: pcelist) {
				pc = new ProComment();
				pc.setUserId(pce.getUserId());
				pc.setCommentCon(pce.getCommentCon());
				pcList.add(pc);
			}
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"checkComment", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}			
		}
		return pcList;
	}

		public static void main(String[] args) {
		ProductDAOImpl aa = new ProductDAOImpl();
		List<ProComment> list  = null;
		try {
			ProComment pc = new ProComment();
			pc.setUserId("hewenrui");
			pc.setCommentCon("要是能便宜点就更好了╮(╯▽╰)╭");
			aa.addComment(pc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


}
