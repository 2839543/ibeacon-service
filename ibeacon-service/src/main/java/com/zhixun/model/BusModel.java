package com.zhixun.model;

/**
 * bus表模型
 * @author shangyu
 *
 */
public class BusModel {

	private Integer id; // 线路名称
	private Integer line_id; // 线路ID
	private String license; // 车牌
	private String self_no; // 车辆自编号
	private String car_type; // 车辆类型
	private Integer car_status; // 车辆状态
	private Integer install_time; // 安装验收日期
	private String install_user; // 安装人
	private Integer del_status; // 删除状态0，默认未删，1已删

	public BusModel() {
	}

	public BusModel(Integer line_id, String license, 
			String self_no, String car_type, Integer car_status, 
			Integer install_time, String install_user) {
		this.line_id = line_id;
		this.license = license;
		this.self_no = self_no;
		this.car_type = car_type;
		this.car_status = car_status;
		this.install_time = install_time;
		this.install_user = install_user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer lineId) {
		line_id = lineId;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getSelf_no() {
		return self_no;
	}

	public void setSelf_no(String selfNo) {
		self_no = selfNo;
	}

	public String getCar_type() {
		return car_type;
	}

	public void setCar_type(String carType) {
		car_type = carType;
	}

	public Integer getCar_status() {
		return car_status;
	}

	public void setCar_status(Integer carStatus) {
		car_status = carStatus;
	}

	public Integer getInstall_time() {
		return install_time;
	}

	public void setInstall_time(Integer installTime) {
		install_time = installTime;
	}

	public Integer getDel_status() {
		return del_status;
	}

	public void setDel_status(Integer delStatus) {
		del_status = delStatus;
	}

	public String getInstall_user() {
		return install_user;
	}

	public void setInstall_user(String installUser) {
		install_user = installUser;
	}

}
