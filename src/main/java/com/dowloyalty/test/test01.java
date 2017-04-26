package com.dowloyalty.test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.junit.Test;

public class test01 {
	@Test
	public void ad(){
		String string="0013000001MT5DcAAL";
		String newStr=string.substring(10);
		System.out.println(newStr);
		// 编码  
		String asB64 = null;
		try {
			asB64 = Base64.getEncoder().encodeToString(string.substring(10).getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(asB64); // 输出为: c29tZSBzdHJpbmc=  
	}
}
