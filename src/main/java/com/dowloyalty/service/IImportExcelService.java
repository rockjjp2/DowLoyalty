package com.dowloyalty.service;


import java.util.List;

import com.dowloyalty.entity.Farmer;
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
    
    /**
     * 根据手机号和项目id查找相应的农户
     * @param mobile  手机号
     * @param projectId  项目id
     * @return  农户
     */
    public Farmer findByMobileAndProjectId(String mobile, int projectId);
    
    /**
     * 根据导入人（推广员）id获取销售记录总数
     * @param promoterId  导入人（推广员）id
     * @return  销售记录总数
     */
    public int findMaxFarmerSaleRecordID(int promoterId);
    
    /**
     * 根据项目名和省份获取开放农户入口的项目
     * @param projectName  项目名
     * @param provinceID  省份id
     * @return  项目
     */
    public Project findHaveFarmerByProjectName(String projectName,int provinceID);

}
