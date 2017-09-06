package business.service;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import dao.ProductDAO;
import resource.Factory;
import resource.MyShopLogger;
import beans.ProComment;
import beans.Product;

public class ProductServiceImpl implements ProductService {

	@Override
	public String checkProduct(String proName) throws Exception {
		String isExist = null;
		try {

			Product pro = Factory.createProductDAO().getProductWithoutId(proName);
			if(pro == null){
				isExist = "Not Found";
			}
			else {
				isExist ="Found";
			}

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "checkProduct",
					exception.getMessage());
			throw exception;
		}
		return isExist;
	}

	@Override
	public Product getNewProduct(String proName) throws Exception {
		Product pro = null;
		try {
			pro = Factory.createProductDAO().getProductWithoutId(proName);
			if(pro == null){
				throw new Exception("产品名称有误");
			}			

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "getNewProduct",
					exception.getMessage());
			throw exception;
		}
		return pro;
	}

	@Override
	public List<Product> getProductsWithId(String proName, Integer proNum)
			throws Exception {
		try {

			return Factory.createProductDAO().getProductsWithId(proName,proNum);

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "getProductsWithId",
					exception.getMessage());
			throw exception;
		}
	}

	@Override
	public String doPurchase(List<Product> prolist, String userId) throws Exception {
		try {
			return Factory.createProductDAO().doPurchase(prolist, userId);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "doPurchase",
					e.getMessage());
			throw e;
		}


	}

	@Override
	public void addToCart(List<Product> prolist, String userId)
			throws Exception {		
		try {
			OrderDAO dao = Factory.createOrderDAO();
			List<Product> existPro = dao.checkCart(userId);
			if(existPro.size() + prolist.size() > 20){
				throw new Exception("购物车内商品不能超过20件");
			}
			else {
				dao.addToCart(prolist, userId);
			}

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "addToCart",
					e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Product> getIndexProducts() throws Exception {
		List<String> proIdList = new ArrayList<String>();
		try {
			proIdList.add("1");
			proIdList.add("2");
			proIdList.add("3");
			proIdList.add("4");
			proIdList.add("5");
			return Factory.createProductDAO().getproductById(proIdList);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getIndexProducts",
					e.getMessage());
			throw e;
		}
	}

	@Override
	public Integer getStock(String proName) throws Exception {
		try {
			return Factory.createProductDAO().getStock(proName);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getIndexProducts",
					e.getMessage());
			throw e;
		}
	}

	@Override
	public void addStock(String proName, Integer stock) throws Exception {
		try {
			Factory.createProductDAO().addStock(proName, stock);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "addStock",
					e.getMessage());
			throw e;
		}

	}

	@Override
	public void changeIndexProducts(Integer proNo, String proName)
			throws Exception {
		try {
			ProductDAO dao = Factory.createProductDAO();
			Product pro = dao.getProductWithoutId(proName);
			dao.changeIndexProducts(proNo, pro);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "changeIndexProducts",
					e.getMessage());
			throw e;
		}

	}

	@Override
	public List<String> getAllProducts() throws Exception {
		List<String>proNameList = null;
		try {
			proNameList = Factory.createProductDAO().getAllProducts();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getAllProducts",
					e.getMessage());
			throw e;
		}
		return proNameList;
	}

	@Override
	public void releaseNewPro(Product pro, Integer stock) throws Exception {
		try {
			Factory.createProductDAO().releaseNewPro(pro, stock);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "releaseNewPro",
					e.getMessage());
			throw e;
		}

	}

	@Override
	public void addComment(ProComment comment) throws Exception {
		try {
			Factory.createProductDAO().addComment(comment);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "addComment",
					e.getMessage());
			throw e;
		}
		
	}

	@Override
	public List<ProComment> checkComment() throws Exception {
		try {
			return Factory.createProductDAO().checkComment();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "checkComment",
					e.getMessage());
			throw e;
		}
		
	}



}
