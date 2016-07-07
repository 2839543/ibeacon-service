package com.zhixun.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibeacon.model.BusModel;
import com.zhixun.model.AttributeInfoModel;

/**
 * 操作AttributeInfo表
 * 
 * @author richie.hao
 *
 */
public class AttributeInfoDao extends BaseDao {

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
	public int insertOne(AttributeInfoModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " INSERT INTO attribute_info (line_id,one_way_traffic_time,peak_shift_interval,peak_shift_interval_score, "
					+ "through_loop_line,through_area,through_area_score,Subway_line_contact_ratio,through_subway, avg_time_subway,"
					+ "create_time,update_time,type,Can_replaced_subway,`year`) VALUES (  ?, ?, ?, ?, ?,  ?, ?,?, ?, ?,  ?, ?,?,?,?)";
			pre = con.prepareStatement(sql);
			pre.setInt(1, model.getLineId());
			pre.setString(2, model.getOneWayTrafficTime());
			pre.setString(3, model.getPeakShiftInterval());
			pre.setString(4, model.getPeakShiftIntervalScore());
			pre.setString(5, model.getThroughLoopLine());
			pre.setString(6, model.getThroughArea());
			pre.setString(7, model.getThroughAreaScore());
			pre.setString(8, model.getSubwayLineContactRatio());
			pre.setString(9, model.getThroughSubway());
			pre.setString(10, model.getAvgTimeSubway());
			pre.setDate(11,  new java.sql.Date(model.getCreatetime().getTime()));
			pre.setDate(12,new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setString(13, model.getType());
			pre.setString(14, model.getCanReplacedSubway());
			pre.setInt(15, model.getYear());
			// System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	public int update(AttributeInfoModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " update  attribute_info  set one_way_traffic_time = ?,	peak_shift_interval = ?,	peak_shift_interval_score = ?, "
					+ "	through_loop_line = ?,	through_area = ?,	through_area_score = ?,	Subway_line_contact_ratio = ?,	 "
					+ "through_subway = ?,	avg_time_subway = ?, update_time = ?,	type = ?,	Can_replaced_subway  = ? where line_id = ? ";
			pre = con.prepareStatement(sql);
			pre.setString(1, model.getOneWayTrafficTime());
			pre.setString(2, model.getPeakShiftInterval());
			pre.setString(3, model.getPeakShiftIntervalScore());
			pre.setString(4, model.getThroughLoopLine());
			pre.setString(5, model.getThroughArea());
			pre.setString(6, model.getThroughAreaScore());
			pre.setString(7, model.getSubwayLineContactRatio());
			pre.setString(8, model.getThroughSubway());
			pre.setString(9, model.getAvgTimeSubway());
			pre.setDate(10, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setString(11, model.getType());
			pre.setString(12, model.getCanReplacedSubway());
			pre.setInt(13, model.getLineId());
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
	public Integer selectIdByLinename(String linename) {
		Integer Id = 0;
		this.connect();
		try {
			String sql = " select a.id from b_line b ,attribute_info  a where  b.id = a.line_id and  b.name  =  ?  ";
			pre = con.prepareStatement(sql);
			pre.setString(1, linename);
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
