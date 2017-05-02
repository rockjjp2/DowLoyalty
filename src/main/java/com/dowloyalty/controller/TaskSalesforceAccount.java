package com.dowloyalty.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.Account;
import com.dowloyalty.pojo.AccountMapping;
import com.dowloyalty.service.IAdminService;
import com.dowloyalty.service.IProvinceService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.utils.SimpleHttpConnectUtil;

@Controller
public class TaskSalesforceAccount {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private static Timer task = null;
	private static boolean start = false;
	@Resource
	IAdminService iAdminService;
	@Resource
	IProvinceService iProvinceService;
	@Resource
	IRetailerService iRetailerService;
	

	/*同步零售商信息*/
	@ResponseBody
	@RequestMapping("/website/synchronizeRetailerInfo")
	public void synchronizeInfo()
	{
		if(start)
		{
			System.out.println("============定时器已在运行，禁止重复开启==========");
			return;
		}
		task = new Timer();
		task.schedule(new TimerTask() {
					
					@Override
					public void run() {
						start = true;
						salesForec();
						start = false;
						System.out.println("============任务完毕，终止定时器==========");
						task.cancel();
					}
				}, 1000, 1000*60*3);
	}
	
	/**
	 * 定时从salesforce系统获取用户信息
	 */
	public void salesForec() {
		System.out.println("开始同步=========================");
		logger.info("数据库开始同步零售商数据");
		JSONObject jsonAuthority = getAuthority();
		String token_type=jsonAuthority.getString("token_type")+" ";
		String instance_url=jsonAuthority.getString("instance_url");
		String salesforceToken=jsonAuthority.getString("access_token");
		String url=instance_url+"/services/data/v29.0/query";
		List<Account> accounts = new ArrayList<>();
		boolean isContinue=false;
		HttpClient httpclient=new HttpClient();
		GetMethod httpGet;
		
		NameValuePair[] params = new NameValuePair[1];  
		params[0]=new NameValuePair("q", getSql());
		while (!isContinue) {
			try {
				httpGet=new GetMethod(url);
				httpGet.setRequestHeader("Authorization", token_type+salesforceToken);
				httpGet.setQueryString(params);
				httpclient.executeMethod(httpGet);
				System.out.println("code:"+httpGet.getStatusCode());
				if (httpGet.getStatusCode()==HttpStatus.SC_OK) {
					String result=httpGet.getResponseBodyAsString();
					JSONObject jsonObject = JSON.parseObject(result);
					
					isContinue=jsonObject.getBoolean("done");
					url=instance_url+jsonObject.getString("nextRecordsUrl");
					List<Account> array = JSONArray.parseArray(jsonObject.getString("records"),Account.class);
					accounts.addAll(array);
				}
			} catch (IOException e) {
				logger.warn("接受retailer数据请求时发生异常",e);
			}
		}
		//将获取的信息接受处理后存入account表
		List<AccountMapping> accountMappings=AccountToAccountMapping(accounts);
		for (AccountMapping accountMapping : accountMappings) {
			iAdminService.insertAccountMapping(accountMapping);
		}
		//将account表数据中新数据写入retailer
		updateRetailerByAccount();
		System.out.println("同步结束=========================");
		logger.info("从salesforce获取Account更新数目"+accountMappings.size());
	}
	/**
	 * 获得salesforce接入的系统授权，信息包裹在JsonObject中
	 * @return access_token,instance_url,id,token_type,issued_at,signature
	 */
	private JSONObject getAuthority() {
		//get basic info,such as token_type,instance_url,salesforceToken
				String URL = "https://login.salesforce.com";
				String CLIENT_ID ="3MVG99OxTyEMCQ3hGN0eh27w.HJW9onLbEVKy2SjW5bIA05IufOKxNhCxxnkl7aNbi9NlGRnCLOipQ06FgwWD";
				String CLIENT_SECRET = "5161380403841450655";
				String USERNAME = "mjhuang@dow.com.wechat";
				String PASSWORD = "GCWeChat@2017sCf1MbnskKLfGlnKSrvvD4SN";
				String POSTURL = URL+"/services/oauth2/token?grant_type=password"
						+ "&client_id="+ CLIENT_ID+ "&client_secret="+ CLIENT_SECRET
						+ "&username="+ USERNAME+ "&password="+ PASSWORD;
				return JSON.parseObject(SimpleHttpConnectUtil.getInstance().sendPost(POSTURL, null));
	}
	private String getSql() {
		String rawSql="select Id, Name, ShippingState, Phone, lastmodifiedDate from account "
				+ "where recordtypeId ='012300000019Q9y'and LastModifiedDate > ";
		String time="2016-02-20T04:50:01.000Z";
		//在有数据时进行校验
		String lastModifiedDate=iAdminService.findLastModifiedDate();
		if (lastModifiedDate != null) {
			time=DateUTC2DateZ(lastModifiedDate,true);
		}
		logger.info("与Salesforce请求数据，SQL语句为："+rawSql+time);
		return rawSql+time;
	}
	/**
	 * 将原始数据转换成数据库映射数据
	 * @param accounts
	 * @return
	 */
	private List<AccountMapping> AccountToAccountMapping(List<Account> accounts) {
		List<AccountMapping> accountMappings = new ArrayList<>();
		for (Account account : accounts) {
			AccountMapping e=new AccountMapping();
			e.setId(account.getId());
			e.setName(account.getName());
			e.setPhone(account.getPhone());
			e.setShippingState(account.getShippingState());
			e.setUrl(account.getAttributesUrl());
			e.setLastModifiedDate(DateZ2DateUTC(account.getLastModifiedDate()));
			accountMappings.add(e);
		}
		return accountMappings;
	}
	/**
	 * 数据更新程序
	 * @param accountMappings
	 */
	private void updateRetailerByAccount() {
		//找到已存在的数据
		List<AccountMapping> updateAccounts = iAdminService.findUpdateAccount();
		for (AccountMapping accountMapping : updateAccounts) {
			Retailer retailer = AccountMapping2Retailer(accountMapping);
			iRetailerService.updateRetailerByRetailer(retailer);
			//数据状态做更改，标记已处理
			iAdminService.releaseAccount(retailer.getSfdcCode());
		}
		//在上一步的基础上，数据为未处理的为全新，则直接写入
		List<AccountMapping> newAccounts = iAdminService.findNewAccount();
		for (AccountMapping accountMapping : newAccounts) {
			Retailer retailer = AccountMapping2Retailer(accountMapping);
			iRetailerService.insertRetailer(retailer);
			//数据状态做更改，标记已处理
			iAdminService.releaseAccount(retailer.getSfdcCode());
		}
	}
	private String  DateZ2DateUTC(String DateZ) {
		//DateZ ="2016-08-9T10:01:54.123Z";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		SimpleDateFormat formatSQL=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		String dateSql = null;
		try {
			d = format.parse(DateZ.replace("Z", " UTC"));
			dateSql=formatSQL.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}//注意是空格+UTC
		return dateSql.toString();
	}
	/**
	 * 标准Date格式转变为DateZ格式
	 * @param DateUTC
	 * @param timeAddOne
	 * @return
	 */
	private String DateUTC2DateZ(String DateUTC,boolean timeAddOne) {
		//DateUTC="2017-02-23 17:25:28.0";
		SimpleDateFormat formatZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat formatSQL=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = formatSQL.parse(DateUTC);
			if (timeAddOne) {
				parse=new Date(parse.getTime()+1000);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formatZ.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String DateZ = formatZ.format(parse);
		logger.info("DateUTC2DateZ,DateUTC："+DateUTC+"DateZ"+DateZ);
		return DateZ;
	}
	
	/*account转为retailer信息*/
	private Retailer AccountMapping2Retailer(AccountMapping accountMapping){
		Retailer retailer=new Retailer();
		retailer.setChineseName(accountMapping.getName());
		retailer.setEmail(accountMapping.getUrl());
		retailer.setLastUpdataData(Timestamp.valueOf(accountMapping.getLastModifiedDate()));
		retailer.setMobile(accountMapping.getPhone());
		String loginCode = null;
		try {
			loginCode = EnCode(accountMapping.getId());
		} catch (UnsupportedEncodingException e) {
			loginCode=accountMapping.getId().substring(6);
		}
		retailer.setLoginCode(loginCode);//---------------zanshi
		retailer.setSfdcCode(accountMapping.getId());
		retailer.setProvinceID(iProvinceService.findProvinceIdByEnName(accountMapping.getShippingState()));
		//retailer.set
		return retailer;
		
	}
	private String EnCode(String in) throws UnsupportedEncodingException{
		String rawStri=Base64.getEncoder().encodeToString(in.substring(10).getBytes("utf-8"));
		rawStri=rawStri.substring(0, rawStri.length()-4);
		String Random=createRandomCharData(4);
		return rawStri+Random;
	}
	//根据指定长度生成字母和数字的随机数
    //0~9的ASCII为48~57
    //A~Z的ASCII为65~90
    //a~z的ASCII为97~122
    private String createRandomCharData(int length)
    {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();//随机用以下三个随机生成器
        Random randdata=new Random();
        int data=0;
        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch(index)
            {
            case 0:
                 data=randdata.nextInt(10);//仅仅会生成0~9
                 sb.append(data);
                break;
            case 1:
                data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
                sb.append((char)data);
                break;
            case 2:
                data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
                sb.append((char)data);
                break;
            }
        }
        return sb.toString();
    }
}
