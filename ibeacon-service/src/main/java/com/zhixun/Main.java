package com.zhixun;

import com.zhixun.util.ReadAttributeInfoData;
import com.zhixun.util.ReadBuslineLocationDescData;
import com.zhixun.util.ReadCommercialCompetitiveEnvData;
import com.zhixun.util.ReadLineData;
import com.zhixun.util.ReadMediaEnvData;
import com.zhixun.util.ReadOwnMediaData;
import com.zhixun.util.ReadSiteInfoData;



public class Main {

	public static int YEAR = 2015;  
	public static void main(String[] args) {

		// 已部署 原始数据入库

//		String cityFilePath = "D:\\tmp\\test\\城市基本信息.xlsx";
//		new ReadCityData().execExcelData(cityFilePath);
		
//		String lineFilePath = "D:\\tmp\\test\\线路基本信息-上海.xlsx";
//		new ReadLineData().execExcelData(lineFilePath);
		
		
		String ownFilePath = "D:\\tmp\\test\\P4-公司自有媒体资源信息.xlsx";
		System.out.println("-------------------->P4-公司自有媒体资源信息<-------------------------");
		new ReadOwnMediaData().execExcelData(ownFilePath);

		System.exit(1);
		
		String mediaEnvFilePath = "D:\\tmp\\test\\P5-媒介环境基本信息.xlsx";
		System.out.println("-------------------->P5-媒介环境基本信息<-------------------------");
		new ReadMediaEnvData().execExcelData(mediaEnvFilePath);
		

		String commercialCompetitiveEnvFilePath = "D:\\tmp\\test\\P6-商业竞争环境信息.xlsx";
		System.out.println("-------------------->P6-商业竞争环境信息<-------------------------");
		new ReadCommercialCompetitiveEnvData().execExcelData(commercialCompetitiveEnvFilePath);
		
		String siteInfoFilePath = "D:\\tmp\\test\\P7-公交线路站点信息-上海.xlsx";
		System.out.println("-------------------->P7-公交线路站点信息-上海<-------------------------");
		new ReadSiteInfoData().execExcelData(siteInfoFilePath);
		
		String attrFilePath = "D:\\tmp\\test\\P8-公交线路属性信息-上海.xlsx";
		System.out.println("-------------------->P8-公交线路属性信息-上海<-------------------------");
		new ReadAttributeInfoData().execExcelData(attrFilePath);

		String buslineLocationDescPath = "D:\\tmp\\test\\P9-公交线路定位描述-上海.xlsx";
		System.out.println("-------------------->P9-公交线路定位描述-上海<-------------------------");
		new ReadBuslineLocationDescData().execExcelData(buslineLocationDescPath);
		
	} 
}
