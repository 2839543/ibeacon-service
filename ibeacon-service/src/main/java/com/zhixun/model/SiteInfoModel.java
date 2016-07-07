package com.zhixun.model;

/**
 * Site_info表模型
 * 
 * @author richie.hao
 * 
 */
public class SiteInfoModel extends LineModel {

	private Integer line_id;
	private String busCount;
	private String screenCount;
	private String allSiteName;
	private String onewayTime;
	private String lineBuildings;
	private int year;

	public SiteInfoModel() {
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}
	 
	public String getBusCount() {
		return busCount;
	}

	public void setBusCount(String busCount) {
		this.busCount = busCount;
	}

	public String getScreenCount() {
		return screenCount;
	}

	public void setScreenCount(String screenCount) {
		this.screenCount = screenCount;
	}

	public String getAllSiteName() {
		return allSiteName;
	}

	public void setAllSiteName(String allSiteName) {
		this.allSiteName = allSiteName;
	}

	public String getOnewayTime() {
		return onewayTime;
	}

	public void setOnewayTime(String onewayTime) {
		this.onewayTime = onewayTime;
	}

	public String getLineBuildings() {
		return lineBuildings;
	}

	public void setLineBuildings(String lineBuildings) {
		this.lineBuildings = lineBuildings;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
