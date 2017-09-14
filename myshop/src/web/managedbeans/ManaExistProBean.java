package web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;
import beans.Product;
import business.service.ProductService;

@ManagedBean
@SessionScoped
public class ManaExistProBean {

	private String searchProName;
	private Integer stock;
	private Integer addNum;	
	List<SelectItem>indexProItem;
	List<SelectItem>allProItem;
	List<Product>indexProList;
	List<String>allProList;	
	private String indexPro;
	private String replacePro;
	private String addStockMessage;
	private String changeProMessage;
	
	public String getSearchProName() {
		return searchProName;
	}
	public void setSearchProName(String searchProName) {
		this.searchProName = searchProName;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getAddNum() {
		return addNum;
	}
	public void setAddNum(Integer addNum) {
		this.addNum = addNum;
	}
	public List<SelectItem> getIndexProItem() {
		return indexProItem;
	}
	public void setIndexProItem(List<SelectItem> indexProItem) {
		this.indexProItem = indexProItem;
	}
	public List<SelectItem> getAllProItem() {
		return allProItem;
	}
	public void setAllProItem(List<SelectItem> allProItem) {
		this.allProItem = allProItem;
	}
	public List<Product> getIndexProList() {
		return indexProList;
	}
	public void setIndexProList(List<Product> indexProList) {
		this.indexProList = indexProList;
	}
	public List<String> getAllProList() {
		return allProList;
	}
	public void setAllProList(List<String> allProList) {
		this.allProList = allProList;
	}
	public String getIndexPro() {
		return indexPro;
	}
	public void setIndexPro(String indexPro) {
		this.indexPro = indexPro;
	}
	public String getReplacePro() {
		return replacePro;
	}
	public void setReplacePro(String replacePro) {
		this.replacePro = replacePro;
	}
	public String getAddStockMessage() {
		return addStockMessage;
	}
	public void setAddStockMessage(String addStockMessage) {
		this.addStockMessage = addStockMessage;
	}
	public String getChangeProMessage() {
		return changeProMessage;
	}
	public void setChangeProMessage(String changeProMessage) {
		this.changeProMessage = changeProMessage;
	}
	
	public ManaExistProBean(){
		this.addStockMessage = null;
		this.changeProMessage = null;
		this.refresh();
	}
	
	public void refresh(){
		try {
			ProductService service = Factory.createProductService();
			indexProItem = new ArrayList<SelectItem>();			
			allProItem = new ArrayList<SelectItem>();		
			indexProList = service.getIndexProducts();
			allProList = service.getAllProducts();
			indexProItem.add(new SelectItem(indexProList.get(0).getProName(), "��Ʒ1(����)"));
			indexProItem.add(new SelectItem(indexProList.get(1).getProName(), "��Ʒ2(����)"));
			indexProItem.add(new SelectItem(indexProList.get(2).getProName(), "��Ʒ3(�Ҳ�)"));
			indexProItem.add(new SelectItem(indexProList.get(3).getProName(), "��Ʒ4(����)"));
			indexProItem.add(new SelectItem(indexProList.get(4).getProName(), "��Ʒ5(����)"));
			for (String proName : allProList) {
				allProItem.add(new SelectItem(proName));
			}
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " refresh", e.toString());
			ErrorRedirect.reDirect();				
			
		}
		
	}
	
	public String searchPro(){
		this.addStockMessage = null;
		this.changeProMessage = null;
		this.stock  = null;
		try {
			ProductService service = Factory.createProductService();
			this.stock = service.getStock(searchProName);
//			System.out.println(stock);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " searchPro", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();				
			}
			else{
				this.addStockMessage=e.getMessage();
			}
		}
		return "";		
	}
	
	public void chooseAddStockPro(ValueChangeEvent vce){
		this.addStockMessage = null;
		this.changeProMessage = null;
		this.searchProName = (String) vce.getNewValue();
	}
	
	public String addStock(){
		this.addStockMessage = null;
		this.changeProMessage = null;
		try {
			ProductService service = Factory.createProductService();
			service.addStock(searchProName, addNum);
			this.addStockMessage = "��ӿ��ɹ�";
			this.stock = service.getStock(searchProName);
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " addStock", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();				
			}
			else{
				this.addStockMessage=e.getMessage();
			}
		}
		return "";
	}
	
	public void choosePro(ValueChangeEvent vce){
		this.addStockMessage = null;
		this.changeProMessage = null;
		this.indexPro = (String) vce.getNewValue();
	}
	
	public String changePro(){
		this.addStockMessage = null;
		this.changeProMessage = null;
		try {
			if(indexPro.equals(replacePro)){
				throw new Exception("������Ĳ�Ʒ�뵱ǰλ������ʾ�Ĳ�Ʒ��ͬ������");
			}
			Integer proNo = null;
			for (int i = 0; i < 5; i++) {
				if(indexPro.equals(indexProList.get(i).getProName())){
					proNo = i;
					break;
				}
			}
		
			Factory.createProductService().changeIndexProducts(proNo + 1,replacePro);
			this.refresh();
			
			this.changeProMessage = "��Ʒ�����ɹ���";
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " changePro", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();	
				return "errorPage.jsp";
			}
			else{
				this.changeProMessage=e.getMessage();
			}
		}
		return "";		
	}
	
	public String viewExistPro(){
		this.addStockMessage = null;
		this.changeProMessage = null;
		try {
			this.refresh();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " viewExistPro", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();				
			}
			else{
				this.addStockMessage=e.getMessage();
			}
		}
		
		return "manaExistPro.jsp";
	}
	
	
}
