package com.zhixun.dao;

import java.sql.SQLException;
import java.util.List;

import com.zhixun.model.SiteInfoModel;

/**
 * 操作site_info表
 * 
 * @author richie.hao
 *
 */
public class SiteInfoDao extends BaseDao {

	/**
	 * @param busList
	 * @return
	 */
	public int[] batchInsert(List<SiteInfoModel> busList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO bus(line_id,license,self_no,car_type,car_status,install_time,install_user) VALUES(?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (SiteInfoModel bus : busList) {
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
	public int insertOne(SiteInfoModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = "INSERT INTO site_info ( line_id,bus_count,Screen_count,all_site_name,oneway_time,line_buildings,"
					+ "create_time,update_time,`year`)   VALUES (  ?, ?, ?, ?, ?,  ?, ?,?, ?)  ";
			pre = con.prepareStatement(sql);
			pre.setInt(1, model.getLine_id());
			pre.setString(2, model.getBusCount());
			pre.setString(3, model.getScreenCount());
			pre.setString(4, model.getAllSiteName());
			pre.setString(5, model.getOnewayTime());
			pre.setString(6, model.getLineBuildings());
			pre.setDate(7, new java.sql.Date(model.getCreatetime().getTime()));
			pre.setDate(8, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(9, model.getYear());
			// System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	public int update(SiteInfoModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " UPDATE  site_info  SET  bus_count = ?,Screen_count = ?,all_site_name = ?,oneway_time = ?,line_buildings = ?,"
					+ "update_time = ? WHERE  line_id = ?  ";
			pre = con.prepareStatement(sql);

			pre.setString(1, model.getBusCount());
			pre.setString(2, model.getScreenCount());
			pre.setString(3, model.getAllSiteName());
			pre.setString(4, model.getOnewayTime());
			pre.setString(5, model.getLineBuildings());
			pre.setDate(6, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(7, model.getLine_id());
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
			String sql = "  select id from site_info  where line_id =  ?   ";
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
