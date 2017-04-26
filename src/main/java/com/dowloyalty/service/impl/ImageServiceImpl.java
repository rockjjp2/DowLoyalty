package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ImageDao;
import com.dowloyalty.entity.Image;
import com.dowloyalty.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService {
	
	@Resource
	ImageDao imageDao;
	
	@Override
	public void save(Image image) {
		imageDao.save(image);
	}

	@Override
	public List<Image> findAll() {
		return imageDao.findAll();
	}

}
