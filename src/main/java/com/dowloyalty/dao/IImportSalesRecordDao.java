package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Farmer;
import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.RetailerProvince;
/**
 * 
 * @author xiafang
 *
 */
public interface IImportSalesRecordDao {

	/**
	 * 推广员插入未参加活动的销售记录
	 * @param retailerId
	 * @param productId
	 * @param totalPrice
	 * @param importerId
	 * @param submitDate
	 */
	public void addSaleRecordNoPoint(@Param(value="retailerid")int retailerId,@Param(value="productid")int productId,@Param(value="totalprice")double totalPrice,@Param(value="importerid")int importerId,@Param(value="submitdate")String submitDate,@Param("amount")int amount,@Param("status")boolean status);
	
	/**推广员插入参加活动的销售记录
	 * insert salerecord
	 * @param retailerId
	 * @param productSpecificationId
	 * @param amount
	 * @param totalPrice
	 * @param importerId
	 * @param submitDate
	 * @param projectId
	 * @param points
	 */
	public void addSaleRecord(@Param(value="retailerid")int retailerId,@Param(value="productid")int productId,@Param(value="totalprice")double totalPrice,@Param(value="importerid")int importerId,@Param(value="submitdate")String submitDate,@Param(value="projectid")int projectId,@Param(value="points")int points,@Param("amount")int amount,@Param("status")boolean status);
	
	//管理员插入销售记录
	public void addSaleRecordNoPointByAdmin(@Param(value="retailerid")int retailerId,@Param(value="productid")int productId,@Param(value="totalprice")double totalPrice,@Param(value="submitdate")String submitDate,@Param("amount")int amount,@Param("status")boolean status);
	public void addSaleRecordByAdmin(@Param(value="retailerid")int retailerId,@Param(value="productid")int productId,@Param(value="totalprice")double totalPrice,@Param(value="submitdate")String submitDate,@Param(value="projectid")int projectId,@Param(value="points")int points,@Param("amount")int amount,@Param("status")boolean status);

	/**
	 * select project by project name and promoterId
	 * @param projectName
	 * @return
	 */
	public Project findByProjectName(@Param("projectName")String projectName,@Param("provinceID")int provinceID);
	
	
	/**
	 * 
	 * @param familyName
	 * @return
	 */
	public ProductFamily findByProductFamilyName(String familyName);
	
	/**
	 * 
	 * @param productName
	 * @return
	 */
	public Product findByProductName(String productName);
	
	public ProductCategory findByProductCategoryName(String categoryName);
	
	/**
	 * find retailer by retailer name and projectId
	 * @param retailerName
	 * @return
	 */
	public Retailer findByRetailerName(@Param("retailerName")String retailerName,@Param("projectId")int projectId);
	
	public int findProvIdByPromId(int promoterId);
	
	public Retailer findRetailerByProvId(@Param("provinceId")int provinceId,@Param("retailerName")String retailerName);
	
	/**
	 * find Product by specification,product name and category name
	 * @param productFamilyName
	 * @param productName
	 * @param categoryName
	 * @return
	 */
	public Product findProductIDByName(@Param(value="productname") String productName,@Param(value="productfamilyname") String productFamilyName,@Param(value="categoryname") String categoryName);
	
	public Product findProductIDByNameAndProjId(@Param(value="productname") String productName,@Param(value="productfamilyname") String productFamilyName,@Param(value="categoryname") String categoryName,@Param("projectId")int projectId);
	/**
	 * find points by projectID and specificationID
	 * @param projectId
	 * @param specificationId
	 * @return
	 */
	public float findPointById(@Param(value="projectid")int projectId,@Param(value="productid")int productId);
	
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
    public Farmer findByMobileAndProjectId(@Param("Mobile")String mobile, @Param("ProjectID")int projectId);
    
    
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
    public Project findHaveFarmerByProjectName(@Param("projectName")String projectName,@Param("provinceID")int provinceID);
}
