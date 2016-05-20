package com.ibeacon.action;

import java.util.List;

import com.ibeacon.model.PrimaryDataModel;
import com.ibeacon.service.BusService;
import com.ibeacon.service.BuscompanyService;
import com.ibeacon.service.CityService;
import com.ibeacon.service.IbeaconService;
import com.ibeacon.service.LineService;
import com.ibeacon.service.PrimaryDataService;

public class TermController {
	public static void execute(List<PrimaryDataModel> primaryList,PrimaryDataModel primary){
		System.out.println("行数："+primaryList.size());
		if(primaryList!=null&&primaryList.size()>0){
		// import data
		PrimaryDataService primaryService = new PrimaryDataService();
		primaryService.firstAddData(primaryList,primary);

		// update city
		CityService cityService = new CityService();
		cityService.updateCity();

		// update bus_company
		BuscompanyService busCompanyService = new BuscompanyService();
		busCompanyService.updateBuscompany();

		// update line
		LineService lineService = new LineService();
		lineService.updateLine();
	
		// update bus
		BusService busService = new BusService();
		busService.updateBus();
	
		// update ibeacon
		IbeaconService ibeaconService = new IbeaconService();
		ibeaconService.updateIbeacon();

		// update primary data
//		PrimaryDataService primaryService = new PrimaryDataService();
		String cityName = primaryList.get(0).getCity_name();
		int line = primaryService.updateData(cityName );//+ "市"
		System.out.println(line);
		System.out.println("---------------------------------------------");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
