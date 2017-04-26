package com.dowloyalty.service;


import java.util.List;


import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.RetailerProvince;

public interface IImportExcelService {

	/**
	 * insert
	 * @param importerId 
	 * @param submitDate
	 */
	public void addSaleRecordNoPoint(int retailerId,int productId,double totalPrice,int importerId,String submitDate,int amount);
	public void addSaleRecord(int retailerId,int productId,double totalPrice,int importerId,String submitDate,int projectId,int points,int amount);
	
	public void addSaleRecordNoPointByAdmin(int retailerId,int productId,double totalPrice,String submitDate,int amount,boolean status);
	public void addSaleRecordByAdmin(int retailerId,int productId,double totalPrice,String submitDate,int projectId,int points,int amount,boolean status);
	
	public Project findByProjectName(String projectName,int provinceID);
	public int findProvIdByPromId(int promoterId);
	public Retailer findByRetailerName(String retailerName,int projectId);
	public Retailer findRetailerByProvId(int provinceId,String retailerName);
	public Product findProductIDByName(String productName,String productFamilyName,String categoryName);
	public Product findProductIDByNameAndProjId(String productName,String productFamilyName,String categoryName,int projectId);
	public float findPointById(int projectId,int productId);
	public ProductCategory findByProductCategoryName(String categoryName);
	public ProductFamily findByProductFamilyName(String familyName);
	public Product findByProductName(String productName);
	public int findMaxSaleRecordID(int promoterId);
	
	
	
	public List<Project> findProject(int provinceId);
	public List<RetailerProvince> findRetailer();
    public List<RetailerProvince> findRetailerLikeName(String retailerName);
    public List<RetailerProvince> findRetailerByProject(int projectId);
    public List<ProductCategory> findProductcategory();
    public List<ProductFamily> findProductFamily();
    public List<Product> findProduct();
    public List<Product> findProductByProject(int projectId);
    public List<ProductFamily> findProductFamilyByCName(String categoryName);
    public List<Product> findProductByFName(String familyName);

}
