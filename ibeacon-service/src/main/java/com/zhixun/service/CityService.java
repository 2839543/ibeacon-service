package com.zhixun.service;

import java.util.List;

import com.zhixun.dao.CityDao;
import com.zhixun.model.CityModel;

public class CityService {

	CityDao cityDao = new CityDao();

	public void updateCity(List<CityModel> cityList) {
		int insertRow = 0;
		int updateRow = 0;
		for (CityModel city : cityList) {
			Integer city_Id = cityDao.selectCityidByParam(city.getRegion(),city.getProvince(),city.getCity());
			if (0 != city_Id) {
				//update data 
				//updateRow++;
				//int line = cityDao.update(city_Id, city_name);
				//if (0 == line) {
				//	primaryDao.updateStatusByCityname(city_name);
				// }
			} else {
				//insert data
				int iLine = cityDao.insertOne(city);
				if (1 == iLine) {
					insertRow ++; 
				}
			}
		}
		System.out.println("城市插入行数："+insertRow); 
		System.out.println("城市已存在行数（默认为1）："+updateRow); 
	}
}
