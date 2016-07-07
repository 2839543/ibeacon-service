package com.zhixun.service;

import java.util.List;

import com.ibeacon.dao.BuscompanyDao;
import com.ibeacon.dao.PrimaryDataDao;
import com.ibeacon.model.BuscompanyModel;

public class BuscompanyService {
	BuscompanyDao busCompanyDao = new BuscompanyDao();
	PrimaryDataDao primaryDao = new PrimaryDataDao();

	public void updateBuscompany() {
		List<BuscompanyModel> buscompanyList = primaryDao.selectListBuscompanyName();
		int insertRow = 0;
		int updateRow = 0;
		for (BuscompanyModel buscompany : buscompanyList) {
			String buscompanyName = buscompany.getName();
			Integer cityId = buscompany.getCityId();
			Integer bus_company_id = busCompanyDao.selectBuscompanyIdByName(buscompanyName,cityId);
			if (0 != bus_company_id) {
				updateRow++;
				int line = primaryDao.updateBuscompanyid(bus_company_id, buscompanyName);
				if (0 == line) {
					primaryDao.updateStatusByBuscompanyName(buscompanyName);
				}
			} else {
				int iLine = busCompanyDao.insertOne(buscompany);
				if (1 == iLine) {
					insertRow++;
					Integer buscomId = busCompanyDao.selectBuscompanyIdByName(buscompanyName,cityId);
					if (0 != buscomId) {
						int line = primaryDao.updateBuscompanyid(buscomId, buscompanyName);
						if (0 == line) {
							primaryDao.updateStatusByBuscompanyName(buscompanyName);
						}
					}
				}
			}
		}
		System.out.println("公交公司插入行数："+insertRow); 
		System.out.println("公交公司已存在行数（默认为1）："+updateRow); 
	}
}
