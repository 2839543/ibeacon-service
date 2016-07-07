package com.zhixun.dao;

import java.sql.SQLException;
import java.util.List; 
import com.zhixun.model.OwnMediaModel;

/**
 * 操作ownMedia表
 * 
 * @author richie.hao
 *
 */
public class OwnMediaDao extends BaseDao {

	/**
	 * @param busList
	 * @return
	 */
	public int[] batchInsert(List<OwnMediaModel> busList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO bus(line_id,license,self_no,car_type,car_status,install_time,install_user) VALUES(?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (OwnMediaModel bus : busList) {
//				pre.setInt(1, bus.getLine_id());
//				pre.setString(2, bus.getLicense());
//				pre.setString(3, bus.getSelf_no());
//				pre.setString(4, bus.getCar_type());
//				pre.setInt(5, bus.getCar_status());
//				pre.setInt(6, bus.getInstall_time());
//				pre.setString(7, bus.getInstall_user());
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
	public int insertOne(OwnMediaModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " INSERT INTO own_media ( city_id,anet_bus_count,bnet_bus_count,all_bus_count,anet_Resource_proportion,all_Resource_proportion,VISN_Resource_proportion,Resources_to_daily,create_time,update_time,`year`) "
					+ " VALUES (  ?, ?, ?, ?, ?,  ?, ?,?, ?, ?,  ?)  ";
			pre = con.prepareStatement(sql);
			pre.setInt(1, model.getCityId());
			pre.setString(2, model.getAnetBusCount());
			pre.setString(3, model.getBnetBusCount());
			pre.setString(4, model.getAllBusCount());
			pre.setString(5, model.getAnetResourceProportion());
			pre.setString(6, model.getAllResourceProportion());
			pre.setString(7, model.getVISNResourceProportion());
			pre.setString(8, model.getResourcesToDaily()); 
			pre.setDate(9,  new java.sql.Date(model.getCreatetime().getTime()));
			pre.setDate(10,new java.sql.Date(model.getUpdatetime().getTime())); 
			pre.setInt(11, model.getYear());
			// System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	public int update(OwnMediaModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " update  own_media  set  anet_bus_count = ?,bnet_bus_count = ?,all_bus_count = ?,anet_Resource_proportion = ?,all_Resource_proportion = ?,VISN_Resource_proportion = ?,Resources_to_daily = ?,update_time  = ? "
					+ " where city_id = ?    ";
					// + " and `year` = ? ";
			pre = con.prepareStatement(sql);
	
			pre.setString(1, model.getAnetBusCount());
			pre.setString(2, model.getBnetBusCount());
			pre.setString(3, model.getAllBusCount());
			pre.setString(4, model.getAnetResourceProportion());
			pre.setString(5, model.getAllResourceProportion());
			pre.setString(6, model.getVISNResourceProportion());
			pre.setString(7, model.getResourcesToDaily());  
			pre.setDate(8,new java.sql.Date(model.getUpdatetime().getTime())); 
			pre.setInt(9, model.getCityId());
			//			 pre.setInt(10, model.getYear());
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
	public Integer selectIdByCityParam(int cityId) {
		Integer Id = 0;
		this.connect();
		try {
			String sql = "  select o.id from own_media  o where o.city_id =  ?   ";
			pre = con.prepareStatement(sql);
			pre.setInt(1, cityId);
//			pre.setInt(2, year);
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
