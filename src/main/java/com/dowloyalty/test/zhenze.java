package com.dowloyalty.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.dowloyalty.service.BaiduFileService;

public class zhenze {
	@Test
	public void name() {
		// 要验证的字符串
	    String str = "/WeChat/retailer/exchangeshop";
	    // 正则表达式规则
	    String regEx = "/WeChat/retailer//*";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    // 忽略大小写的写法
	    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(str);
	    // 查找字符串中是否有匹配正则表达式的字符/字符串
	    
	    boolean rs = matcher.find();
	    System.out.println(matcher.toString());
	    System.out.println(rs);
	}
	@Test
	public void asdf(){
		BosClient client = BaiduFileService.getInstance().getBosClient();
		System.out.println(client);
	}
	@Test
	public void cad(){
		String a=null;
		
		System.out.println(a.isEmpty());
	}
	@Test
	public void writerFile() throws FileNotFoundException{
		BosClient client = BaiduFileService.getInstance().getBosClient();
		//InputStream inputStream = new FileInputStream("E:/Downloads/jquery.api.1.11.3.CHM");
		// 以数据流形式上传Object
		File file = new File("E:/Downloads/jquery.api.1.11.3.CHM");
		PutObjectResponse putObjectResponseFromInputStream = client.putObject("dowloyalty", "objectKey", file);
		System.out.println(putObjectResponseFromInputStream.toString());;
	}
}
