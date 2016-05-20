package com.ces.dao;

import java.sql.SQLException;
import java.util.List;

import com.ces.model.PlayListCesModel;
import com.ibeacon.dao.BaseDao;
import com.ibeacon.model.BusModel;

public class PlayListCesDao extends BaseDao {

	/**
	 * @param busList
	 * @return
	 */
	public int[] batchInsert(List<PlayListCesModel> playList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO tv_play_list_ces(city_id,city_name,sn,play_duration,play_time,name,sub_name,type,play_date,version,fileName,size) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (PlayListCesModel bus : playList) {
				pre.setInt(1, bus.getCity_id());
				pre.setString(2, bus.getCity_name());
				pre.setString(3, bus.getSn());
				pre.setInt(4, bus.getPlay_duration());
				pre.setString(5, bus.getPlay_time());
				pre.setString(6, bus.getName());
				pre.setString(7, bus.getSub_name());
				pre.setInt(8, bus.getType());
				pre.setString(9, bus.getPlay_date());
				pre.setInt(10, bus.getVersion());
				pre.setString(11, bus.getFileName());
				pre.setInt(12, bus.getSize());
				pre.addBatch();

				if (++count % batchSize == 0) {
					lineArr = pre.executeBatch();
				}
			}
			//System.out.println("busSql:"+sql);
			lineArr = pre.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return lineArr;
	}
}
