package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.Image;

public interface ImageDao {
	
	public void save(Image image);
	
	public List<Image> findAll();
}
