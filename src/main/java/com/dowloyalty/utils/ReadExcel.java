package com.dowloyalty.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.dowloyalty.pojo.Excel;
import com.dowloyalty.pojo.FarmerExcel;

/**
 * 
 * @author xiafang
 *
 */

public class ReadExcel {

	// private IImportSalesRecordDao importExcelDao;

	public List<Excel> readExcel(MultipartFile filePath) throws InvalidFormatException, IOException {

		List<Excel> excels = new ArrayList<Excel>();

		// FileInputStream file= new FileInputStream(filePath);//创建文件输入流对象
		Workbook workBook = WorkbookFactory.create(filePath.getInputStream());// 创建一个工作簿
		Sheet sheet = workBook.getSheetAt(0);// 得到第一个工作空间
		Row row1 = sheet.getRow(0);// 得到第一行
		for (int x = 1; x < sheet.getPhysicalNumberOfRows(); x++) {// 读取每个单元格的数据
			Row row = sheet.getRow(x);
			Excel excel = new Excel();
			if (row != null) {
				for (int y = 0; y < row.getPhysicalNumberOfCells(); y++) {
					String title = row1.getCell(y).getStringCellValue();
					Cell cell = row.getCell(y);

					if ("项目名".equals(title)) {
						if (cell == null) {
							excel.setProjectName(null);
						} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
							String projectName = cell.getStringCellValue();
							System.out.println("projectName=======" + projectName);
							excel.setProjectName(projectName.replace(" ", ""));
						}
					}
					if ("零售商名".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
							String retailerName = cell.getStringCellValue();

							System.out.println("retailerName=======" + retailerName);
							excel.setRetailerName(retailerName.replace(" ", ""));
						}
					}
					if ("产品类别".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
							String category = cell.getStringCellValue();
							System.out.println("category=======" + category);
							excel.setCategory(category.replace(" ", ""));
						}
					}
					if ("产品家族".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
							String specification = cell.getStringCellValue();

							System.out.println("specification=======" + specification);
							excel.setProductFamilyName(specification.replace(" ", ""));

						}
					}
					if ("产品".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
							String productName = cell.getStringCellValue();

							System.out.println("productName=======" + productName);
							excel.setProductName(productName.replace(" ", ""));

						}
					}
					if ("销售额".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							double totalPrice = cell.getNumericCellValue();

							System.out.println("totalPrice=======" + totalPrice);
							excel.setTotalPrice(totalPrice);
						}
					}
					if ("数量".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							int amount = (int) row.getCell(y).getNumericCellValue();

							System.out.println("amount=======" + amount);
							excel.setAmount(amount);
						}
					}

				}
			}
			excels.add(excel);
		}
		return excels;
	}

	public List<FarmerExcel> readFarmerExcel(MultipartFile filePath) throws InvalidFormatException, IOException {

		List<FarmerExcel> excels = new ArrayList<FarmerExcel>();

		// FileInputStream file= new FileInputStream(filePath);//创建文件输入流对象
		Workbook workBook = WorkbookFactory.create(filePath.getInputStream());// 创建一个工作簿
		Sheet sheet = workBook.getSheetAt(0);// 得到第一个工作空间
		Row row1 = sheet.getRow(0);// 得到第一行
		for (int x = 1; x < sheet.getPhysicalNumberOfRows(); x++) {// 读取每个单元格的数据
			Row row = sheet.getRow(x);
			FarmerExcel excel = new FarmerExcel();
			if (row != null) {
				for (int y = 0; y < row.getPhysicalNumberOfCells(); y++) {
					String title = row1.getCell(y).getStringCellValue();
					Cell cell = row.getCell(y);

					if ("项目名".equals(title)) {
						if (cell == null) {
							excel.setProjectName(null);
						} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
							String projectName = cell.getStringCellValue();
							System.out.println("projectName=======" + projectName);
							excel.setProjectName(projectName.replace(" ", ""));
						}
					}
					if ("农户名".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
							String farmerName = cell.getStringCellValue();

							System.out.println("farmerName=======" + farmerName);
							excel.setFarmerName(farmerName.replace(" ", ""));
						}
					}
					if ("手机号".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							DecimalFormat df = new DecimalFormat("0");
							String mobile = df.format(cell.getNumericCellValue());

							System.out.println("mobile=======" + mobile);
							excel.setMobile(mobile.replace(" ", ""));
						}
					}
					if ("销售额".equals(title)) {
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							double totalPrice = cell.getNumericCellValue();

							System.out.println("totalPrice=======" + totalPrice);
							excel.setTotalPrice((float) totalPrice);
						}
					}
				}
			}
			excels.add(excel);
		}
		return excels;
	}
}
