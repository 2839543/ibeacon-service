package com.zhixun.dao;

import java.sql.SQLException;
import java.util.List;

import com.zhixun.model.BuslineLocationDescModel;

/**
 * 操作busline_location_desc表
 * 
 * @author richie.hao
 *
 */
public class BuslineLocationDescDao extends BaseDao {

	/**
	 * @param busList
	 * @return
	 */
	public int[] batchInsert(List<BuslineLocationDescModel> busList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO bus(line_id,license,self_no,car_type,car_status,install_time,install_user) VALUES(?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (BuslineLocationDescModel bus : busList) {
				// pre.setInt(1, bus.getLine_id());
				// pre.setString(2, bus.getLicense());
				// pre.setString(3, bus.getSelf_no());
				// pre.setString(4, bus.getCar_type());
				// pre.setInt(5, bus.getCar_status());
				// pre.setInt(6, bus.getInstall_time());
				// pre.setString(7, bus.getInstall_user());
				pre.addBatch();

				if (++count % batchSize == 0) {
					lineArr = pre.executeBatch();
				}
			}
			// System.out.println("busSql:"+sql);
			lineArr = pre.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return lineArr;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public int insertOne(BuslineLocationDescModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = "	INSERT INTO busline_location_desc (line_id,business_center,traffic_hub,public_entertainment,college_community,large_street_community,"
					+ "large_science_technology_park,create_time,	update_time,	`year` )   VALUES (  ?, ?, ?, ?, ?,  ?, ?,?, ?, ? )"; 
			pre = con.prepareStatement(sql);
			pre.setInt(1, model.getLine_id());
			pre.setString(2, model.getBusinessCenter());
			pre.setString(3, model.getTrafficHub());
			pre.setString(4, model.getPublicEntertainment());
			pre.setString(5, model.getCollegeCommunity());
			pre.setString(6, model.getLargeStreetCommunity());
			pre.setString(7, model.getLargeScienceTechnologyPark());
			pre.setDate(8, new java.sql.Date(model.getCreatetime().getTime()));
			pre.setDate(9, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(10, model.getYear());
			// System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	public int update(BuslineLocationDescModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " UPDATE  busline_location_desc  SET  business_center = ? ,traffic_hub = ? ,public_entertainment = ? ,college_community = ? ,"
					+ "large_street_community = ? ,large_science_technology_park = ? ,	update_time = ?  WHERE  line_id = ?  ";
			pre = con.prepareStatement(sql);

			pre.setString(1, model.getBusinessCenter());
			pre.setString(2, model.getTrafficHub());
			pre.setString(3, model.getPublicEntertainment());
			pre.setString(4, model.getCollegeCommunity());
			pre.setString(5, model.getLargeStreetCommunity());
			pre.setString(6, model.getLargeScienceTechnologyPark());
			pre.setDate(7, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(8, model.getLine_id());
			// System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param self_no
	 * @return
	 */
	public Integer selectIdByLineParam(int lineId) {
		Integer Id = 0;
		this.connect();
		try {
			String sql = "  select id from busline_location_desc  where line_id =  ?   ";
			pre = con.prepareStatement(sql);
			pre.setInt(1, lineId);
			// pre.setInt(2, year);
			res = pre.executeQuery();
			res.beforeFirst();
			if (res.next()) {
				Id = res.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return Id;
	}
}
