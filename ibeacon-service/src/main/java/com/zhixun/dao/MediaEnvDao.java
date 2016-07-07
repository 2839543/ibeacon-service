package com.zhixun.dao;

import java.sql.SQLException;
import java.util.List;

import com.zhixun.model.MediaEnvModel;

/**
 * @author richie.hao :
 * @version 创建时间：2016年7月4日 下午3:23:29 类说明
 */
public class MediaEnvDao extends BaseDao {

	/**
	 * @param busList
	 * @return
	 */
	public int[] batchInsert(List<MediaEnvModel> busList) {
		int[] lineArr = null;
		final int batchSize = 500;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO bus(line_id,license,self_no,car_type,car_status,install_time,install_user) VALUES(?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (MediaEnvModel lst : busList) {
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
	public int insertOne(MediaEnvModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " INSERT INTO media_environment (city_id,male_ratio,women_ratio,metro_line_number,Bus_lanes_mileage,Peak_congestion_delay_index,"
					+ "peak_average_speed,regional_urban_agglomeration,large_enterprises_Relocation_site_info,Regional_policy_characteristics,city_population,"
					+ "Urban_population_coefficient,GDP,vs2015GDP,Economic_performance_coefficient,citybus_totalnum,public_transport_Per_million_people,"
					+ "installed_bus_total,BusTV_install_proportion,Signal_trans_mode,TV_permeability,radio_permeability,movie_permeability,internet_permeability,"
					+ "ooh_permeability,np_permeability,mg_permeability,busLCD_permeability,`busLCD/ooh_effect`,busLCD_Tendency,busLCD_Tendency_coefficient,"
					+ "take_bus_num_ratio,create_time,update_time,`year`) "
					+ " VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ? )  ";
			pre = con.prepareStatement(sql);
			pre.setInt(1, model.getCityId());
			pre.setString(2, model.getMaleRatio());
			pre.setString(3, model.getWomenRatio());
			pre.setString(4, model.getMetroLineNumber());
			pre.setString(5, model.getBusLanesMileage());
			pre.setString(6, model.getPeakCongestionDelayIndex());
			pre.setString(7, model.getPeakAverageSpeed());
			pre.setString(8, model.getRegionalUrbanAgglomeration());
			pre.setString(9, model.getLargeEnterprisesRelocationSiteInfo());
			pre.setString(10,model.getRegionalPolicyCharacteristics());
			pre.setString(11, model.getCityPopulation());
			pre.setString(12, model.getUrbanPopulationCoefficient());
			pre.setString(13, model.getGDP());
			pre.setString(14, model.getVs2015GDP());
			pre.setString(15, model.getEconomicPerformanceCoefficient());
			pre.setString(16, model.getCitybusTotalnum());
			pre.setString(17, model.getPublicTransportPerMillionPeople());
			pre.setString(18, model.getInstalledBusTotal());
			pre.setString(19, model.getBusTVInstallProportion());
			pre.setString(20,model.getSignalTransMode());
			pre.setString(21, model.getTVPermeability());
			pre.setString(22, model.getRadioPermeability());
			pre.setString(23, model.getMoviePermeability());
			pre.setString(24, model.getInternetPermeability());
			pre.setString(25, model.getOohPermeability());
			pre.setString(26, model.getNpPermeability());
			pre.setString(27, model.getMgPermeability());
			pre.setString(28, model.getBusLCDPermeability());
			pre.setString(29, model.getBusLCDoohEffect());
			pre.setString(30, model.getBusLCDTendency());
			pre.setString(31,model.getBusLCDTendencyCoefficient());
			pre.setString(32, model.getTakeBusNumRatio());
			pre.setDate(33,new java.sql.Date(model.getCreatetime().getTime()));
			pre.setDate(34, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(35, model.getYear());
			 
			// System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	public int update(MediaEnvModel model) {
		int line = 0;
		this.connect();
		try {
			String sql = " update  media_environment  set   male_ratio = ? ,women_ratio = ? ,metro_line_number = ? ,Bus_lanes_mileage = ? ,Peak_congestion_delay_index = ? ,"
					+ "peak_average_speed = ? ,regional_urban_agglomeration = ? ,large_enterprises_Relocation_site_info = ? ,Regional_policy_characteristics = ? ,city_population = ? ,"
					+ "Urban_population_coefficient = ? ,GDP = ? ,vs2015GDP = ? ,Economic_performance_coefficient = ? ,citybus_totalnum = ? ,public_transport_Per_million_people = ? ,"
					+ "installed_bus_total = ? ,BusTV_install_proportion = ? ,Signal_trans_mode = ? ,TV_permeability = ? ,radio_permeability = ? ,movie_permeability = ? ,internet_permeability = ? ,"
					+ "ooh_permeability = ? ,np_permeability = ? ,mg_permeability = ? ,busLCD_permeability = ? ,`busLCD/ooh_effect` = ? ,busLCD_Tendency = ? ,busLCD_Tendency_coefficient = ? ,"
					+ "take_bus_num_ratio = ? ,update_time = ?   "
					+ " where city_id = ?    ";
			// + " and `year` = ? ";
			pre = con.prepareStatement(sql);
 
			pre.setString(1, model.getMaleRatio());
			pre.setString(2, model.getWomenRatio());
			pre.setString(3, model.getMetroLineNumber());
			pre.setString(4, model.getBusLanesMileage());
			pre.setString(5, model.getPeakCongestionDelayIndex());
			pre.setString(6, model.getPeakAverageSpeed());
			pre.setString(7, model.getRegionalUrbanAgglomeration());
			pre.setString(8, model.getLargeEnterprisesRelocationSiteInfo());
			pre.setString(9,model.getRegionalPolicyCharacteristics());
			pre.setString(10, model.getCityPopulation());
			pre.setString(11, model.getUrbanPopulationCoefficient());
			pre.setString(12, model.getGDP());
			pre.setString(13, model.getVs2015GDP());
			pre.setString(14, model.getEconomicPerformanceCoefficient());
			pre.setString(15, model.getCitybusTotalnum());
			pre.setString(16, model.getPublicTransportPerMillionPeople());
			pre.setString(17, model.getInstalledBusTotal());
			pre.setString(18, model.getBusTVInstallProportion());
			pre.setString(19,model.getSignalTransMode());
			pre.setString(20 , model.getTVPermeability());
			pre.setString(21, model.getRadioPermeability());
			pre.setString(22, model.getMoviePermeability());
			pre.setString(23, model.getInternetPermeability());
			pre.setString(24, model.getOohPermeability());
			pre.setString(25, model.getNpPermeability());
			pre.setString(26, model.getMgPermeability());
			pre.setString(27, model.getBusLCDPermeability());
			pre.setString(28, model.getBusLCDoohEffect());
			pre.setString(29, model.getBusLCDTendency());
			pre.setString(30,model.getBusLCDTendencyCoefficient());
			pre.setString(31, model.getTakeBusNumRatio());
			pre.setDate(32, new java.sql.Date(model.getUpdatetime().getTime()));
			pre.setInt(33, model.getCityId());
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
			String sql = " SELECT id from media_environment where  city_id = ?   ";
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
