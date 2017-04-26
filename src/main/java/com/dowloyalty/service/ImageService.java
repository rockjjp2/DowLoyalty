package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Image;

public interface ImageService {
	public void save(Image image);
	
	public List<Image> findAll();
}
