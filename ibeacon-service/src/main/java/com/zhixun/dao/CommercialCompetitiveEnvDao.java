package com.zhixun.dao;

import java.sql.SQLException;
import java.util.List;

import com.zhixun.model.CommercialCompetitiveEnvModel;

/**
 * @author richie.hao :
 * @version 创建时间：2016年7月4日 下午3:23:29 类说明
 */
public class CommercialCompetitiveEnvDao extends BaseDao {

	/**
	 * @param busList
	 * @return
	 */
	public int[] batchInsert(List<CommercialCompetitiveEnvModel> busList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO bus(line_id,license,self_no,car_type,car_status,install_time,install_user) VALUES(?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (CommercialCompetitiveEnvModel lst : busList) {
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
	public int insertOne(CommercialCompetitiveEnvModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " INSERT INTO commercial_competitive_env  (city_id,Anet_hang_rate,bnet_Account,top3_customer,bus_Share_proportion,"
					+ "visn_share_proportion,towona_share_proportion, infommtv_share_proportion,bus_City_dist_proportion,visn_City_dist_proportion,"
					+ "towona_City_dist_proportion,infommtv_City_dist_proportion,bus_Brand_num,visn_Brand_num,towona_Brand_num,infommtv_Brand_num,"
					+ "create_time,update_time,`year`) value ( ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,? ) ";
			pre = con.prepareStatement(sql);
			pre.setInt(1, model.getCityId());
			pre.setString(2, model.getAnetHangRate());
			pre.setString(3, model.getBnetAccount());
			pre.setString(4, model.getTop3Customer());
			pre.setString(5, model.getBusShareProportion());
			pre.setString(6, model.getVisnShareProportion());
			pre.setString(7, model.getTowonaShareProportion());
			pre.setString(8, model.getInfommtvShareProportion());
			pre.setString(9, model.getBusCityDistProportion());
			pre.setString(10, model.getVisnCityDistProportion());
			pre.setString(11, model.getTowonaCityDistProportion());
			pre.setString(12, model.getInfommtvCityDistProportion());
			pre.setString(13, model.getBusBrandNum());
			pre.setString(14, model.getVisnBrandNum());
			pre.setString(15, model.getTowonaBrandNum());
			pre.setString(16, model.getInfommtvBrandNum());
			pre.setDate(17, new java.sql.Date(model.getCreatetime().getTime()));
			pre.setDate(18, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(19, model.getYear());

			// System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	public int update(CommercialCompetitiveEnvModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " update  commercial_competitive_env  SET Anet_hang_rate = ? ,bnet_Account = ? ,top3_customer = ? ,"
					+ "bus_Share_proportion = ? ,visn_share_proportion = ? ,towona_share_proportion = ? , infommtv_share_proportion = ? ,"
					+ "bus_City_dist_proportion = ? ,visn_City_dist_proportion = ? ,towona_City_dist_proportion = ? ,infommtv_City_dist_proportion = ? ,"
					+ "bus_Brand_num = ? ,visn_Brand_num = ? ,towona_Brand_num = ? ,infommtv_Brand_num = ? ,update_time = ?   "
					+ "WHERE  city_id = ?  ";
			// + " and `year` = ? ";
			pre = con.prepareStatement(sql);

			pre.setString(1, model.getAnetHangRate());
			pre.setString(2, model.getBnetAccount());
			pre.setString(3, model.getTop3Customer());
			pre.setString(4, model.getBusShareProportion());
			pre.setString(5, model.getVisnShareProportion());
			pre.setString(6, model.getTowonaShareProportion());
			pre.setString(7, model.getInfommtvShareProportion());
			pre.setString(8, model.getBusCityDistProportion());
			pre.setString(9, model.getVisnCityDistProportion());
			pre.setString(10, model.getTowonaCityDistProportion());
			pre.setString(11, model.getInfommtvCityDistProportion());
			pre.setString(12, model.getBusBrandNum());
			pre.setString(13, model.getVisnBrandNum());
			pre.setString(14, model.getTowonaBrandNum());
			pre.setString(15, model.getInfommtvBrandNum());
			pre.setDate(16, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(17, model.getCityId());
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
			String sql = " SELECT id from commercial_competitive_env where  city_id = ?   ";
			pre = con.prepareStatement(sql);
			pre.setInt(1, cityId);
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
