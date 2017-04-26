package com.dowloyalty.test;

import org.junit.Test;

import com.dowloyalty.utils.JWTTokenUtils;

public class TokenTest {
	@Test
	public void ok(){
		String aString="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9tb3RlcldlY2hhdCIsImF1ZCI6IjIiLCJpc3MiOiJjYXBnZW1pbmkiLCJpYXQiOjE0ODcyMTE1NjZ9.C7R5feyCtLuC0KH6ljss-sTgYb7uaRs9EEfeUs0HP2Q";
		@SuppressWarnings("unused")
		String token = JWTTokenUtils.getInstance().creatToken("123", "123");
		JWTTokenUtils.getInstance().decodeToken(aString, "retailer", "123");
	}
}
