package web.managedbeans;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import beans.Product;
import business.service.ProductService;
import resource.ErrorRedirect;
import resource.Factory;
import resource.MyShopLogger;

@ManagedBean
@SessionScoped
public class ManaProBean implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private String proName;
	private String proCategory;
	private List<SelectItem>typeList;
	private String proDesc;
	private Double proPrice;
	private String prolink;
	private Part file;
	private Integer newProStock;	
	private String message;
	

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public Double getProPrice() {
		return proPrice;
	}
	public void setProPrice(Double proPrice) {
		this.proPrice = proPrice;
	}
	public String getProlink() {
		return prolink;
	}
	public void setProlink(String prolink) {
		this.prolink = prolink;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	public Integer getNewProStock() {
		return newProStock;
	}
	public void setNewProStock(Integer newProStock) {
		this.newProStock = newProStock;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SelectItem> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<SelectItem> typeList) {
		this.typeList = typeList;
	}
	
	
	public ManaProBean(){		
		try {			
			this.message = null;
			this.typeList = new ArrayList<SelectItem>();
			typeList.add(new SelectItem("�����"));
			typeList.add(new SelectItem("�����"));
			typeList.add(new SelectItem("���"));
			typeList.add(new SelectItem("����"));
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " ManaProBean", e.toString());
			ErrorRedirect.reDirect();						
		}	
	}

	public String addProduct() {
		this.message = null;	
		String fileName = null;
		try {
			 ProductService service =  Factory.createProductService();	
			 if(service.checkProduct(proName).equals("Found")){
				 throw new Exception("��Ʒ���Ѵ���");
			 }
			 
			 fileName = FilenameUtils.getName(getSubmittedFileName(file));
			final Path destination = Paths.get("D:/eclipse/workplace/MyShop/WebContent/page-resource/img/products/" + proName + ".jpg");
//			D:/eclipse/workplace/MyShop/WebContent/page-resource/img/products/
			String imgUrl = "../page-resource/img/products/" + proName + ".jpg";
			InputStream bytes=null;
	         if (file!=null) {
	         	bytes = file.getInputStream();
	           	  Files.copy(bytes, destination);
	         }
	         product = new Product();
	         product.setProName(proName);
	         product.setProCategory(proCategory);
	         product.setImgUrl(imgUrl);
	         product.setPrice(proPrice);	        
	         product.setProDes(proDesc);
	         product.setProLink(prolink);	         
	         product.setSelledDate(null);
	         service.releaseNewPro(product, newProStock);
	         this.message = "��Ʒ��ӳɹ���";
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), " addProduct", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();				
			}
			else{
				if(e.getMessage().contains("D:\\eclipse\\workplace\\")){
					this.message ="��·���´�����Ϊ��\"" + fileName + "\"��ͼƬ";
				}else {
					this.message =e.getMessage();
				}
				
			}
		}
		
		return "";		
	}
	
	public static String getSubmittedFileName(Part filePart)	{

		try {
			 String header = filePart.getHeader("content-disposition");
			    if(header == null){
			    	 return null;
			    }	       
			    for(String headerPart : header.split(";"))
			    {
			        if(headerPart.trim().startsWith("filename"))
			        {
			            return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
			        }
			    }
			
		} catch (Exception e) {
			MyShopLogger.logError("ManaProBean", " getSubmittedFileName", e.toString());
				ErrorRedirect.reDirect();			
			
		}
		 return null;
	   
	}
}
