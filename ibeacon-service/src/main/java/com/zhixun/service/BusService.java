package com.zhixun.service;

import java.util.List;

import com.ibeacon.dao.BusDao;
import com.ibeacon.dao.PrimaryDataDao;
import com.ibeacon.model.BusModel;

public class BusService {

	BusDao busDao = new BusDao();
	PrimaryDataDao primaryDao = new PrimaryDataDao();

	public void updateBus() {
		List<BusModel> busList = primaryDao.selectListBus();
		int i=0;
		int insertRow = 0;
		for (BusModel bus : busList) {
			int iLine = busDao.insertOne(bus);

			String bus_license = bus.getLicense();
			Integer busId = busDao.selectBusidBySelfNum(bus_license);
			if (0 != busId) {
				insertRow++;
				int line = primaryDao.updateBusid(busId, bus_license);
				if (0 == line) {
					primaryDao.updateStatusByBusSelfNum(bus_license);
				}
			}
//			System.out.println("处理车辆:"+i++);
		}
		System.out.println("公交车辆插入行数(依据车牌号聚合)："+insertRow); 
	}
}
