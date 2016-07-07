package com.zhixun.service;

import java.util.List;

import com.zhixun.dao.CityDao;
import com.zhixun.dao.CommercialCompetitiveEnvDao;
import com.zhixun.model.CommercialCompetitiveEnvModel;

public class CommercialCompetitiveEnvService {

	CommercialCompetitiveEnvDao commercialCompetitiveEnvDao = new CommercialCompetitiveEnvDao();
	CityDao cityDao =new CityDao();

	public void updateInfo(List<CommercialCompetitiveEnvModel> list) {
		int i = 0;
		int insertRow = 0;
		for (CommercialCompetitiveEnvModel model : list) {
			
			//获取 cityId
			Integer cityId =  cityDao.selectCityidByParam(model.getCity());
			System.out.println("city ==>" + model.getCity());
			model.setCityId(cityId);
			
			//数据库中是否存在该条记录 
			Integer  id = commercialCompetitiveEnvDao.selectIdByCityParam(model.getCityId());
			
			if(id == 0 ){
				//插入数据
				commercialCompetitiveEnvDao.insertOne(model);
				System.out.println("商业竞争环境信息插入数据：条数=" +  ++insertRow);
			}else {
				//更新数据
				commercialCompetitiveEnvDao.update(model);
				System.out.println("商业竞争环境信息更新数据：条数=" +  ++insertRow);
			}
		}
		System.out.println("商业竞争环境信息插入行数：" + insertRow);
	}
}
