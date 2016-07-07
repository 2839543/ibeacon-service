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
import com.zhixun.model.AttributeInfoModel;

public class ReadAttributeInfoData extends ReadExcelUtil {

	/**
	 * 获取文件中所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public void execExcelData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for (Sheet sheet : sheets) {
			List<AttributeInfoModel> list = new ArrayList<AttributeInfoModel>();
			Iterator<Row> rows = sheet.rowIterator();

			list = parseRow(rows);
			if (list != null && !list.isEmpty()) {
				System.out.println(sheet.getSheetName() + "--执行导入");

				TermController.executeAttributeInfo(list);
			} else {
				System.out.println(sheet.getSheetName() + "--跳过");
			}
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private List<AttributeInfoModel> parseRow(Iterator<Row> rows) {

		List<AttributeInfoModel> list = new ArrayList<AttributeInfoModel>();
		
		int rowNum = 0;
		// 读取每一行
		while (rows.hasNext()) {
			Row row = rows.next();
			if ( ++rowNum < 3)
				continue;
			
			Iterator<Cell> cells = row.cellIterator();

			AttributeInfoModel model = new AttributeInfoModel();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				int cellType = cell.getCellType();

				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case 1:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING){
						String sname = cell.getStringCellValue().trim();
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setLineName(sname);
					}
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC){
						String sname = Double.toString(cell.getNumericCellValue());
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setLineName( sname);
						}
					break;
				 
				case 2:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setOneWayTrafficTime(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setOneWayTrafficTime(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 3:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setPeakShiftInterval(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setPeakShiftInterval(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 4:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setPeakShiftIntervalScore(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setPeakShiftIntervalScore(String.valueOf(cell.getNumericCellValue()).trim());
					break;
					
					
				case 5:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setThroughLoopLine(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setThroughLoopLine(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 6:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setThroughArea(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setThroughArea(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 7:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setThroughAreaScore(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setThroughAreaScore(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 8:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setSubwayLineContactRatio(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setSubwayLineContactRatio(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 9:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setThroughSubway(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setThroughSubway(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 10:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setAvgTimeSubway(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setAvgTimeSubway(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 11:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setCanReplacedSubway(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setCanReplacedSubway(String.valueOf(cell.getNumericCellValue()).trim());
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
