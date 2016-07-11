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
import com.zhixun.model.LineModel;

public class ReadLineData extends ReadExcelUtil {

	/**
	 * 获取文件中所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public void execExcelData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for (Sheet sheet : sheets) {
			List<LineModel> list = new ArrayList<LineModel>();
			sheet.getLastRowNum();
			Iterator<Row> rows = sheet.rowIterator();

//			list = parseRow(rows);
			if (list != null && !list.isEmpty()) {
				System.out.println(sheet.getSheetName() + "--执行导入");

				TermController.executeLine(list);
			} else {
				System.out.println(sheet.getSheetName() + "--跳过");
			}
		}
	}
	
	public void execExcelData(String fileName,String[] cityNames) {
		List<Sheet> sheets = readExcel(fileName);
		for(int i = 0 ;i <sheets.size() ;i++){
			List<LineModel> list = new ArrayList<LineModel>();
			sheets.get(i).getLastRowNum();
			Iterator<Row> rows = sheets.get(i).rowIterator();
			
			list = parseRow(rows,cityNames[i]);
			 
			if (list != null && !list.isEmpty()) {
				System.out.println(sheets.get(i).getSheetName() + "--执行导入城市"+cityNames[i]);

				TermController.executeLine(list);
			} else {
				System.out.println(sheets.get(i).getSheetName() + "--跳过城市"+cityNames[i]);
			}
			
			i++;
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private List<LineModel> parseRow(Iterator<Row> rows,String cityname) {

		List<LineModel> list = new ArrayList<LineModel>();
		// 读取每一行
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();

			LineModel model = new LineModel();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				int cellType = cell.getCellType();

				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {

				case 1:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING) {
						model.setName(cell.getStringCellValue().trim());
					} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC) {
						String sname = Double.toString(cell.getNumericCellValue());
						sname = sname.substring(0, sname.indexOf("."));
						model.setName(sname);
					}
					break;
				default:
					break;
				}
			}

			if (StringUtils.isEmpty(model.getName()) || StringUtils.equalsIgnoreCase(model.getName(), "线路名称")) {
				continue;
			}
			model.setCityName(cityname);
			list.add(model);
		}
		return list;
	}
}
