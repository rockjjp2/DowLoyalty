package com.dowloyalty.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.dowloyalty.entity.Goods;
import com.dowloyalty.pojo.PromoterVo;

public class ImportExcelUtil {
	
	public static List<Goods> importGoodsInfo(InputStream inputStream) throws Exception
	{
		//创建集合存储礼品对象
		List<Goods> goodsList = new ArrayList<>();
		//解析表格
		Workbook workBook = WorkbookFactory.create(inputStream);
		Sheet sheet =  workBook.getSheetAt(0);
		Row titleRow = sheet.getRow(0);
//		GoodsVo goodsVo = null;
		Goods goods = null;
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
		{
			Row row = sheet.getRow(i);
//			goodsVo = new GoodsVo();
			goods = new Goods();
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++)
			{
				String title = titleRow.getCell(j).getStringCellValue();
				if(row.getCell(j).getCellType() != Cell.CELL_TYPE_STRING)
				{
					continue;
				}
				String goodsInfo = row.getCell(j).getStringCellValue();
				switch (title)
				{
				case "奖品类别":
					if(goodsInfo != null && !"".equals(goodsInfo.trim()))
					{
						goods.setName(goodsInfo);
					}
					break;
				case "礼品描述":
					goods.setDescription(goodsInfo);
					break;
				}
			}
			goods.setActive(true);
			goods.setImagePath("//");
			goodsList.add(goods);
		}
		return goodsList;
	}
	
	public static List<PromoterVo> importPromotersInfo(InputStream inputStream) throws Exception
	{
		List<PromoterVo> promoters = new ArrayList<>();
		Workbook workBook = WorkbookFactory.create(inputStream);
		Sheet sheet =  workBook.getSheetAt(0);
		Row titleRow = sheet.getRow(0);
		PromoterVo promoter = null;
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
		{
			Row row = sheet.getRow(i);
			promoter = new PromoterVo();
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++)
			{
				String title = titleRow.getCell(j).getStringCellValue();
				if(row.getCell(j).getCellType() != Cell.CELL_TYPE_STRING)
				{
					continue;
				}
				String Info = row.getCell(j).getStringCellValue();
				switch (title)
				{
				case "ChineseName":
					if(Info != null && !"".equals(Info.trim()))
					{
						promoter.setChineseName(Info);
					}
					break;
				case "UserName":
					promoter.setUserID(Info);
					break;
				case "Name":
					promoter.setProvinceName(Info);
					break;
				}
			}
			promoter.setActive(true);
			promoters.add(promoter);
		}
		return promoters;
	}
}
