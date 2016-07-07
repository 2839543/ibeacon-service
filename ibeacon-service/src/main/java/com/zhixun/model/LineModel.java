package com.zhixun.model;

import com.core.model.BaseEntity;

/**
 * line表模型
 * 
 * @author richie.hao
 * 
 */
public class LineModel extends BaseEntity {

	private Integer id;
	private String name; // 线路名称
//	private Integer city_id;
//	private Integer company_id;
//	private Integer del_status;

	public LineModel() {
	}

	 

//	public Integer getCompany_id() {
//		return company_id;
//	}
//
//	public void setCompany_id(Integer companyId) {
//		company_id = companyId;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Integer getCity_id() {
//		return city_id;
//	}
//
//	public void setCity_id(Integer cityId) {
//		city_id = cityId;
//	}
//
//	public Integer getDel_status() {
//		return del_status;
//	}
//
//	public void setDel_status(Integer delStatus) {
//		del_status = delStatus;
//	}

}
