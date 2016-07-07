package com.zhixun.dao;

import com.ibeacon.model.BuscompanyModel;


public class BuscompanyDao extends BaseDao {

	public Integer selectBuscompanyIdByName(String buscompanyName,Integer cityId) {
		Integer buscompanyId = 0;
		this.connect();
		try {
			String sql = "select id from bus_company where name=? and city_id=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, buscompanyName);
			pre.setInt(2, cityId);
			res = pre.executeQuery();
			res.beforeFirst();
			if (res.next()) {
				buscompanyId = res.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return buscompanyId;
	}

	/**
	 * @param buscompany
	 * @return
	 */
	public int insertOne(BuscompanyModel buscompany) {
		int line = 0;
		this.connect();
		try {
			String sql = "insert ignore into bus_company(name,city_id) values(?,?)";
			pre = con.prepareStatement(sql);
			pre.setString(1, buscompany.getName());
			pre.setInt(2, buscompany.getCityId());
//			System.out.println("buscompanySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

}
