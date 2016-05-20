package com.ibeacon.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibeacon.model.BusModel;

/**
 * 操作bus表
 * @author shangyu
 *
 */
public class BusDao extends BaseDao {

	/**
	 * @param busList
	 * @return
	 */
	public int[] batchInsert(List<BusModel> busList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO bus(line_id,license,self_no,car_type,car_status,install_time,install_user) VALUES(?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (BusModel bus : busList) {
				pre.setInt(1, bus.getLine_id());
				pre.setString(2, bus.getLicense());
				pre.setString(3, bus.getSelf_no());
				pre.setString(4, bus.getCar_type());
				pre.setInt(5, bus.getCar_status());
				pre.setInt(6, bus.getInstall_time());
				pre.setString(7, bus.getInstall_user());
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

	/**
	 * @param bus
	 * @return
	 */
	public int insertOne(BusModel bus) {
		int line = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO bus(line_id,license,self_no,install_time) VALUES(?,?,?,?)";
			pre = con.prepareStatement(sql);
			pre.setInt(1, bus.getLine_id());
			pre.setString(2, bus.getLicense());
			pre.setString(3, bus.getSelf_no());
			pre.setInt(4, bus.getInstall_time());
			//System.out.println("busSql:"+sql);
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
	public Integer selectBusidBySelfNum(String self_no) {
		Integer busId = 0;
		this.connect();
		try {
			String sql = "SELECT id FROM bus WHERE license=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, self_no);
			res = pre.executeQuery();
			res.beforeFirst();
			if (res.next()) {
				busId = res.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return busId;
	}
}
