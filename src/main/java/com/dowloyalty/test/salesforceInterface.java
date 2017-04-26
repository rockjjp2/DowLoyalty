package com.dowloyalty.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.utils.SimpleHttpConnectUtil;

public class salesforceInterface {
@Test
public void earnOauth(){
	String URL = "https://test.salesforce.com";
	String CLIENT_ID ="3MVG9hq7jmfCuKffxV8EIa4PjxS_QOqqUEg.hZVXSMKsFbpneKmcn6JeqmDlK_NsX0cddCWyMxPOmqNCaky0A";
	String CLIENT_SECRET = "5319329570756912986";
	String USERNAME = "mjhuang@dow.com.wechat.full";
	String PASSWORD = "das123qwehyRgEBcWBISAHj0XrYDIHwH1v";
	String POSTURL = URL+"/services/oauth2/token?grant_type=password"
			+ "&client_id="+ CLIENT_ID+ "&client_secret="+ CLIENT_SECRET
			+ "&username="+ USERNAME+ "&password="+ PASSWORD;
	String result=SimpleHttpConnectUtil.getInstance().sendPost(POSTURL, null);
	System.out.println(result);
}
	@Test
	public void salesforce(){
		HttpClient httpclient=new HttpClient();
		GetMethod httpGet;
		String token_type="Bearer ";
		String salesforceTOKEN="00D2C0000000wqK!ARgAQCEz4EP7oXnl8IUyy1lZrK6oThG3e3h08y1C5GOJzOZaLvqPKiVfKVNY4CcQ2qteu14K.8tm0tT4KHALhPS5okIVz0xG";
		String url="https://das--Full.cs59.my.salesforce.com/services/data/v29.0/query";
		
		NameValuePair[] params = new NameValuePair[1];  
		params[0]=new NameValuePair("q", "select Id, Name, ShippingState, Phone, lastmodifiedDate from account "
				+ "where recordtypeId ='012300000019Q9y'and LastModifiedDate > 2016-02-20T04:57:01.000Z");
		
		try {
			httpGet=new GetMethod(url);
			httpGet.setRequestHeader("Authorization", token_type+salesforceTOKEN);
			httpGet.setQueryString(params);
			httpclient.executeMethod(httpGet);
				System.out.println("code:"+httpGet.getStatusCode());
			if (httpGet.getStatusCode()==HttpStatus.SC_OK) {
				String result=httpGet.getResponseBodyAsString();
				JSONObject jsonObject = JSON.parseObject(result);
				jsonObject.getBoolean("done");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void data(){
		String str ="2016-08-9T10:01:54.123Z";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		Date d = null;
		try {
			d = format.parse(str.replace("Z", " UTC"));
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//注意是空格+UTC
	}
	@Test
	public void data2(){
		String DateUTC="2017-02-23 17:25:28.85";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = format.parse(DateUTC);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(parse);
	}
	@Test
	public void data3(){
		String DateUTC="2017-02-23 17:25:28";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parse = null;
		try {
			parse = format.parse(DateUTC);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(parse);
		System.out.println(parse.getTime());
		System.out.println(new Date(parse.getTime()));
		System.out.println(parse.getTime()+1000);
		System.out.println(new Date(parse.getTime()+1000));
	}
	@Test
	public void DateUTC2DateZ() {
		String DateUTC="2017-02-23 17:25:28.0";
		SimpleDateFormat formatZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat formatSQL=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = formatSQL.parse(DateUTC);
			if (false) {
				parse=new Date(parse.getTime()+1000);
				System.out.println("-------------加1s时间："+parse);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formatZ.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String DateZ = formatZ.format(parse);
		System.out.println(DateZ);
	}
	@Test
	public void DateUTC2D() {
		int i =1/10;
		System.out.println(i);
	}
}
