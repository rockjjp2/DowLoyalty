package com.dowloyalty.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleHttpConnectUtil {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private static SimpleHttpConnectUtil instance=null;
	private  SimpleHttpConnectUtil() {
	}
	public static SimpleHttpConnectUtil getInstance() {
		if(instance==null){
			//如果A，B为空则进入
			synchronized(SimpleHttpConnectUtil.class){//A 进入后 此处功能是防止B进入，加入同步锁 只允许一个实例进入
			if(instance==null)//A为null
			 //对A进行实例化 再返回同步快12行，此时只有一个对象B,可以进入同步锁，到14行此时对象不为空因为实例化了A ，直接返回S
			instance = new SimpleHttpConnectUtil();
			}
		}
		return instance;
	}
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求数据,以String形式发送。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, String param) {
    	logger.info("已发送请求信息至:"+url+"--，内容为："+param);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("发送 POST 请求出现异常！",e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        logger.info("返回信息内容为："+result);
        return result;
    }
    
   
}
