package com.zhixun.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.zhixun.action.TermController;
import com.zhixun.model.OwnMediaModel;

public class ReadOwnMediaData extends ReadExcelUtil {

	/**
	 * 获取文件中所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public void execExcelData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for (Sheet sheet : sheets) {
			List<OwnMediaModel> list = new ArrayList<OwnMediaModel>();
			Iterator<Row> rows = sheet.rowIterator();

			list = parseRow(rows);
			if (list != null && !list.isEmpty()) {
				System.out.println(sheet.getSheetName() + "--执行导入");

				TermController.executeOwnMedia(list);
			} else {
				System.out.println(sheet.getSheetName() + "--跳过");
			}
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private List<OwnMediaModel> parseRow(Iterator<Row> rows) {

		List<OwnMediaModel> list = new ArrayList<OwnMediaModel>();
		
		int rowNum = 0;
		// 读取每一行
		while (rows.hasNext()) {
			Row row = rows.next(); 
			//前3行为无效行，从第4行开始读取
			if ( ++rowNum < 4)
				continue;
			
			Iterator<Cell> cells = row.cellIterator();

			OwnMediaModel model = new OwnMediaModel();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				int cellType = cell.getCellType();

				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				//第4列对应 城市 city
				case 3:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setCity(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setCity(String.valueOf(cell.getNumericCellValue()).trim());
					else if (cellType == HSSFCell.CELL_TYPE_BLANK || cellType == XSSFCell.CELL_TYPE_BLANK)
						model.setCity(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				 
				case 5:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING){
						String sname = cell.getStringCellValue().trim();
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setAnetBusCount(sname);
					}
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC){
						String sname = Double.toString(cell.getNumericCellValue());
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setAnetBusCount( sname);
						}
					break;
				case 6:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING){
						String sname = cell.getStringCellValue().trim();
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setBnetBusCount(sname);
					}
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC){
						String sname = Double.toString(cell.getNumericCellValue());
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setBnetBusCount( sname);
						}
					break;
				case 7:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING){
						String sname = cell.getStringCellValue().trim();
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setAllBusCount(sname);
					}
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC){
						String sname = Double.toString(cell.getNumericCellValue());
						if(sname.contains(".")){
							sname = sname.substring(0, sname.indexOf("."));
						}
						model.setAllBusCount( sname);
						}
					break;
				case 8:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setAnetResourceProportion(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setAnetResourceProportion(String.valueOf(cell.getNumericCellValue()).trim());
					break;					
				case 9:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setAllResourceProportion(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setAllResourceProportion(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 10:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setVISNResourceProportion(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setVISNResourceProportion(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 11:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setResourcesToDaily(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setResourcesToDaily(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				default:
					break;
				}
			}
			model.setYear(2015);
			list.add(model);
		}
		return list;
	}
}
