package com.zhixun.action;

import java.util.List;

import com.zhixun.model.AttributeInfoModel;
import com.zhixun.model.CityModel;
import com.zhixun.model.CommercialCompetitiveEnvModel;
import com.zhixun.model.LineModel;
import com.zhixun.model.MediaEnvModel;
import com.zhixun.model.OwnMediaModel;
import com.zhixun.model.SiteInfoModel;
import com.zhixun.model.BuslineLocationDescModel;
import com.zhixun.service.AttributeInfoService;
import com.zhixun.service.BuslineLocationDescService;
import com.zhixun.service.CityService;
import com.zhixun.service.CommercialCompetitiveEnvService;
import com.zhixun.service.LineService;
import com.zhixun.service.MediaEnvService;
import com.zhixun.service.OwnMediaService;
import com.zhixun.service.SiteInfoService;

public class TermController {
	// public static void execute(List<PrimaryDataModel>
	// primaryList,PrimaryDataModel primary){
	// System.out.println("行数："+primaryList.size());
	// if(primaryList!=null&&primaryList.size()>0){
	// // import data
	//// PrimaryDataService primaryService = new PrimaryDataService();
	//// primaryService.firstAddData(primaryList,primary);
	//
	// // update city
	// CityService cityService = new CityService();
	//// cityService.updateCity();
	//
	// // update bus_company
	// BuscompanyService busCompanyService = new BuscompanyService();
	// busCompanyService.updateBuscompany();
	//
	// // update line
	//// LineService lineService = new LineService();
	//// lineService.updateLine();
	//
	// // update bus
	// BusService busService = new BusService();
	// busService.updateBus();
	//
	// // update ibeacon
	// IbeaconService ibeaconService = new IbeaconService();
	// ibeaconService.updateIbeacon();
	//
	// // update primary data
	//// PrimaryDataService primaryService = new PrimaryDataService();
	// String cityName = primaryList.get(0).getCity_name();
	//// int line = primaryService.updateData(cityName );//+ "市"
	//// System.out.println(line);
	// System.out.println("---------------------------------------------");
	// }
	// }

	public static void executeLine(List<LineModel> lineList) {
		LineService lineService = new LineService();
		lineService.updateLine(lineList);
	}

	public static void executeCity(List<CityModel> cityList) {
		CityService cityService = new CityService();
		cityService.updateCity(cityList);
	}

	public static void executeAttributeInfo(List<AttributeInfoModel> list) {
		AttributeInfoService service = new AttributeInfoService();
		service.updateBus(list);
	}

	public static void executeOwnMedia(List<OwnMediaModel> list) {
		OwnMediaService service = new OwnMediaService();
		service.updateInfo(list);
	}

	public static void executeMediaEnv(List<MediaEnvModel> list) {
		MediaEnvService service = new MediaEnvService();
		service.updateInfo(list);
	}

	public static void executeSiteInfo(List<SiteInfoModel> list) {
		SiteInfoService service = new SiteInfoService();
		service.updateInfo(list);
	}

	public static void executeBusLineLocationDesc(List<BuslineLocationDescModel> list) {
		BuslineLocationDescService service = new BuslineLocationDescService();
		service.updateInfo(list);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void executeCommercialCompetitiveEnv(List<CommercialCompetitiveEnvModel> list) {
		CommercialCompetitiveEnvService service = new CommercialCompetitiveEnvService();
		service.updateInfo(list);
	}

}
