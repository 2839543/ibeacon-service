package com.ibeacon.dao;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import com.ibeacon.model.BusModel;
import com.ibeacon.model.BuscompanyModel;
import com.ibeacon.model.CityModel;
import com.ibeacon.model.IbeaconModel;
import com.ibeacon.model.LineModel;
import com.ibeacon.model.PrimaryDataModel;

/**
 * 操作primary_data表
 * @author shangyu
 *
 */
public class PrimaryDataDao extends BaseDao {
	
	
	/**
	 * @param cityList
	 * @return
	 */
	public int[] batchInsert(List<PrimaryDataModel> dataList,PrimaryDataModel primaryModel) {
		int[] lineArr = null;
		final int batchSize = 50;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT IGNORE INTO primary_data(city_name,line_name,bus_company,bus_license,bus_self_num,install_pos,install_time,factory,model,serial_num,device_id,comment,time,status,isproc) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?)";
			pre = con.prepareStatement(sql);
			for (PrimaryDataModel primary : dataList) {
				if(primaryModel!=null){
					if(primary.getCity_name()==null||"".equals(primary.getCity_name())){ 
						primary.setCity_name(primaryModel.getCity_name()); 
					}
//					System.out.println("primary.getBus_license()==null--"+(primary.getBus_license()==null)); 
//					System.out.println("\"\".equals(primary.getBus_license())--"+("".equals(primary.getBus_license()))); 
					if(primary.getBus_license()==null||"".equals(primary.getBus_license())){
						primary.setBus_license(primaryModel.getBus_license()); 
					}
				}
				pre.setString(1, primary.getCity_name());
				pre.setString(2, primary.getLine_name());
				pre.setString(3, primary.getBus_company());
				pre.setString(4, primary.getBus_license());
				pre.setString(5, primary.getBus_self_num());
				pre.setString(6, primary.getInstall_pos());
				pre.setInt(7, 0);
//				pre.setInt(7, primary.getInstall_time());
				pre.setString(8, primary.getFactory());
				pre.setString(9, primary.getModel());
				pre.setString(10, primary.getSerial_num());
				//System.out.println(primary.getDevice_id()); 
				pre.setInt(11, primary.getDevice_id());
				pre.setString(12, primary.getComment());
				pre.setInt(13, 0);
				pre.setInt(14, 0);
				pre.addBatch();

				if (++count % batchSize == 0) {
					lineArr = pre.executeBatch();
				}
			}
//			//System.out.println("primarySql:"+sql);
			lineArr = pre.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return lineArr;
	}

	/**
	 * select cityName
	 */
	public List<CityModel> selectListCityName() {
		List<CityModel> cityList = new ArrayList<CityModel>();
		this.connect();
		try {
			String sql = "select city_name from primary_data where status=0 and isproc=0 group by city_name";
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			res.beforeFirst();
			while (res.next()) {
				CityModel city = new CityModel();
				city.setName(res.getString("city_name"));
				cityList.add(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return cityList;
	}

	/**
	 * @return buscompany name
	 */
	public List<BuscompanyModel> selectListBuscompanyName() {
		List<BuscompanyModel> busCompanyList = new ArrayList<BuscompanyModel>();
		this.connect();
		try {
			String sql = "select bus_company,city_id from primary_data where status=0 and isproc=0 group by bus_company";
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			res.beforeFirst();
			while (res.next()) {
				BuscompanyModel buscompany = new BuscompanyModel();
				buscompany.setName(res.getString("bus_company"));
				buscompany.setCityId(res.getInt("city_id")); 
				busCompanyList.add(buscompany); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return busCompanyList;
	}

	/**
	 * select lineName
	 * @return
	 */
	public List<LineModel> selectListLineName() {
		List<LineModel> lineList = new ArrayList<LineModel>();
		this.connect();
		try {
			String sql = "select bus_company_id,city_id,line_name from primary_data where status=0 and isproc=0 group by city_id,line_name";
			//System.out.println(sql);
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			res.beforeFirst();
			while (res.next()) {
				LineModel line = new LineModel();
				line.setCity_id(res.getInt("city_id"));
				line.setName(res.getString("line_name"));
				line.setCompany_id(res.getInt("bus_company_id"));
				lineList.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return lineList;
	}

	/**
	 * select bus information
	 * @return
	 */
	public List<BusModel> selectListBus() {
		List<BusModel> busList = new ArrayList<BusModel>();
		this.connect();
		try {
			String sql = "select line_id,bus_license,bus_self_num,install_time from primary_data where status=0 and isproc=0 group by bus_license";
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			res.beforeFirst();
			while (res.next()) {
				BusModel bus = new BusModel();
				bus.setLine_id(res.getInt("line_id"));
				bus.setLicense(res.getString("bus_license"));
				bus.setSelf_no(res.getString("bus_self_num"));
				bus.setInstall_time(res.getInt("install_time"));
				busList.add(bus);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return busList;
	}

	/**
	 * select ibeacon information
	 * @return
	 */
	public List<IbeaconModel> selectListIbeacon() {
		List<IbeaconModel> ibeaconList = new ArrayList<IbeaconModel>();
		this.connect();
		try {
			String sql = "select bus_id,install_pos,device_id,comment from primary_data where status=0 and isproc=0 group by device_id";
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			res.beforeFirst();
			while (res.next()) {
				IbeaconModel ibeacon = new IbeaconModel();
				ibeacon.setBus_id(res.getInt("bus_id"));
				ibeacon.setInstall_position(res.getString("install_pos"));
				ibeacon.setWx_device_id(res.getInt("device_id"));
				ibeacon.setComment(res.getString("comment"));
				ibeaconList.add(ibeacon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return ibeaconList;
	}

	/**
	 * @param cityId
	 * @param cityName
	 */
	public int updateCityid(Integer cityId, String cityName) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET city_id=? WHERE city_name=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, cityId);
			pre.setString(2, cityName);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param busCompanyId
	 * @param buscompanyName
	 * @return
	 */
	public int updateBuscompanyid(Integer busCompanyId, String buscompanyName) {
		int line = 0;
		this.connect();
		try {
			String sql = "update primary_data set bus_company_id=? where bus_company=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, busCompanyId);
			pre.setString(2, buscompanyName);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param lineId
	 * @param lineName
	 * @return
	 */
	public int updateLineid(Integer lineId, String lineName,Integer cityId) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET line_id=? WHERE line_name=? and city_id=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, lineId);
			pre.setString(2, lineName);
			pre.setInt(3, cityId);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param busId
	 * @param busSelfNum
	 * @return
	 */
	public int updateBusid(Integer busId, String bus_license) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET bus_id=? WHERE bus_license=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, busId);
			pre.setString(2, bus_license);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * TODO do nothing
	 * @param beaconId
	 * @param deviceId
	 * @return
	 */
	public int updateBeaconid(Integer beaconId, String deviceId) {
		int line = 0;
		return line;
	}

	/**
	 * @param status
	 * @param id
	 * @return
	 */
	public int updateStatusById(Integer status, Integer id) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET status=? WHERE id=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, status);
			pre.setInt(2, id);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param status
	 * @param cityName
	 * @return
	 */
	public int updateStatusByCityname(String cityName) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET status=1 WHERE city_name=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, cityName);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}


	/**
	 * @param buscompanyName
	 */
	public int updateStatusByBuscompanyName(String buscompanyName) {
		int line = 0;
		this.connect();
		try {
			String sql = "update primary_data set status=6 where bus_company=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, buscompanyName);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param status
	 * @param lineName
	 * @return
	 */
	public int updateStatusByLineName(String lineName,Integer cityId) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET status=2 WHERE line_name=? and city_id=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, lineName);
			pre.setInt(2, cityId);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param busSelfNum
	 * @return
	 */
	public int updateStatusByBusSelfNum(String busSelfNum) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET status=3 WHERE bus_self_num=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, busSelfNum);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param deviceId
	 * @return
	 */
	public int updateStatusByDeviceId(Integer deviceId) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET status=4 WHERE device_id=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, deviceId);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param cityName
	 * @return
	 */
	public int updateIsprocByCityName(String cityName) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE primary_data SET isproc=1 where city_name=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, cityName);
			//System.out.println("primarySql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

}
