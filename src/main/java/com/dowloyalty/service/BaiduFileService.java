package com.dowloyalty.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.AppendObjectResponse;
import com.dowloyalty.controller.TaskWeChatKeyConfiguration;

public class BaiduFileService{
	private String BaiduDiskUrl="http://dowloyalty.bj.bcebos.com/";
	protected final Log logger = LogFactory.getLog(this.getClass());
	private static BaiduFileService instance=null;
	private  BaiduFileService() {
	}
	public static BaiduFileService getInstance() {
		if(instance==null){
			//如果A，B为空则进入
			synchronized(BaiduFileService.class){//A 进入后 此处功能是防止B进入，加入同步锁 只允许一个实例进入
			if(instance==null)//A为null
			 //对A进行实例化 再返回同步快12行，此时只有一个对象B,可以进入同步锁，到14行此时对象不为空因为实例化了A ，直接返回S
			instance = new BaiduFileService();
			}
		}
		return instance;
	}
	public BosClient getBosClient() {
		String ACCESS_KEY_ID = TaskWeChatKeyConfiguration.BaiduBOS_ACCESS_KEY_ID;                   // 用户的Access Key ID
	    String SECRET_ACCESS_KEY = TaskWeChatKeyConfiguration.BaiduBOS_SECRET_ACCESS_KEY;           // 用户的Secret Access Key
	    // 初始化一个BosClient
	    BosClientConfiguration config = new BosClientConfiguration();
	    config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
	    BosClient client = new BosClient(config);
		return client;
	}
	public String uploadFile(MultipartFile  file) {
		String newName=null;
		if (file==null) {
			logger.error("没有文件，请检查后上传");
		}else {
			InputStream inputStream = null;
			try {
				inputStream = file.getInputStream();
			} catch (IOException e) {
				logger.error("获取文件流异常");
			}
			BosClient bosClient=getBosClient();
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			newName=new Date().getTime()+suffix;
			//PutObjectResponse putObjectResponseFromInputStream = bosClient.putObject("dowloyalty", newName, file);
			// 以数据流形式上传Object
	        AppendObjectResponse appendObjectResponseFromInputStream = bosClient.appendObject("dowloyalty", newName, inputStream);

			logger.debug("上传文件在百度中ETag为"+appendObjectResponseFromInputStream.getETag());
		}
		return BaiduDiskUrl+newName;
	}
}
