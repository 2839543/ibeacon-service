package com.ibeacon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ibeacon.model.PrimaryDataModel;
import com.ibeacon.util.ReadPrimaryData;


public class Main {

	public static void main(String[] args) {	
	
		// 已部署ibeacon原始数据入库
		String primaryFilePath = "E:\\工作计划\\ibeacon\\ibeacon数据表总汇20160316.xls";
		autoUpload(primaryFilePath);
}
	private static void manualUpload(String filePath){
		Map<String,String> map = new HashMap<String,String>();
//		map.put("成都", "成都市");
//		map.put("哈尔滨", "哈尔滨市");
		map.put("沈阳", "沈阳市");
//		map.put("郑州", "郑州市");
//		map.put("深圳", "深圳市");
//		map.put("武汉", "武汉市");
//		map.put("青岛", "青岛市");
//		map.put("东莞", "东莞市");
//		map.put("海口", "海口市");
//		map.put("宁波B网", "宁波市");
		Set<Entry<String, String>> set = map.entrySet();
		for(Entry<String, String> entry:set){
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			String sheetName = entry.getKey();
			String cityName = entry.getValue();
			System.out.println(sheetName+"--"+cityName);
			List<PrimaryDataModel> primaryList = new ReadPrimaryData().getPrimaryData(filePath, sheetName,cityName);
			System.out.println("行数："+primaryList.size());
	}
	}
	private static void autoUpload(String filePath){
		new ReadPrimaryData().getPrimaryData(filePath);
	}
}
