package com.zhixun.model;

import java.io.Serializable;

/**
 * ibeacon表模型
 * 
 * @author shangyu
 * 
 */
public class IbeaconModel implements Serializable {

	private static final long serialVersionUID = -8419985959014497331L;

	private Integer id;
	private String sn; // 序列号
	private String factory; // 厂家
	private String model; // 型号
	private String uuid;
	private String major;
	private String minor;
	private Integer bus_id; // 车辆ID
	private Integer type; // ibeacon安装位置1表示前2表示后
	private String install_position; // 安装位置
	private String screen_type; // 屏幕类型
	private Integer status; // 状态0是未激活，1激活
	private Integer wx_device_id; // 微信设备id
	private String comment; // 备注

	public IbeaconModel() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public Integer getBus_id() {
		return bus_id;
	}

	public void setBus_id(Integer busId) {
		bus_id = busId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getInstall_position() {
		return install_position;
	}

	public void setInstall_position(String installPosition) {
		install_position = installPosition;
	}

	public String getScreen_type() {
		return screen_type;
	}

	public void setScreen_type(String screenType) {
		screen_type = screenType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getWx_device_id() {
		return wx_device_id;
	}

	public void setWx_device_id(Integer wxDeviceId) {
		wx_device_id = wxDeviceId;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id=");
		sb.append(this.id);
		sb.append(",sn=");
		sb.append(this.sn);
		sb.append(",factory=");
		sb.append(this.factory);
		sb.append(",model=");
		sb.append(this.model);
		sb.append(",uuid=");
		sb.append(this.uuid);
		sb.append(",major=");
		sb.append(this.major);
		sb.append(",minor=");
		sb.append(this.minor);
		sb.append(",bus_id=");
		sb.append(this.bus_id);
		sb.append(",type=");
		sb.append(this.type);
		sb.append(",install_position=");
		sb.append(this.install_position);
		sb.append(",screen_type=");
		sb.append(this.screen_type);
		sb.append(",status=");
		sb.append(this.status);
		sb.append(",wx_device_id=");
		sb.append(this.wx_device_id);

		return sb.toString();
	}
}
