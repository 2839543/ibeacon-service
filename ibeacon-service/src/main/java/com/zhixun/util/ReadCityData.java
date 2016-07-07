package com.zhixun.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.zhixun.action.TermController;
import com.zhixun.model.CityModel;

public class ReadCityData extends ReadExcelUtil {

	/**
	 * 获取文件中所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public void execExcelData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for (Sheet sheet : sheets) {
			List<CityModel> list = new ArrayList<CityModel>();
			sheet.getLastRowNum();
			Iterator<Row> rows = sheet.rowIterator();

			list = parseRow(rows);
			if (list != null && !list.isEmpty()) {
				System.out.println(sheet.getSheetName() + "--执行导入");

				TermController.executeCity(list);
			} else {
				System.out.println(sheet.getSheetName() + "--跳过");
			}
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private List<CityModel> parseRow(Iterator<Row> rows) {

		List<CityModel> list = new ArrayList<CityModel>();
		// 读取每一行
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();

			CityModel model = new CityModel();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				int cellType = cell.getCellType();

				int columnIndex = cell.getColumnIndex();
 				switch (columnIndex) {

				case 1:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setRegion(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setRegion(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 2:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setProvince(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setProvince(String.valueOf((int) cell.getNumericCellValue()).trim());
					break;
				case 3:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setCity(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setCity(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 4:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setLevel(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setLevel(String.valueOf((int) cell.getNumericCellValue()).trim());
					break;
				default:
					break;
				}

			}

			if (StringUtils.isEmpty(model.getRegion()) || StringUtils.isEmpty(model.getCity())
					|| StringUtils.equalsIgnoreCase(model.getRegion(), "区域")
					|| StringUtils.equalsIgnoreCase(model.getCity(), "城市")) {
				continue;
			}
			list.add(model);
		}
		return list;
	}
}
