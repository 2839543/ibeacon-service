package com.zhixun.service;
//package com.ibeacon.service;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import com.ibeacon.dao.BusDao;
//import com.ibeacon.dao.CityDao;
//import com.ibeacon.dao.IbeaconDao;
//import com.ibeacon.dao.LineDao;
//import com.ibeacon.model.BusModel;
//import com.ibeacon.model.CityModel;
//import com.ibeacon.model.DeployedModel;
//import com.ibeacon.model.LineModel;
//
//public class DeployedService {
//	
//	CityDao cityDao = new CityDao();
//	LineDao lineDao = new LineDao();
//	BusDao busDao = new BusDao();
//	IbeaconDao ibeaconDao = new IbeaconDao();
//
//	/**
//	 * @param depList
//	 */
//	public void addDDeployedData(List<DeployedModel> depList) {
//
//		for (DeployedModel deploy : depList) {
//			int line = cityDao.insertOne(new CityModel(deploy.getCityName(), null, 2));
//			if (1 == line) {
//				Integer cityId = cityDao.selectCityidByName(deploy.getCityName());
//				if (0 != cityId) {
//					deploy.setCityId(cityId);
//					int lin = lineDao.insertOne(new LineModel(deploy.getLineName(), cityId));
//					if (1 == lin) {
//						Integer lineId = lineDao.selectLineidByName(deploy.getLineName());
//						if (0 != lineId) {
//							deploy.setLineId(lineId);
//							String install_time = deploy.getInstall_time();
//							Integer install_time_stamp = getTimeStamp(getFormatTime(install_time));
//							int li = busDao.insertOne(new BusModel(lineId, deploy.getLicense(), deploy.getSelfNum(), null, null, install_time_stamp, null));
//							if (1 == li) {
//								Integer busId = busDao.selectBusidBySelfNum(deploy.getSelfNum());
//								if (0 != busId) {
//									int L = ibeaconDao.updateBusId(busId, Integer.parseInt(deploy.getDeviceId()));
//									if (1 == L)
//										continue;
////									else
//										// rollback
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * @param install_time
//	 * @return
//	 */
//	private String getFormatTime(String install_time) {
//		String[] time = install_time.split("\\.");
//		String year = (time[0].length() == 4) ? time[0] : "20".concat(time[0]);
////		String month = (time[1].length() > 1) ? time[1] : "0".concat(time[1]);
////		String day = (time[2].length() > 1) ? time[2] : "0".concat(time[2]);
//		String month = time[1];
//		String day = time[2];
//		
//		return year.concat("-").concat(month).concat("-").concat(day);
//	}
//
//	/**
//	 * @param time
//	 * @return
//	 */
//	private Integer getTimeStamp(String time) {
//		Integer timeStamp = 0;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date dTime = sdf.parse(time);
//			System.out.println(sdf.format(dTime));
//			timeStamp = (int) (dTime.getTime() / 1000);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		return timeStamp;
//	}
//
////	public static void main(String[] args) throws ParseException {
////		DeployedService ds = new DeployedService();
////		System.out.println(ds.getTimeStamp(ds.getFormatTime("15.4.3")));
////		
////		String time = "2015-4-3";
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////		Date dTime = sdf.parse(time);
////		System.out.println(sdf.format(dTime));
////		System.out.println(dTime.getTime()/1000);
////	}
//}
