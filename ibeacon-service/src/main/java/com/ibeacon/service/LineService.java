package com.ibeacon.service;

import java.util.List;

import com.ibeacon.dao.LineDao;
import com.ibeacon.dao.PrimaryDataDao;
import com.ibeacon.model.LineModel;

public class LineService {

	LineDao lineDao = new LineDao();
	PrimaryDataDao primaryDao = new PrimaryDataDao();

	public void updateLine() {
		List<LineModel> lineList = primaryDao.selectListLineName();
		int insertRow = 0;
		for (LineModel line : lineList) {
			
			int iLine = lineDao.insertOne(line);
			String lineName = line.getName();
			Integer lineId = lineDao.selectLineidByName(lineName,line.getCity_id());
			if (0 != lineId) {
				insertRow++;
				int lin = primaryDao.updateLineid(lineId, lineName,line.getCity_id());
				if (0 == lin) {
					primaryDao.updateStatusByLineName(lineName,line.getCity_id());
				}
			}
		}
		System.out.println("公交线路插入行数(聚合城市和线路)："+insertRow); 
	}
}
