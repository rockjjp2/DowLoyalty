package com.dowloyalty.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTTokenUtils {
	private String secert="dowSecret";
	private String issuer="capgemini";
	
	private static JWTTokenUtils instance=null;
	private JWTTokenUtils(){}
	
	public static JWTTokenUtils getInstance() {
		if(instance==null){
			//如果A，B为空则进入
			synchronized(JWTTokenUtils.class){//A 进入后 此处功能是防止B进入，加入同步锁 只允许一个实例进入
			if(instance==null)//A为null
			 //对A进行实例化 再返回同步快12行，此时只有一个对象B,可以进入同步锁，到14行此时对象不为空因为实例化了A ，直接返回S
			instance = new JWTTokenUtils();
			}
		}
		return instance;
	}
	
	public String creatToken(String subject,String audience) {
			String token = "";
			try {
			   token = JWT.create()
			        .withIssuer(issuer)
			        .withSubject(subject)
			        .withAudience(audience)
			        .withIssuedAt(new Date())
			        .sign(Algorithm.HMAC256(secert));
			} catch (JWTCreationException | IllegalArgumentException | UnsupportedEncodingException exception){
			    //Invalid Signing configuration / Couldn't convert Claims.
			}
			System.out.println("---------jwt--create-------:"+token);
			System.out.println("subject"+subject+"audience"+audience);
		return token;
	}
	public boolean decodeToken(String token,String subject,String audience) {
		try {
			    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secert))
			        .withIssuer(issuer)
			        .withSubject(subject)
			        .withAudience(audience)
			        .build(); //Reusable verifier instance
			    JWT jwt= (JWT) verifier.verify(token);
			    System.out.println("时间---"+jwt.getIssuedAt());
			} catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException exception){
				System.out.println("---------jwt--error-------");
				System.err.println(exception.getMessage());
				return false;
			}
		System.out.println("---------jwt--true-------");
		return true;
	}
}
