package com.zhixun.service;

import java.util.List;

import com.zhixun.dao.AttributeInfoDao;
import com.zhixun.dao.LineDao;
import com.zhixun.model.AttributeInfoModel;

public class AttributeInfoService {

	AttributeInfoDao attributeInfoDao = new AttributeInfoDao();
	LineDao lineDao =new LineDao();

	public void updateBus(List<AttributeInfoModel> list) {
		int i = 0;
		int insertRow = 0;
		for (AttributeInfoModel model : list) {
			
			//获取 lineid
			Integer lineId =  lineDao.selectLineidByName(model.getLineName());
			System.out.println("线路==>" + model.getLineName());
			model.setLineId(lineId);
			if (lineId == 0) {
				System.out.println("!!!未找到线路->" + model.getLineName()+" 跳过ing...");
				continue;
			}
			//数据库中是否存在该条记录
			Integer  id = attributeInfoDao.selectIdByLinename(model.getLineName());
			
			if(id == 0 ){
				//插入数据
				attributeInfoDao.insertOne(model);
				System.out.println("属性信息插入数据：条数=" +  ++insertRow);
			}else {
				//更新数据
				attributeInfoDao.update(model);
				System.out.println("属性信息更新数据：条数=" +  ++insertRow);
			}
		}
		System.out.println("属性信息插入行数：" + insertRow);
	}
}
