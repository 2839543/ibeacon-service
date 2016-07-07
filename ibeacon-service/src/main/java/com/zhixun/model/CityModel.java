package com.zhixun.model;

import com.core.model.BaseEntity;

/**
 * city表模型
 * @author shangyu
 *
 */
public class CityModel  extends BaseEntity{

	private Integer id;
	private String region; // 区域
	private String province; // 省
	private String city; // 城市
	private String level; //城市等级 一线城市，二线城市

	public CityModel() {
	}
 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	} 

}
