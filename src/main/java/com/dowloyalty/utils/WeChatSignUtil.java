package com.dowloyalty.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.dowloyalty.controller.TaskWeChatKeyConfiguration;

/**
 * 微信首次验证处理
 * @author MingXuan
 *
 */
public class WeChatSignUtil {
    // token
    private static String token = TaskWeChatKeyConfiguration.WechatSubscriptionToken;

    /**
     * 首次接入验证
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp,String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };
        MessageDigest md = null;
        String tmpStr = null;
        try {
        	// 将token、timestamp、nonce三个参数进行字典序排序
        	Arrays.sort(arr);
        	StringBuilder content = new StringBuilder();
        	for (int i = 0; i < arr.length; i++) {
        		content.append(arr[i]);
        	}
        	md = MessageDigest.getInstance("SHA-1");
        	// sha1加密
        	byte[] digest = md.digest(content.toString().getBytes());
        	tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }catch (NullPointerException e) {
        	e.printStackTrace();
        	System.err.println("token、timestamp、nonce 有问题，验证失败");
        	return false;
		}
        // 将经过sha1加密的序列与发送端进行匹配
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将byte 转化为String
     * 
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * byte to 十六进制
     * 
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
    /**
     * 字典排序
     * @param a
     */
    public static void sort(String a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}