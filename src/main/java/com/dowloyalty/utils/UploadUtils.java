package com.dowloyalty.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
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
}
