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
import com.zhixun.model.MediaEnvModel;

public class ReadMediaEnvData extends ReadExcelUtil {

	/**
	 * 获取文件中所有sheet
	 * 
	 * @param fileName
	 * @return
	 */
	public void execExcelData(String fileName) {
		List<Sheet> sheets = readExcel(fileName);
		for (Sheet sheet : sheets) {
			List<MediaEnvModel> list = new ArrayList<MediaEnvModel>();
			Iterator<Row> rows = sheet.rowIterator();

			list = parseRow(rows);
			if (list != null && !list.isEmpty()) {
				System.out.println(sheet.getSheetName() + "--执行导入");

				TermController.executeMediaEnv(list);
			} else {
				System.out.println(sheet.getSheetName() + "--跳过");
			}
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private List<MediaEnvModel> parseRow(Iterator<Row> rows) {

		List<MediaEnvModel> list = new ArrayList<MediaEnvModel>();
		
		int rowNum = 0;
		// 读取每一行
		while (rows.hasNext()) {
			Row row = rows.next();
			//前3行为无效行，从第4行开始读取
			if ( ++rowNum < 4)
				continue;
			
			Iterator<Cell> cells = row.cellIterator();

			MediaEnvModel model = new MediaEnvModel();
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
					break;				 
				 
				case 5:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setCityPopulation(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setCityPopulation(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 6:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setUrbanPopulationCoefficient(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setUrbanPopulationCoefficient(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 7:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setGDP(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setGDP(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 8:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setVs2015GDP(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setVs2015GDP(String.valueOf(cell.getNumericCellValue()).trim());
					break;					
				case 9:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setEconomicPerformanceCoefficient(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setEconomicPerformanceCoefficient(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 10:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setCitybusTotalnum(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setCitybusTotalnum(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 11:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setPublicTransportPerMillionPeople(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setPublicTransportPerMillionPeople(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 12:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setInstalledBusTotal(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setInstalledBusTotal(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 13:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setBusTVInstallProportion(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setBusTVInstallProportion(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 14:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setSignalTransMode(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setSignalTransMode(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 15:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setTVPermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setTVPermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 16:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setRadioPermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setRadioPermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 17:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setMoviePermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setMoviePermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 18:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setInternetPermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setInternetPermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;					
				case 19:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setOohPermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setOohPermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 20:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setNpPermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setNpPermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 21:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setMgPermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setMgPermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 22:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setBusLCDPermeability(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setBusLCDPermeability(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 23:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setBusLCDoohEffect(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setBusLCDoohEffect(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 24:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setBusLCDTendency(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setBusLCDTendency(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 25:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setBusLCDTendencyCoefficient(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setBusLCDTendencyCoefficient(String.valueOf(cell.getNumericCellValue()).trim());
					break;				 
				case 26:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setTakeBusNumRatio(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setTakeBusNumRatio(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 27:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setMaleRatio(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setMaleRatio(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 28:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setWomenRatio(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setWomenRatio(String.valueOf(cell.getNumericCellValue()).trim());
					break;					
				case 29:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setMetroLineNumber(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setMetroLineNumber(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 30:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setBusLanesMileage(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setBusLanesMileage(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 31:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setPeakCongestionDelayIndex(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setPeakCongestionDelayIndex(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 32:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setPeakAverageSpeed(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setPeakAverageSpeed(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 33:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setRegionalUrbanAgglomeration(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setRegionalUrbanAgglomeration(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 34:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setLargeEnterprisesRelocationSiteInfo(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setLargeEnterprisesRelocationSiteInfo(String.valueOf(cell.getNumericCellValue()).trim());
					break;
				case 35:
					if (cellType == HSSFCell.CELL_TYPE_STRING || cellType == XSSFCell.CELL_TYPE_STRING)
						model.setRegionalPolicyCharacteristics(cell.getStringCellValue().trim());
					else if (cellType == HSSFCell.CELL_TYPE_NUMERIC || cellType == XSSFCell.CELL_TYPE_NUMERIC)
						model.setRegionalPolicyCharacteristics(String.valueOf(cell.getNumericCellValue()).trim());
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
