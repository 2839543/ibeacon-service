package com.core.model;

import java.util.Date;

public class BaseEntity {

	private Integer id;
	private Date createtime = new Date();//创建时间
	private Date updatetime =  new Date(); //更新时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdateTime() {
		return updatetime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updatetime = updateTime;
	}
	
}
