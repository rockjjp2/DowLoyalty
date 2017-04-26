package com.dowloyalty.test;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTest {
	int a = 1;
	int b;
	
	
	
	public static void main(String[] args) {
		JsonTest fff = new JsonTest();
		fff.a = 111;
		fff.b = 222;
		String s = ((JSONArray)JSON.parse("[{'a':333,'b':444},{'a':222,'b':111}]")).toString();
		String json = JSON.toJSONString(fff);
		List<JsonTest> json1 = JSON.parseArray(s, JsonTest.class);
		for(JsonTest ss: json1)
		{
			System.out.println(ss.getA());
		}
		//System.out.println(s);
		//System.out.println(json);
	}



	public int getA() {
		return a;
	}



	public void setA(int a) {
		this.a = a;
	}



	public int getB() {
		return b;
	}



	public void setB(int b) {
		this.b = b;
	}
}
