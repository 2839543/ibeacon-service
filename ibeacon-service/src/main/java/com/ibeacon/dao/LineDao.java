package com.ibeacon.dao;

import java.util.List;

import com.ibeacon.model.LineModel;

/**
 * 操作line表
 * @author shangyu
 *
 */
public class LineDao extends BaseDao {

	/**
	 * @param lineList
	 * @return
	 */
	public int[] batchInsert(List<LineModel> lineList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO line(name,city_id) VALUES(?,?)";
			pre = con.prepareStatement(sql);
			for (LineModel line : lineList) {
				pre.setString(1, line.getName());
				pre.setInt(2, line.getCity_id());
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
	 * @param line
	 * @return
	 */
	public int insertOne(LineModel line) {
		int lin = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO line(name,city_id,company_id) VALUES(?,?,?)";
			pre = con.prepareStatement(sql);
			pre.setString(1, line.getName());
			pre.setInt(2, line.getCity_id());
			pre.setInt(3, line.getCompany_id());
//			System.out.println("lineSql:"+sql);
			lin = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return lin;
	}

	/**
	 * @param lineName
	 * @return
	 */
	public Integer selectLineidByName(String lineName,Integer cityId) {
		Integer lineId = 0;
		this.connect();
		try {
			String sql = "SELECT id FROM line WHERE name=? and city_id=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, lineName);
			pre.setInt(2, cityId);
			res = pre.executeQuery();
			res.beforeFirst();
			if (res.next()) {
				lineId = res.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return lineId;
	}
}
