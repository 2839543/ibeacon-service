package com.ibeacon.model;

/**
 * city表模型
 * @author shangyu
 *
 */
public class CityModel {

	private Integer id;
	private String name; // 名称
	private Integer parent_id; // 父ID
	private Integer type; // 类型0国家，1省份，2市区，3区县4，乡镇
	private Integer del_status;

	public CityModel() {
	}

	public CityModel(String name, Integer parent_id, Integer type) {
		this.name = name;
		this.parent_id = parent_id;
		this.type = type;
	}

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

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parentId) {
		parent_id = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDel_status() {
		return del_status;
	}

	public void setDel_status(Integer delStatus) {
		del_status = delStatus;
	}

}
