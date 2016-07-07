package com.zhixun.service;

import java.util.ArrayList;
import java.util.List;

import com.ibeacon.dao.IbeaconDao;
import com.ibeacon.dao.PrimaryDataDao;
import com.ibeacon.model.IbeaconModel;

/**
 * @author shangyu
 *
 */
public class IbeaconService {

	IbeaconDao ibeaDao = new IbeaconDao();
	PrimaryDataDao primaryDao = new PrimaryDataDao();

	/**
	 * ibeacon数据更新导入
	 * @param ibeaconList
	 */
	public void updateIbeaconData(List<IbeaconModel> ibeaconList) {
		List<IbeaconModel> newDataList = new ArrayList<IbeaconModel>();

		for (IbeaconModel model : ibeaconList) {
			int id = ibeaDao.findIbeacon(model);
			if (id > 0) {
				int line = ibeaDao.updateDataSn(model, id);
				if (1 == line) {
					System.out.println(id + " : 更新成功");
				} else {
					System.err.println(id + " : 更新失败");
				}
			} else {
				System.out.println(model.toString());
				newDataList.add(model);
			}
		}
		System.out.println(newDataList.size());
		if (null != newDataList && newDataList.size() > 0) {
			ibeaDao.addData(newDataList);
		}
	}

	/**
	 * ibeacon更新bus_id
	 */
	public void updateIbeacon() {
		List<IbeaconModel> ibeaconList = primaryDao.selectListIbeacon();
		int i=0;
		int insertRow = 0;
		for (IbeaconModel ibeacon : ibeaconList) {
			Integer deviceId = ibeacon.getWx_device_id();
			int iLine = ibeaDao.updateBusData(ibeacon);
			insertRow++;
			if (0 == iLine) {
				primaryDao.updateStatusByDeviceId(deviceId);
			}
//			System.out.println("更新ibeacon："+i++);
		}
		System.out.println("ibeacon修改行数："+insertRow); 
	}

	/**
	 * 空表时导入数据
	 */
	public void firstAddData(List<IbeaconModel> ibeaconList) {
		int[] line = ibeaDao.addData(ibeaconList);
		System.out.println("共新增" + line.length + "条");
	}
}
