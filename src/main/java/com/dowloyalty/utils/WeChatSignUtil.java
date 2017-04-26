package com.dowloyalty.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.dowloyalty.controller.TaskWeChatKeyConfiguration;

/**
 * ΢���״���֤����
 * @author MingXuan
 *
 */
public class WeChatSignUtil {
    // token
    private static String token = TaskWeChatKeyConfiguration.WechatSubscriptionToken;

    /**
     * �״ν�����֤
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
        	// ��token��timestamp��nonce�������������ֵ�������
        	Arrays.sort(arr);
        	StringBuilder content = new StringBuilder();
        	for (int i = 0; i < arr.length; i++) {
        		content.append(arr[i]);
        	}
        	md = MessageDigest.getInstance("SHA-1");
        	// sha1����
        	byte[] digest = md.digest(content.toString().getBytes());
        	tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }catch (NullPointerException e) {
        	e.printStackTrace();
        	System.err.println("token��timestamp��nonce �����⣬��֤ʧ��");
        	return false;
		}
        // ������sha1���ܵ������뷢�Ͷ˽���ƥ��
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * ��byte ת��ΪString
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
     * byte to ʮ������
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
     * �ֵ�����
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