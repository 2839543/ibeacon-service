package com.zhixun.dao;


import java.util.List;

import com.zhixun.model.LineModel;

/**
 * 操作line表
 * @author richie.hao
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
			String sql = "INSERT IGNORE INTO b_line(name) VALUES(?)";
			pre = con.prepareStatement(sql);
			for (LineModel line : lineList) {
				pre.setString(1, line.getName());
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
			String sql = "INSERT IGNORE INTO b_line(name) VALUES( ? )";
			pre = con.prepareStatement(sql);
			pre.setString(1, line.getName()); 
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
	public Integer selectLineidByName(String lineName) {
		Integer lineId = 0;
		this.connect();
		try {
			String sql = "SELECT id FROM b_line WHERE name=? ";
			pre = con.prepareStatement(sql);
			pre.setString(1, lineName);
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
