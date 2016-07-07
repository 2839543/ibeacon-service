package com.zhixun.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.zhixun.Main;
import com.zhixun.action.TermController;
import com.zhixun.model.SiteInfoModel;

public class ReadSiteInfoData extends ReadExcelUtil {

	/**
	 * 获取文件中所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public void execExcelData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for (Sheet sheet : sheets) {
			List<SiteInfoModel> list = new ArrayList<SiteInfoModel>();
			Iterator<Row> rows = sheet.rowIterator();

			list = parseRow(rows);
			if (list != null && !list.isEmpty()) {
				System.out.println(sheet.getSheetName() + "--执行导入");

				TermController.executeSiteInfo(list);
			} else {
				System.out.println(sheet.getSheetName() + "--跳过");
			}
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private List<SiteInfoModel> parseRow(Iterator<Row> rows) {

		List<SiteInfoModel> list = new ArrayList<SiteInfoModel>();

		int rowNum = 0;
		boolean IsOver = false;
		// 读取每一行
		while (rows.hasNext() && !IsOver) {
			Row row = rows.next();
			// 前2行为无效行，从第3行开始读取
			if (++rowNum < 3)
				continue;

			Iterator<Cell> cells = row.cellIterator();

			SiteInfoModel model = new SiteInfoModel();
			while (cells.hasNext() && !IsOver) {
				Cell cell = cells.next();
				int cellType = cell.getCellType();

				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				// 第2列对应 线路 line
				case 1:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING) {
						String sname = cell.getStringCellValue().trim();
						if (sname.contains(".")) {
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setName(sname);
					} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC) {
						String sname = Double.toString(cell.getNumericCellValue());
						if (sname.contains(".")) {
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setName(sname);
					}
					break;
				case 2:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING) {
						String sname = cell.getStringCellValue().trim();
						if (sname.contains(".")) {
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setBusCount(sname);
					} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC) {
						String sname = Double.toString(cell.getNumericCellValue());
						if (sname.contains(".")) {
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setBusCount(sname);
					}
					break;
				case 3:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING) {
						String sname = cell.getStringCellValue().trim();
						if (sname.contains(".")) {
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setScreenCount(sname);
					} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC) {
						String sname = Double.toString(cell.getNumericCellValue());
						if (sname.contains(".")) {
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setScreenCount(sname);
					}
					break;
				case 4:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setAllSiteName(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setAllSiteName(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 5:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setOnewayTime(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setOnewayTime(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 6:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setLineBuildings(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setLineBuildings(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				default:
					break;
				}
			}
			// if(model.get ){
			model.setYear(Main.YEAR);
			list.add(model);
			// }
		}
		return list;
	}
}
