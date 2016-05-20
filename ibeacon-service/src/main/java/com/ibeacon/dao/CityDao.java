package com.ibeacon.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibeacon.model.CityModel;

/**
 * 操作city表
 * @author shangyu
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
			String sql = "INSERT INTO city(name,type) VALUES(?,?)";
			pre = con.prepareStatement(sql);
			for (CityModel city : cityList) {
				pre.setString(1, city.getName());
				pre.setInt(2, city.getType());
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
			String sql = "INSERT INTO city(name) VALUES(?)";
			pre = con.prepareStatement(sql);
			pre.setString(1, city.getName());
//			System.out.println("citySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param cityName
	 * @return
	 */
	public Integer selectCityidByName(String cityName) {
		Integer cityId = 0;
		this.connect();
		try {
			String sql = "SELECT id FROM city WHERE name=? and parent_id>0";
			pre = con.prepareStatement(sql);
			pre.setString(1, cityName);
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
