package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.pojo.ProductInfos;

public interface IProductDao {
	public List<Product> findAllProduct();
	public List<Product> findProductByPromoterId(int promoterId);
	public List<Product> findProductByProjectId(int projectId);
	public List<Product> findProductByCategroyID(int categroyID);
	public List<Product> findProductByFamilyIdAndProjectId(@Param("projectId")int projectId,@Param("productFamilyId")int productFamilyId);
	public List<Product> findProductByCategroyIdOrProjectIdAndPromoterId(@Param("categroyId")int categroyId,
			@Param("projectId")int projectId,@Param("promoterId")int promoterId);
	public List<Product> findProductByFamilyIdOrProjectIdAndPromoterId(@Param("familyId")int familyId,
			@Param("projectId")int projectId,@Param("promoterId")int promoterId);
	public List<ProductFamily> findAllProductFamily();
	public List<ProductFamily> findProductFamilyByPromoterId(int promoterId);
	public List<ProductFamily> findProductFamilyByProjectId(int projectId);
	public List<ProductFamily> findProductFamilyByCategroyID(int categroyID);
	public List<ProductFamily> findProductFamilyByCategroyIdAndProjectId(@Param("categroyId")int categroyId,@Param("projectId")int projectId);
	public List<ProductFamily> findProductFamilyByCategroyIdOrProjectIdAndPromoterId(@Param("categroyId")int categroyId,
			@Param("projectId")int projectId,@Param("promoterId")int promoterId);
	public List<ProductCategory> findProductCategoryByPromoterId(int promoterId);
	public List<ProductCategory> findProductCategoryByProjectId(int projectId);
	
	/**
	 * 获取所有的产品分类
	 * @return	产品分类集合
	 */
	public List<ProductCategory> findAllProductCategory();
	
	/**
	 * 根据产品家族id获取所有的产品
	 * @param	产品家族id
	 * @return	产品分类集合
	 */
	public List<Product> findProductByFamilyId(@Param("ProductFamilyID")int id);
	
	/**
	 * 根据项目id获取所有产品相关信息
	 * @param projectId	项目id
	 * @return	产品信息集合
	 */
	public List<ProductInfos> findProductInfosByProjectId(int projectId);
}
