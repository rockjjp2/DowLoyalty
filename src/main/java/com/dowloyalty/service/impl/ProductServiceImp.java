package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IProductDao;
import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.pojo.ProductInfos;
import com.dowloyalty.service.IProductService;

@Service
public class ProductServiceImp implements IProductService {
@Resource
IProductDao iProductDao;

		@Override
		public List<Product> findProductByPromoterId(int promoterId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductByPromoterId(promoterId);
		}
		
		@Override
		public List<Product> findProductByProjectId(int projectId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductByProjectId(projectId);
		}
		
		@Override
		public List<ProductFamily> findProductFamilyByPromoterId(int promoterId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductFamilyByPromoterId(promoterId);
		}
		
		@Override
		public List<ProductFamily> findProductFamilyByProjectId(int projectId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductFamilyByProjectId(projectId);
		}
		
		@Override
		public List<ProductCategory> findProductCategoryByPromoterId(int promoterId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductCategoryByPromoterId(promoterId);
		}
		
		@Override
		public List<ProductCategory> findProductCategoryByProjectId(int projectId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductCategoryByProjectId(projectId);
		}

		@Override
		public List<Product> findProductByCategroyID(int categroyID) {
			// TODO Auto-generated method stub
			return iProductDao.findProductByCategroyID(categroyID);
		}

		@Override
		public List<ProductFamily> findProductFamilyByCategroyID(int categroyID) {
			// TODO Auto-generated method stub
			return iProductDao.findProductFamilyByCategroyID(categroyID);
		}

		@Override
		public List<Product> findProductByCategroyIdOrProjectIdAndPromoterId(int categroyID, int projectId,
				int promoterId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductByCategroyIdOrProjectIdAndPromoterId(categroyID, projectId, promoterId);
		}

		@Override
		public List<ProductFamily> findProductFamilyByCategroyIdOrProjectIdAndPromoterId(int categroyId, int projectId,
				int promoterId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductFamilyByCategroyIdOrProjectIdAndPromoterId(categroyId, projectId, promoterId);
		}

		@Override
		public List<Product> findProductByFamilyIdOrProjectIdAndPromoterId(int familyId, int projectId,
				int promoterId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductByFamilyIdOrProjectIdAndPromoterId(familyId, projectId, promoterId);
		}

		@Override
		public List<ProductCategory> findAllProductCategory() {
			return iProductDao.findAllProductCategory();
		}

		@Override
		public List<Product> findProductByFamilyId(int id) {
			return iProductDao.findProductByFamilyId(id);
		}

		@Override
		public List<ProductInfos> findProductInfosByProjectId(int projectId) {
			return iProductDao.findProductInfosByProjectId(projectId);
		}

		@Override
		public List<Product> findAllProduct() {
			// TODO Auto-generated method stub
			return iProductDao.findAllProduct();
		}

		@Override
		public List<ProductFamily> findAllProductFamily() {
			// TODO Auto-generated method stub
			return iProductDao.findAllProductFamily();
		}

		@Override
		public List<ProductFamily> findProductFamilyByCategroyIdAndProjectId(int categroyId, int projectId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductFamilyByCategroyIdAndProjectId(categroyId, projectId);
		}

		@Override
		public List<Product> findProductByFamilyIdAndProjectId(int projectId, int productFamilyId) {
			// TODO Auto-generated method stub
			return iProductDao.findProductByFamilyIdAndProjectId(projectId, productFamilyId);
		}
	
}
 