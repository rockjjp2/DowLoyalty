package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.pojo.ProductInfos;

public interface IProductService {
	public List<Product> findAllProduct();
	public List<Product> findProductByPromoterId(int promoterId);
	public List<Product> findProductByProjectId(int projectId);
	public List<Product> findProductByCategroyID(int categroyID);
	public List<Product> findProductByFamilyIdAndProjectId(int projectId,int productFamilyId);
	public List<Product> findProductByCategroyIdOrProjectIdAndPromoterId(int categroyID,int projectId,int promoterId);
	public List<Product> findProductByFamilyIdOrProjectIdAndPromoterId(int familyId,int projectId,int promoterId);
	public List<ProductFamily> findAllProductFamily();
	public List<ProductFamily> findProductFamilyByPromoterId(int promoterId);
	public List<ProductFamily> findProductFamilyByProjectId(int projectId);
	public List<ProductFamily> findProductFamilyByCategroyID(int categroyID);
	public List<ProductFamily> findProductFamilyByCategroyIdAndProjectId(int categroyId,int projectId);
	public List<ProductFamily> findProductFamilyByCategroyIdOrProjectIdAndPromoterId(int categroyId,int projectId,int promoterId);
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
	public List<Product> findProductByFamilyId(int id);
	
	/**
	 * 根据项目id获取所有产品相关信息
	 * @param projectId	项目id
	 * @return	产品信息集合
	 */
	public List<ProductInfos> findProductInfosByProjectId(int projectId);
	
	/**
	 * 根据id获取产品信息
	 * @param id  产品id
	 * @return  产品对象
	 */
	public Product findById(int id);
}
