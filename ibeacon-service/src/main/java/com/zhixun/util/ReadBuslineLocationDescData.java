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
import com.zhixun.model.BuslineLocationDescModel;

public class ReadBuslineLocationDescData extends ReadExcelUtil {

	/**
	 * 获取文件中所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public void execExcelData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for (Sheet sheet : sheets) {
			List<BuslineLocationDescModel> list = new ArrayList<BuslineLocationDescModel>();
			Iterator<Row> rows = sheet.rowIterator();

			list = parseRow(rows);
			if (list != null && !list.isEmpty()) {
				System.out.println(sheet.getSheetName() + "--执行导入");

				TermController.executeBusLineLocationDesc(list);
			} else {
				System.out.println(sheet.getSheetName() + "--跳过");
			}
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private List<BuslineLocationDescModel> parseRow(Iterator<Row> rows) {

		List<BuslineLocationDescModel> list = new ArrayList<BuslineLocationDescModel>();

		int rowNum = 0;
		boolean IsOver = false;
		// 读取每一行
		while (rows.hasNext() && !IsOver) {
			Row row = rows.next();
			// 前2行为无效行，从第3行开始读取
			if (++rowNum < 3)
				continue;

			Iterator<Cell> cells = row.cellIterator();

			BuslineLocationDescModel model = new BuslineLocationDescModel();
			while (cells.hasNext() && !IsOver) {
				Cell cell = cells.next();
				int cellType = cell.getCellType();

				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				// 第2列对应 线路 line
				case 1:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING){
						String sname = cell.getStringCellValue().trim();
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setName(sname);
					}else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC){
						String sname = Double.toString(cell.getNumericCellValue());
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setName( sname);
					}
					break;
				case 2:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setBusinessCenter(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setBusinessCenter(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 3:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING) {
						model.setTrafficHub(cell.getStringCellValue().trim());
					} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setTrafficHub(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 4:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setPublicEntertainment(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setPublicEntertainment(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 5:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setCollegeCommunity(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setCollegeCommunity(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 6:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setLargeStreetCommunity(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setLargeStreetCommunity(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 7:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setLargeScienceTechnologyPark(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setLargeScienceTechnologyPark(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				default:
					break;
				}
			}
			model.setYear(Main.YEAR);
			list.add(model);
		}
		return list;
	}
}
