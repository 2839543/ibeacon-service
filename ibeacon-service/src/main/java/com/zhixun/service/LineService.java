package com.zhixun.service;

import java.util.List;

import com.zhixun.dao.CityDao;
import com.zhixun.dao.LineDao;
import com.zhixun.model.LineModel;

public class LineService {

	LineDao lineDao = new LineDao();
	CityDao cityDao = new CityDao();

	public void updateLine(List<LineModel> lineList) {
		int insertRow = 0;
		int updateRow = 0;
		
		
		
		for (LineModel model : lineList) {
			Integer city_id	= cityDao.selectCityidByParam(model.getCityName());
			System.out.println("["+model.getCityName()+"]处理线路："+model.getName());
			model.setCityId(city_id);
			
			Integer line_Id = lineDao.selectLineidByName(model.getName());
			if (0 != line_Id) {
				//update data 
				//updateRow++;
				//int line = cityDao.update(city_Id, city_name);
				//if (0 == line) {
				//	primaryDao.updateStatusByCityname(city_name);
				// }
			} else {
				//insert data
				int iLine = lineDao.insertOne(model);
				if (1 == iLine) {
					insertRow ++; 
				}
			}
		}
		System.out.println("线路插入行数："+insertRow); 
		System.out.println("线路已存在行数（默认为1）："+updateRow); 
	}
}
