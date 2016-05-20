package com.ibeacon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.ibeacon.action.TermController;
import com.ibeacon.model.PrimaryDataModel;

public class ReadPrimaryData extends ReadExcelUtil {

	/**
	 * @param fileName
	 * @param sheetName
	 * @return
	 */
	public List<PrimaryDataModel> getPrimaryData(String fileName, String sheetName,String cityName) {
		List<PrimaryDataModel> list = new ArrayList<PrimaryDataModel>();

		Sheet sheet = readExcel(fileName, sheetName);
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			PrimaryDataModel model= parseRow(row,cityName);
			if(model!=null&&null!=model.getCity_name()){
				list.add(model);
			}
		} 
		TermController.execute(list,null);
		return list;
	}
	/**获取文件中所有sheet
	 * @param fileName
	 * @return
	 */
	public void getPrimaryData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for(Sheet sheet:sheets){
		List<PrimaryDataModel> list = new ArrayList<PrimaryDataModel>();
		Iterator<Row> rows = sheet.rowIterator();
		PrimaryDataModel primary = new PrimaryDataModel();
		if(parseRow(rows,list,primary)){
			System.out.println(sheet.getSheetName()+"--执行导入");
			
			TermController.execute(list,primary);
		}else{
			System.out.println(sheet.getSheetName()+"--跳过");
		} 
		}
	}
	/**
	 * @param row
	 * @return
	 */
	private boolean parseRow(Iterator<Row> rows,List<PrimaryDataModel> list,PrimaryDataModel primary) {
		//城市索引
		int cityIndex = -1;
		boolean cityFlag = false;
		boolean driverFlag = false;
		//是否存在必要字段，存在则执行导入操作，否则略过
		boolean importFlag = false;//表头完整标示
		boolean defaultFlag=false;//设置默认值标示，空字段时使用该默认值，只设置一次（城市和车牌号同时存在）
		//读取每一行
		while (rows.hasNext()) {
		
		Row row = rows.next();
		Iterator<Cell> cells = row.cellIterator();
		if(!importFlag){
			//读取表头行每一列
			while (cells.hasNext()) {
				//读取表头是否包含“城市”
				Cell cell = cells.next();
				int cellType = cell.getCellType();
				if (cell.getColumnIndex() > 11) {
					break;
				}
				String cellValue = (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)?cell.getStringCellValue().trim():String.valueOf(cell.getNumericCellValue()).trim();
				if("城市".equalsIgnoreCase(cellValue)){ 
					cityIndex = cell.getColumnIndex();
					cityFlag =true;
				}else if("设备ID".equalsIgnoreCase(cellValue)){ 
					driverFlag =true;
				}
				if(cityFlag&&driverFlag){
					importFlag = true;
					break;
				}
			}
		}else{
		boolean localCellFlag = false;//列完整验证标示
		PrimaryDataModel model = new PrimaryDataModel();
		while (cells.hasNext()) {
			Cell cell = cells.next();
			int cellType = cell.getCellType();
			// 只读前N列
			if (cell.getColumnIndex() > (9+cityIndex)) {
				localCellFlag =true;
				break;
			}
//			if(!flag){
//				if(!cityName.equals(
//						(cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)?cell.getStringCellValue().trim():String.valueOf(cell.getNumericCellValue()).trim())){ 
//					index++;
//					continue;
//				}else{
//					flag = true;
//				}
//			}
//			if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
//				System.out.println(cell.getStringCellValue().trim());
//			else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
//				System.out.println(String.valueOf(cell.getNumericCellValue()).trim());
// 
			int columnIndex = cell.getColumnIndex();
			switch (columnIndex-cityIndex) {
			case 0:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setCity_name(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setCity_name(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 1:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setBus_company(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setBus_company(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 2:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setLine_name(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setLine_name(String.valueOf((int) cell.getNumericCellValue()).trim());
				break;
			case 3:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setBus_license(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setBus_license(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 4:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setBus_self_num(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setBus_self_num(String.valueOf((int) cell.getNumericCellValue()).trim());
				break;
			case 5:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setFactory(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setFactory(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 6:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setModel(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setModel(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 7:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setSerial_num(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setSerial_num(String.valueOf((int) cell.getNumericCellValue()).trim());
				break;
			case 8:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setDevice_id(Integer.parseInt(cell.getStringCellValue().trim()));
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setDevice_id((int) cell.getNumericCellValue());
				break;
			case 9:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setInstall_pos(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setInstall_pos(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 10:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setInstall_time(getTimeStamp(getFormatTime(cell.getStringCellValue().trim())));
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setInstall_time(getTimeStamp(getFormatTime(String.valueOf(cell.getNumericCellValue()).trim())));
				break;
			case 11:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setComment(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setComment(String.valueOf(cell.getNumericCellValue()).trim());
			default:
				break;
			}
		}
		if(localCellFlag){
			//数据列完整则加入到导入队列中，否则视为无效数据
			if(!(model.getCity_name()==null||"".equals(model.getCity_name()))){
				model.setCity_name(model.getCity_name().contains("市")?model.getCity_name():model.getCity_name()+"市"); 
				list.add(model);
				if(!defaultFlag){
					primary.setCity_name(model.getCity_name()); 
					primary.setBus_license(model.getCity_name()+"xxxxxx");
					if(!(model.getBus_license()==null||"".equals(model.getBus_license()))){
						primary.setBus_license(model.getBus_license().substring(0, 2)+"xxxxxx");  
						defaultFlag =true; 
					}
				}
			}
		}
		}
	}
		return importFlag;
	}

	/**
	 * @param row
	 * @return
	 */
	private PrimaryDataModel parseRow(Row row,String cityName) {
		PrimaryDataModel model = new PrimaryDataModel();
		Iterator<Cell> cells = row.cellIterator();
		int index=0;
		boolean flag = false;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			int cellType = cell.getCellType();
			// 只读前N列
			if (cell.getColumnIndex() > (9+index)) {
				break;
			}
			if(!flag){
				if(!cityName.equals(
						(cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)?cell.getStringCellValue().trim():String.valueOf(cell.getNumericCellValue()).trim())){ 
					index++;
					continue;
				}else{
					flag = true;
				}
			}
//			if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
//				System.out.println(cell.getStringCellValue().trim());
//			else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
//				System.out.println(String.valueOf(cell.getNumericCellValue()).trim());
// 
			int columnIndex = cell.getColumnIndex();
			switch (columnIndex-index) {
			case 0:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setCity_name(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setCity_name(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 1:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setBus_company(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setBus_company(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 2:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setLine_name(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setLine_name(String.valueOf((int) cell.getNumericCellValue()).trim());
				break;
			case 3:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setBus_license(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setBus_license(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 4:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setBus_self_num(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setBus_self_num(String.valueOf((int) cell.getNumericCellValue()).trim());
				break;
			case 5:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setFactory(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setFactory(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 6:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setModel(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setModel(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 7:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setSerial_num(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setSerial_num(String.valueOf((int) cell.getNumericCellValue()).trim());
				break;
			case 8:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setDevice_id(Integer.parseInt(cell.getStringCellValue().trim()));
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setDevice_id((int) cell.getNumericCellValue());
				break;
			case 9:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setInstall_pos(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setInstall_pos(String.valueOf(cell.getNumericCellValue()).trim());
				break;
			case 10:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setInstall_time(getTimeStamp(getFormatTime(cell.getStringCellValue().trim())));
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setInstall_time(getTimeStamp(getFormatTime(String.valueOf(cell.getNumericCellValue()).trim())));
				break;
			case 11:
				if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
					model.setComment(cell.getStringCellValue().trim());
				else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
					model.setComment(String.valueOf(cell.getNumericCellValue()).trim());
			default:
				break;
			}
		}
		return model;
	}

	/**
	 * @param install_time
	 * @return
	 */
	private String getFormatTime(String install_time) {
		String[] time = install_time.split("\\.");
		String year = (time[0].length() == 4) ? time[0] : "20".concat(time[0]);
		// String month = (time[1].length() > 1) ? time[1] :
		// "0".concat(time[1]);
		// String day = (time[2].length() > 1) ? time[2] : "0".concat(time[2]);
		String month = time[1];
		String day = time[2];

		return year.concat("-").concat(month).concat("-").concat(day);
	}

	/**
	 * @param time
	 * @return
	 */
	private Integer getTimeStamp(String time) {
		Integer timeStamp = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dTime = sdf.parse(time);
//			System.out.println(sdf.format(dTime));
			timeStamp = (int) (dTime.getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return timeStamp;
	}
}
