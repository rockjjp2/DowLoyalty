package com.dowloyalty.entity;

import java.io.Serializable;

public class Image implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5230524564584993187L;
	private int id;
	private byte[] imageFile;
	private String base64;
	public Image(int id, byte[] imageFile, String base64) {
		super();
		this.id = id;
		this.imageFile = imageFile;
		this.base64 = base64;
	}
	public Image() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	
	
}
