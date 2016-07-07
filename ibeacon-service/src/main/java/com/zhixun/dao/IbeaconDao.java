package com.zhixun.dao;

import java.util.List;

import com.ibeacon.model.IbeaconModel;

/**
 * 操作ibeacon表
 * @author shangyu
 *
 */
public class IbeaconDao extends BaseDao {

	/**
	 * 批量添加数据
	 * @param ibeaconList
	 * @return
	 */
	public int[] addData(List<IbeaconModel> ibeaconList) {
		int[] line = null;
		final int batchSize = 1000;
		int count = 0;
		this.connect();
		try {
			String sql = "INSERT INTO ibeacon(factory,model,wx_device_id,uuid,major,minor,sn) VALUES (?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql);
			for (IbeaconModel data : ibeaconList) {
				pre.setString(1, data.getFactory());
				pre.setString(2, data.getModel());
				pre.setInt(3, data.getWx_device_id());
				pre.setString(4, data.getUuid());
				pre.setString(5, data.getMajor());
				pre.setString(6, data.getMinor());
				pre.setString(7, data.getSn());

				pre.addBatch();
				if (++count % batchSize == 0) {
					line = pre.executeBatch();
				}
			}
//			System.out.println("busSql:"+sql);
			line = pre.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * 查找是否存在某条记录
	 * @param model
	 * @return 0-不存在；其它-存在
	 */
	public int findIbeacon(IbeaconModel model) {
		int id = 0;
		this.connect();
		try {
			String sql = "SELECT id FROM ibeacon WHERE uuid=? AND major=? AND minor=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, model.getUuid());
			pre.setString(2, model.getMajor());
			pre.setString(3, model.getMinor());
			res = pre.executeQuery();
			res.beforeFirst();
			if (res.next()) {
				id = res.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return id;
	}

	/**
	 * 更新一条记录
	 * @param model
	 * @param id
	 * @return 1-成功；0-失败
	 */
	public int updateDataSn(IbeaconModel model, Integer id) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE ibeacon SET sn=? WHERE id=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, model.getSn());
			pre.setInt(2, id);
//			System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

	/**
	 * @param busid
	 * @param wx_device_id
	 * @return
	 */
	public int updateBusData(IbeaconModel ibeacon) {
		int line = 0;
		this.connect();
		try {
			String sql = "UPDATE ibeacon SET bus_id=?,install_position=?,comment=? WHERE wx_device_id=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, ibeacon.getBus_id());
			pre.setString(2, ibeacon.getInstall_position());
			pre.setString(3, ibeacon.getComment());
			pre.setInt(4, ibeacon.getWx_device_id());
//			System.out.println("busSql:"+sql);
			line = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return line;
	}

}
