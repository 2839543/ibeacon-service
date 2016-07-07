package com.zhixun.service;

import java.util.List;

import com.zhixun.dao.CityDao;
import com.zhixun.dao.OwnMediaDao;
import com.zhixun.model.OwnMediaModel;

public class OwnMediaService {

	OwnMediaDao ownMediaDao = new OwnMediaDao();
	CityDao cityDao =new CityDao();

	public void updateInfo(List<OwnMediaModel> list) {
		int i = 0;
		int insertRow = 0;
		for (OwnMediaModel model : list) {
			
			//获取 cityId
			Integer cityId =  cityDao.selectCityidByParam(model.getCity());
			System.out.println("city ==>" + model.getCity());
			model.setCityId(cityId);
			
			//数据库中是否存在该条记录 
			Integer  id = ownMediaDao.selectIdByCityParam(model.getCityId());
			
			if(id == 0 ){
				//插入数据
				ownMediaDao.insertOne(model);
				System.out.println("媒体自有信息插入数据：条数=" +  ++insertRow);
			}else {
				//更新数据
				ownMediaDao.update(model);
				System.out.println("媒体自有信息更新数据：条数=" +  ++insertRow);
			}
		}
		System.out.println("媒体自有信息插入行数：" + insertRow);
	}
}
