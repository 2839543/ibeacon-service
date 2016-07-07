package com.zhixun.service;

import java.util.List;

import com.zhixun.dao.CityDao;
import com.zhixun.dao.MediaEnvDao;
import com.zhixun.model.MediaEnvModel;

public class MediaEnvService {

	MediaEnvDao mediaEnvDao = new MediaEnvDao();
	CityDao cityDao =new CityDao();

	public void updateInfo(List<MediaEnvModel> list) {
		int i = 0;
		int insertRow = 0;
		for (MediaEnvModel model : list) {
			
			//获取 cityId
			Integer cityId =  cityDao.selectCityidByParam(model.getCity());
			System.out.println("city ==>" + model.getCity());
			model.setCityId(cityId);
			
			//数据库中是否存在该条记录 
			Integer  id = mediaEnvDao.selectIdByCityParam(model.getCityId());
			
			if(id == 0 ){
				//插入数据
				mediaEnvDao.insertOne(model);
				System.out.println("媒介环境基本信息插入数据：条数=" +  ++insertRow);
			}else {
				//更新数据
				mediaEnvDao.update(model);
				System.out.println("媒介环境基本信息更新数据：条数=" +  ++insertRow);
			}
		}
		System.out.println("媒介环境基本信息插入行数：" + insertRow);
	}
}
