package com.dowloyalty.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONTokener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.dowloyalty.entity.SaleRecord;
import com.dowloyalty.pojo.SendSaleRecord;
import com.dowloyalty.service.ISalesRecordService;
import com.dowloyalty.utils.SimpleHttpConnectUtil;

@Controller
//@RequestMapping("/api")
public class SendToSaleForceController {
	private static String URL = "https://login.salesforce.com";
	private static String CLIENT_ID ="3MVG99OxTyEMCQ3hGN0eh27w.HJW9onLbEVKy2SjW5bIA05IufOKxNhCxxnkl7aNbi9NlGRnCLOipQ06FgwWD";
	private static String CLIENT_SECRET = "5161380403841450655";
	private static String USERNAME = "mjhuang@dow.com.wechat";
	private static String PASSWORD = "GCWeChat@2017sCf1MbnskKLfGlnKSrvvD4SN";
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	ISalesRecordService iSalesRecordService;

	
	/**
	 * 定时将销售记录发送至salesforce
	 */
	@Scheduled(cron="0 0 1 * * *")
	public void sendSaleRecordJson() {
		
		String access_token = "";
		JSONObject tokenJson = null;
		String postBody = "";
		Map<String , Object> map = null;
		int insertCount = 0;
		//初次连接，获取授权token和start_time
		try {
			tokenJson = new JSONObject(new JSONTokener(getToken()));
			access_token = tokenJson.get("access_token").toString();
		} catch (JSONException e) {
			//写到log里面
			logger.warn("获取token时，json数据转换异常");
		}
		
		List<SendSaleRecord> allSaleRecords = iSalesRecordService.findAllSaleRecord();
		for(SendSaleRecord record : allSaleRecords)
		{
				//组合销售记录的json数据
				String closeDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(record.getSubmitDate().getTime()));
				String CreatedDate = record.getSubmitDate().toLocalDateTime().toString();
				postBody = "{\"Name\":\""+ record.getSaleRecordId() +
							"\",\"Hybrid_Name__c\":\""+ record.getProductId() +
							"\",\"StageName\":\"Closed Won\",\"RecordTypeId\":\"012300000019QA9\"," +
							"\"AccountId\":\""+ record.getRetailerId() +
			              	"\",\"CloseDate\":\""+ closeDate +
			              	"\",\"Price_per_bag__c\":\""+ record.getTotalPrice() +
			              	"\",\"Bags_units__c\":\""+ record.getAmount() +
			              	"\",\"CreatedDate\":\""+ CreatedDate +"\"}";
				
				
				try {
					//携带token和销售记录数据访问链接并获取返回值oppId
					map = createOpp(access_token,postBody);
					if(insertCount == 0)
					{
						logger.debug("Start to insert sale records! Start time-->"+ new Date());
					}
					else if(insertCount == (allSaleRecords.size() - 1))
					{
						logger.debug("End insert sale records! End time-->"+ new Date());
					}
					
					insertCount++;
					
					SaleRecord saleRecord = iSalesRecordService.findById(record.getSaleRecordId());
					
					//若状态为success
					if((boolean) map.get("success"))
					{	
						saleRecord.setStatus(true);
						saleRecord.setOppId(map.get("oppId").toString());
						iSalesRecordService.update(saleRecord);
					}
					
				} catch (Exception e) {
					//写进log
					logger.warn("访问saleforce接口异常");
				}
		}
		System.out.println("total number ="+ insertCount);
	}
	
	/**
	 * 获取token
	 * @return	token字符串
	 */
	public static String getToken() {
		String token = null;
		String POSTURL = URL+"/services/oauth2/token?grant_type=password"
				+ "&client_id="+ CLIENT_ID+ "&client_secret="+ CLIENT_SECRET
				+ "&username="+ USERNAME+ "&password="+ PASSWORD;
		token = SimpleHttpConnectUtil.getInstance().sendPost(POSTURL, null);
		return token;
	}
	
	
	/*发送数据至salesforce并获取返回oppid*/
	public static Map<String, Object> createOpp(String token,String postBody) throws Exception{
	       
		   Map<String, Object> map = new HashMap<String ,Object>();
		   String oppId = "";

	       HttpClient httpclient = new HttpClient();
	       
	       PostMethod post = new PostMethod("https://das.my.salesforce.com/services/data/v29.0/sobjects/Opportunity");
	       
	       post.setRequestHeader("Authorization", "Bearer " + token);
	       
	       post.setRequestEntity(new StringRequestEntity(postBody,
	              "application/json;charset=utf-8", null));

	       System.out.println("post :" + postBody);
	       
	       try {
	           int status = httpclient.executeMethod(post);
	           System.out.println("status :" + status);
	           map.put("status", status);
	           if(status == 201) {
	        	   JSONObject response = new JSONObject(
	                        new JSONTokener(post.getResponseBodyAsString()));
	              System.out.println("response :" + response);
	              boolean success = response.getBoolean("success");
	             // String error = response.get("error").toString();
	              map.put("success", success);
	             // map.put("error", error);
	              if(success) {
	                  oppId = response.getString("id");
	                  map.put("oppId", oppId);
	                  System.out.println("Id :" + oppId);
	              }
	              
	           }
	           
	       } finally {
	           post.releaseConnection();
	       }
	       
	       return map;
	    }

	
}
