package com.zhixun.model;

/**
 * busline_location_desc表模型
 * 
 * @author richie.hao
 * 
 */
public class BuslineLocationDescModel extends LineModel {

	private Integer line_id;

	private String businessCenter;
	private String trafficHub;
	private String publicEntertainment;
	private String collegeCommunity;
	private String largeStreetCommunity;
	private String largeScienceTechnologyPark;
	private String type;
	private int year;

	public BuslineLocationDescModel() {
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public String getBusinessCenter() {
		return businessCenter;
	}

	public void setBusinessCenter(String businessCenter) {
		this.businessCenter = businessCenter;
	}

	public String getTrafficHub() {
		return trafficHub;
	}

	public void setTrafficHub(String trafficHub) {
		this.trafficHub = trafficHub;
	}

	public String getPublicEntertainment() {
		return publicEntertainment;
	}

	public void setPublicEntertainment(String publicEntertainment) {
		this.publicEntertainment = publicEntertainment;
	}

	public String getCollegeCommunity() {
		return collegeCommunity;
	}

	public void setCollegeCommunity(String collegeCommunity) {
		this.collegeCommunity = collegeCommunity;
	}

	public String getLargeStreetCommunity() {
		return largeStreetCommunity;
	}

	public void setLargeStreetCommunity(String largeStreetCommunity) {
		this.largeStreetCommunity = largeStreetCommunity;
	}

	public String getLargeScienceTechnologyPark() {
		return largeScienceTechnologyPark;
	}

	public void setLargeScienceTechnologyPark(String largeScienceTechnologyPark) {
		this.largeScienceTechnologyPark = largeScienceTechnologyPark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
