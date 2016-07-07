package com.zhixun.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.ibeacon.model.IbeaconModel;

public class ReadIbeaconData extends ReadExcelUtil {

	/**
	 * @param fileName
	 * @param sheetName
	 * @return
	 */
	public List<IbeaconModel> getIbeaconData(String fileName, String sheetName) {
		List<IbeaconModel> list = new ArrayList<IbeaconModel>();

		Sheet sheet = readExcel(fileName, sheetName);
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			list.add(parseRow(row));
		}
		return list;
	}

	/**
	 * ibeacon数据读取
	 * 
	 * @param row
	 * @return
	 */
	private IbeaconModel parseRow(Row row) {
		IbeaconModel model = new IbeaconModel();
		model.setFactory("创新微");
		model.setModel("i4迷你型");

		Iterator<Cell> cells = row.cellIterator();
		while (cells.hasNext()) {
			Cell cell = cells.next();
			// 只读取前5列
			if (cell.getColumnIndex() > 4) {
				break;
			}

			int cellType = cell.getCellType();
			int columnIndex = cell.getColumnIndex();
			
			switch (columnIndex) {
			case 0:
				if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setWx_device_id((int) cell.getNumericCellValue());
				break;
			case 1:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setUuid(cell.getStringCellValue());
				break;
			case 2:
				if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setMajor(String.valueOf((int) cell.getNumericCellValue()));
				break;
			case 3:
				if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setMinor(String.valueOf((int) cell.getNumericCellValue()));
				break;
			case 4:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setSn(cell.getStringCellValue());
				break;
			default:
				break;
			}
		}
		return model;
	}

	@Override
	public void execExcelData(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
