package com.zhixun.dao;

import java.sql.SQLException;
import java.util.List;

import com.zhixun.model.CityModel;

/**
 * 操作city表
 * @author richie.hao
 *
 */
public class CityDao extends BaseDao {

	/**
	 * @param cityList
	 * @return
	 */
	public int[] batchInsert(List<CityModel> cityList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT INTO b_city(region,province,city,`level`) VALUES ( '?', '?', '?', '?');";
			pre = con.prepareStatement(sql);
			for (CityModel city : cityList) {
				pre.setString(1, city.getRegion());
				pre.setString(2, city.getProvince());
				pre.setString(3, city.getCity());
				pre.setString(4, city.getLevel());
				pre.addBatch();

				if (++count % batchSize == 0) {
					lineArr = pre.executeBatch();
				}
			}
			lineArr = pre.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return lineArr;
	}

	
	/**
	 * @param city
	 * @return
	 */
	public int insertOne(CityModel city) {
		int line = 0;
		this.connect();
		try {
			String sql = " INSERT INTO b_city(region,province,city,`level`) VALUES  ( ?, ?, ?, ?);";
			pre = con.prepareStatement(sql);
			pre.setString(1, city.getRegion());
			pre.setString(2, city.getProvince());
			pre.setString(3, city.getCity());
			pre.setString(4, city.getLevel());
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}



	/**
	 * 
	 * @param region 
	 * @param province
	 * @param city
	 * @return
	 */
	public Integer selectCityidByParam(String region,String province,String city) {
		Integer cityId = 0;
		this.connect();
		try {
			String sql = " select * from b_city where region = ? and province = ? and city = ? ";
			pre = con.prepareStatement(sql);
			pre.setString(1, region);
			pre.setString(2, province);
			pre.setString(3, city);
			res = pre.executeQuery();
			res.beforeFirst();
			if (res.next()) {
				cityId = res.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return cityId;
	}
	
	/**
	 * 
	 * @param region 
	 * @param province
	 * @param city
	 * @return
	 */
	public Integer selectCityidByParam(String city) {
		Integer cityId = 0;
		this.connect();
		try {
			String sql = " select * from b_city where city = ? ";
			pre = con.prepareStatement(sql);
			pre.setString(1, city);
			res = pre.executeQuery();
			res.beforeFirst();
			if (res.next()) {
				cityId = res.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return cityId;
	}
}
