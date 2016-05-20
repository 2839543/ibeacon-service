package com.ibeacon.service;

import java.util.List;

import com.ibeacon.dao.CityDao;
import com.ibeacon.dao.PrimaryDataDao;
import com.ibeacon.model.CityModel;

public class CityService {

	CityDao cityDao = new CityDao();
	PrimaryDataDao primaryDao = new PrimaryDataDao();

	public void updateCity() {
		List<CityModel> cityList = primaryDao.selectListCityName();
		int insertRow = 0;
		int updateRow = 0;
		for (CityModel city : cityList) {
			String city_name = city.getName();
			Integer city_Id = cityDao.selectCityidByName(city_name);
			if (0 != city_Id) {
				updateRow++;
				int line = primaryDao.updateCityid(city_Id, city_name);
				if (0 == line) {
					primaryDao.updateStatusByCityname(city_name);
				}
			} else {
				int iLine = cityDao.insertOne(city);
				if (1 == iLine) {
					insertRow ++;
					Integer cityId = cityDao.selectCityidByName(city_name);
					if (0 != cityId) {
						int line = primaryDao.updateCityid(cityId, city_name);
						if (0 == line) {
							primaryDao.updateStatusByCityname(city_name);
						}
					}
				}
			}
		}
		System.out.println("城市插入行数："+insertRow); 
		System.out.println("城市已存在行数（默认为1）："+updateRow); 
	}
}
