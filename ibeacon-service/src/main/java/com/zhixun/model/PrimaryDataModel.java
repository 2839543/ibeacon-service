package com.zhixun.model;

/**
 * primary_data表模型
 * 
 * @author shangyu
 * 
 */
public class PrimaryDataModel {

	private Integer id;
	private String city_name; // 城市
	private String bus_company; // 公交公司
	private String line_name; // 线路
	private String bus_license; // 车牌号
	private String bus_self_num; // 车辆自编号
	private String factory; // ibeacon厂家
	private String model; // ibeacon型号
	private String serial_num; // ibeacon序列号
	private Integer device_id; // 设备ID
	private String install_pos; // 安装位置
	private Integer install_time; // 安装日期
	private Integer city_id; // 城市id;
	private Integer line_id; // 线路ID
	private Integer bus_id; // 车辆ID
	private Integer status; // 0-默认原始数据；1-city数据有误；2-line数据有误；3-bus数据有误；4-ibeacon数据有误；5-ibeacon设备丢失
	private String comment; // 备注信息
	private Integer isproc;
	private Integer bus_company_id;
	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String cityName) {
		city_name = cityName;
	}

	public String getBus_company() {
		return bus_company;
	}

	public void setBus_company(String busCompany) {
		bus_company = busCompany;
	}

	public String getLine_name() {
		return line_name;
	}

	public void setLine_name(String lineName) {
		line_name = lineName;
	}

	public String getBus_license() {
		return bus_license;
	}

	public void setBus_license(String busLicense) {
		bus_license = busLicense;
	}

	public String getBus_self_num() {
		return bus_self_num;
	}

	public void setBus_self_num(String busSelfNum) {
		bus_self_num = busSelfNum;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(String serialNum) {
		serial_num = serialNum;
	}

	public Integer getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Integer deviceId) {
		device_id = deviceId;
	}

	public String getInstall_pos() {
		return install_pos;
	}

	public void setInstall_pos(String installPos) {
		install_pos = installPos;
	}

	public Integer getInstall_time() {
		return install_time;
	}

	public void setInstall_time(Integer installTime) {
		install_time = installTime;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer cityId) {
		city_id = cityId;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer lineId) {
		line_id = lineId;
	}

	public Integer getBus_id() {
		return bus_id;
	}

	public void setBus_id(Integer busId) {
		bus_id = busId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getIsproc() {
		return isproc;
	}

	public void setIsproc(Integer isproc) {
		this.isproc = isproc;
	}

	public Integer getBus_company_id() {
		return bus_company_id;
	}

	public void setBus_company_id(Integer bus_company_id) {
		this.bus_company_id = bus_company_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
