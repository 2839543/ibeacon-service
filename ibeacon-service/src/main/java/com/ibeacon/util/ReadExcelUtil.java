package com.ibeacon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelUtil {

	public boolean isExcel_2007 = false;

	/**
	 * @param fileName
	 * @param sheetName
	 * @return
	 */
	public Sheet readExcel(String fileName, String sheetName) {
		// 判断是否是excel2007格式
		if (fileName.endsWith("xlsx"))
			isExcel_2007 = true;

		Workbook wb = null;
		try {
			File excelFile = new File(fileName);
			// 根据文件格式(2003或者2007)来初始化
			if (isExcel_2007)
				wb = new XSSFWorkbook(new FileInputStream(excelFile));
			else
				wb = new HSSFWorkbook(new FileInputStream(excelFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb.getSheet(sheetName);
	}
	/**获取所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public List<Sheet> readExcel(String fileName) {
		// 判断是否是excel2007格式
		if (fileName.endsWith("xlsx"))
			isExcel_2007 = true;

		Workbook wb = null;
		List<Sheet> sheets = new LinkedList<Sheet>();
		try {
			File excelFile = new File(fileName);
			// 根据文件格式(2003或者2007)来初始化
			if (isExcel_2007)
				wb = new XSSFWorkbook(new FileInputStream(excelFile));
			else
				wb = new HSSFWorkbook(new FileInputStream(excelFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {//获取每个Sheet表
			sheets.add(wb.getSheetAt(i));
		}
		return sheets;
	}
}
