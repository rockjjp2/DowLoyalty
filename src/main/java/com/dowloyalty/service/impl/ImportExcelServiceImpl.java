package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IImportSalesRecordDao;
import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.RetailerProvince;
import com.dowloyalty.service.IImportExcelService;
/**
 * insert into salerecord serviceImpl
 * @author xiafang
 *
 */
@Service
public class ImportExcelServiceImpl implements IImportExcelService{

	@Resource
	private IImportSalesRecordDao iImportSalesRecordDao;
	
	
	@Override
	public void addSaleRecordNoPoint(int retailerId, int productId, double totalPrice, int importerId,String submitDate,int amount) {
		// TODO Auto-generated method stub
		iImportSalesRecordDao.addSaleRecordNoPoint(retailerId, productId, totalPrice, importerId, submitDate,amount,false);
	}
	
	@Override
	public void addSaleRecord(int retailerId, int productId, double totalPrice, int importerId,
			String submitDate, int projectId, int points,int amount) {
		
		iImportSalesRecordDao.addSaleRecord(retailerId, productId,totalPrice, importerId, submitDate, projectId, points, amount,false);
		
	}
	
	@Override
	public Project findByProjectName(String projectName,int provinceID){
		
		return iImportSalesRecordDao.findByProjectName(projectName,provinceID);
	}
	
	@Override
	public Retailer findByRetailerName(String retailerName,int projectId) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findByRetailerName(retailerName,projectId);
	}
	
	@Override
	public Retailer findRetailerByProvId(int provinceId, String retailerName) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findRetailerByProvId(provinceId, retailerName);
	}

	
	@Override
	public Product findProductIDByName(String productName,String productFamilyName,String categoryName){
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProductIDByName(productName, productFamilyName, categoryName);
	}
	
	@Override
	public Product findProductIDByNameAndProjId(String productName, String productFamilyName, String categoryName,
			int projectId) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProductIDByNameAndProjId(productName, productFamilyName, categoryName, projectId);
	}

	
	@Override
	public float findPointById(int projectId, int productId) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findPointById(projectId, productId);
	}
	
	@Override
	public ProductCategory findByProductCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findByProductCategoryName(categoryName);
	}

	@Override
	public ProductFamily findByProductFamilyName(String familyName) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findByProductFamilyName(familyName);
	}

	@Override
	public Product findByProductName(String productName) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findByProductName(productName);
	}
	
	@Override
	public int findMaxSaleRecordID(int promoterId) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findMaxSaleRecordID(promoterId);
	}

	
	@Override
	public List<Project> findProject(int provinceId) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProject(provinceId);
	}
	@Override
	public List<RetailerProvince> findRetailer() {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findRetailer();
	}
	
    public List<RetailerProvince> findRetailerLikeName(String retailerName){
    	return iImportSalesRecordDao.findRetailerLikeName(retailerName);
    }
    
	@Override
	public List<RetailerProvince> findRetailerByProject(int projectId) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findRetailerByProject(projectId);
	}
	@Override
	public List<ProductCategory> findProductcategory() {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProductcategory();
	}
	@Override
	public List<ProductFamily> findProductFamilyByCName(String categoryName) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProductFamilyByCName(categoryName);
	}
	@Override
	public List<Product> findProductByFName(String familyName) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProductByFName(familyName);
	}

	public List<Product> findProductByProject(int projectId){
		return iImportSalesRecordDao.findProductByProject(projectId);
	}
	
	@Override
	public List<ProductFamily> findProductFamily() {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProductFamily();
	}

	@Override
	public List<Product> findProduct() {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProduct();
	}

	@Override
	public int findProvIdByPromId(int promoterId) {
		// TODO Auto-generated method stub
		return iImportSalesRecordDao.findProvIdByPromId(promoterId);
	}

	@Override
	public void addSaleRecordNoPointByAdmin(int retailerId, int productId, double totalPrice, String submitDate,
			int amount, boolean status) {
		// TODO Auto-generated method stub
		iImportSalesRecordDao.addSaleRecordNoPointByAdmin(retailerId, productId, totalPrice, submitDate, amount, status);
	}

	@Override
	public void addSaleRecordByAdmin(int retailerId, int productId, double totalPrice, String submitDate, int projectId,
			int points, int amount, boolean status) {
		// TODO Auto-generated method stub
		iImportSalesRecordDao.addSaleRecordByAdmin(retailerId, productId, totalPrice, submitDate, projectId, points, amount, status);
	}

	
	
	


	
	
	
	
}
