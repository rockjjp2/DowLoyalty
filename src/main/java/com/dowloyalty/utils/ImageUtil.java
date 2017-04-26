package com.dowloyalty.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageUtil {
	private static FileInputStream in = null;
	
	public static byte[] transferImage(File file) throws IOException
	{
		in = new FileInputStream(file);
		byte[] image = new byte[in.available()];
		in.read(image);
		in.close();
		return image;
	}
	
}
