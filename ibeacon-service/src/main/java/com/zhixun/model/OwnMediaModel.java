package com.zhixun.model;

/**
 * own_media表模型
 * 
 * @author richie.hao
 * 
 */
public class OwnMediaModel extends CityModel {

	private Integer cityId;
	private String anetBusCount;
	private String bnetBusCount;
	private String allBusCount;
	private String anetResourceProportion;
	private String allResourceProportion;
	private String VISNResourceProportion;
	private String ResourcesToDaily; 
	private int year; 
	

	public OwnMediaModel() {
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getAnetBusCount() {
		return anetBusCount;
	}

	public void setAnetBusCount(String anetBusCount) {
		this.anetBusCount = anetBusCount;
	}

	public String getBnetBusCount() {
		return bnetBusCount;
	}

	public void setBnetBusCount(String bnetBusCount) {
		this.bnetBusCount = bnetBusCount;
	}

	public String getAllBusCount() {
		return allBusCount;
	}

	public void setAllBusCount(String allBusCount) {
		this.allBusCount = allBusCount;
	}

	public String getAnetResourceProportion() {
		return anetResourceProportion;
	}

	public void setAnetResourceProportion(String anetResourceProportion) {
		this.anetResourceProportion = anetResourceProportion;
	}

	public String getAllResourceProportion() {
		return allResourceProportion;
	}

	public void setAllResourceProportion(String allResourceProportion) {
		this.allResourceProportion = allResourceProportion;
	}

	public String getVISNResourceProportion() {
		return VISNResourceProportion;
	}

	public void setVISNResourceProportion(String vISNResourceProportion) {
		VISNResourceProportion = vISNResourceProportion;
	}

	public String getResourcesToDaily() {
		return ResourcesToDaily;
	}

	public void setResourcesToDaily(String resourcesToDaily) {
		ResourcesToDaily = resourcesToDaily;
	} 

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	} 
 
}
