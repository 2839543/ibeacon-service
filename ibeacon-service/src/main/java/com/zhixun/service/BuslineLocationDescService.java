package com.zhixun.service;

import java.util.List;

import com.zhixun.dao.BuslineLocationDescDao;
import com.zhixun.dao.LineDao;
import com.zhixun.model.BuslineLocationDescModel;

public class BuslineLocationDescService {

	BuslineLocationDescDao buslineLocationDescDao = new BuslineLocationDescDao();
	LineDao lineDao = new LineDao();

	public void updateInfo(List<BuslineLocationDescModel> list) {
		int i = 0;
		int insertRow = 0;
		for (BuslineLocationDescModel model : list) {

			// 获取 cityId
			Integer lineId = lineDao.selectLineidByName(model.getName());
			System.out.println("线路==>" + model.getName());
			model.setLine_id(lineId);
			if (lineId == 0) {
				System.out.println("!!!未找到线路->" + model.getName()+" 跳过ing...");
				continue;
			}
			// 数据库中是否存在该条记录
			Integer id = buslineLocationDescDao.selectIdByLineParam(model.getLine_id());

			
			if (id == 0) {
				// 插入数据
				buslineLocationDescDao.insertOne(model);
				System.out.println("公交线路定位描述插入数据：条数=" + ++insertRow);
			} else {
				
				// 更新数据
				buslineLocationDescDao.update(model);
				System.out.println("公交线路定位描述更新数据：条数=" + ++insertRow);
			}
		}
		System.out.println("公交线路定位描述信息插入行数：" + insertRow);
	}
}
