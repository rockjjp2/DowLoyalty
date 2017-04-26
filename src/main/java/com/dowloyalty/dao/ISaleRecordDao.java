package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.SaleRecord;
import com.dowloyalty.pojo.webSale;
import com.dowloyalty.pojo.PojoSaleRecord;
/**
 * 
 * @author wangyuanjie
 *
 */
import com.dowloyalty.pojo.SendSaleRecord;
public interface ISaleRecordDao {

	/**
	 * 显示销售记录
	 * @return
	 */
	/*public List<SaleRecord> selectSalesRecord(@Param("startindex")int startIndex,@Param("pagesize")int pageSize);*/
	
	//public List<webSale> selectSalesRecord(@Param("promoterId")int promoterId,@Param("startindex")int startIndex,@Param("pagesize")int pageSize);
	public List<webSale> findNewInsertSalesRecord(@Param("id")int id,@Param("promoterId")int promoterId);

	
	public List<PojoSaleRecord> selectWeChatSaleRecords(int promoterId);
	
	/**
	 * 获取所有需要发送到saleforce的销售记录
	 * @return	销售记录集合
	 */
	public List<SendSaleRecord> findAllSaleRecord();
	
	/**
	 * 修改销售记录发送sfdc状态并记录oppId
	 * @param saleRecord	销售记录对象
	 */
	public void update(SaleRecord saleRecord);
	
	/**
	 * 根据销售记录id获取销售记录信息
	 * @param id	销售记录id
	 * @return	销售记录对象
	 */
	public SaleRecord findById(int id);
	
//	/**
//	 * 获取所有需要发送到saleforce的指定时间以后的销售记录总数
//	 * @param time	指定时间
//	 * @return	销售记录总数
//	 */
//	public int getCount(@Param("time")String time);
//	/**
//	 * 根据零售商id或者姓名获取所有销售记录并分页显示
//	 * @param id	零售商id
//	 * @param name	零售商姓名
//	 * @param PageNum	页码数
//	 * @param ShowNum	每页显示兑换记录条数
//	 * @return	所有销售记录
//	 */
//	public List<SearchSaleRecords> findByConditions(@Param("retailerId")String id, @Param("retailerName")String name,@Param("PageNum")int PageNum,@Param("ShowNum")int ShowNum);
	
//	/**
//	 * 根据零售商id或者姓名获取销售记录总数
//	 * @param id	零售商id
//	 * @param name	零售商姓名
//	 * @return	销售记录总数
//	 */
//	public int getCount(@Param("retailerId")String id, @Param("retailerName")String name);
}
