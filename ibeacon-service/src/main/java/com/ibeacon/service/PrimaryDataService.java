package com.ibeacon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibeacon.dao.PrimaryDataDao;
import com.ibeacon.model.PrimaryDataModel;

public class PrimaryDataService {

	PrimaryDataDao primaryDao = new PrimaryDataDao();

	/**
	 * @param dataList
	 */
	public void firstAddData(List<PrimaryDataModel> dataList,PrimaryDataModel primary) {
		Map map = new HashMap();
		map.put("id", false);
		primaryDao.createTable("primary_data",new PrimaryDataModel(),map);
		primaryDao.batchInsert(dataList,primary);
	}

	/**
	 * 对已处理的数据更新状态
	 * @param cityName
	 */
	public int updateData(String cityName) {
		return primaryDao.updateIsprocByCityName(cityName);
	}
}
