package com.dowloyalty.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	protected final Log logger = LogFactory.getLog(this.getClass());
    public boolean uploadFile(MultipartFile file,HttpServletRequest request){  
          String path=request.getSession().getServletContext().getRealPath("/")+"Resources/upload/";
          String newFileName=new Date().getTime() + file.getOriginalFilename();
        if(!file.isEmpty()){
        	String filePath = path+newFileName;
			File saveDir = new File(filePath);
			if (!saveDir.getParentFile().exists())
				saveDir.getParentFile().mkdirs();
			try {
				file.transferTo(saveDir);
			} catch (IllegalStateException | IOException e) {
				return false;
			}
        }
            return true;  
    } 
    
    public String uploadFiles(MultipartFile file,HttpServletRequest request){  
        String path=request.getSession().getServletContext().getRealPath("/")+"Resources/upload/";
        String newFileName=new Date().getTime() + file.getOriginalFilename();
      if(!file.isEmpty()){
      	String filePath = path+newFileName;
			File saveDir = new File(filePath);
			
			if (!saveDir.getParentFile().exists())
				saveDir.getParentFile().mkdirs();
			try {
				file.transferTo(saveDir);
			} catch (IllegalStateException | IOException e) {
				logger.warn("上传文件时文件转存失败");
				return null;
			}
      }
          return "Resources/upload/"+newFileName;  
  } 
}
