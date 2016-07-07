package com.zhixun.model;

import com.core.model.BaseEntity;

/**
 * attribute_info表模型
 * 
 * @author richie.hao
 * 
 */
public class AttributeInfoModel extends BaseEntity {

	private Integer lineId;
	private String lineName;
	private String oneWayTrafficTime;
	private String peakShiftInterval;
	private String peakShiftIntervalScore;
	private String throughLoopLine;
	private String throughArea;
	private String throughAreaScore;
	private String subwayLineContactRatio;
	private String throughSubway;
	private String avgTimeSubway;
	private String canReplacedSubway;
	private String type;
	private int year;

	public AttributeInfoModel() {
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getOneWayTrafficTime() {
		return oneWayTrafficTime;
	}

	public void setOneWayTrafficTime(String oneWayTrafficTime) {
		this.oneWayTrafficTime = oneWayTrafficTime;
	}

	public String getPeakShiftInterval() {
		return peakShiftInterval;
	}

	public void setPeakShiftInterval(String peakShiftInterval) {
		this.peakShiftInterval = peakShiftInterval;
	}

	public String getPeakShiftIntervalScore() {
		return peakShiftIntervalScore;
	}

	public void setPeakShiftIntervalScore(String peakShiftIntervalScore) {
		this.peakShiftIntervalScore = peakShiftIntervalScore;
	}

	public String getThroughLoopLine() {
		return throughLoopLine;
	}

	public void setThroughLoopLine(String throughLoopLine) {
		this.throughLoopLine = throughLoopLine;
	}

	public String getThroughArea() {
		return throughArea;
	}

	public void setThroughArea(String throughArea) {
		this.throughArea = throughArea;
	}

	public String getThroughAreaScore() {
		return throughAreaScore;
	}

	public void setThroughAreaScore(String throughAreaScore) {
		this.throughAreaScore = throughAreaScore;
	}

	public String getSubwayLineContactRatio() {
		return subwayLineContactRatio;
	}

	public void setSubwayLineContactRatio(String subwayLineContactRatio) {
		this.subwayLineContactRatio = subwayLineContactRatio;
	}

	public String getThroughSubway() {
		return throughSubway;
	}

	public void setThroughSubway(String throughSubway) {
		this.throughSubway = throughSubway;
	}

	public String getAvgTimeSubway() {
		return avgTimeSubway;
	}

	public void setAvgTimeSubway(String avgTimeSubway) {
		this.avgTimeSubway = avgTimeSubway;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCanReplacedSubway() {
		return canReplacedSubway;
	}

	public void setCanReplacedSubway(String canReplacedSubway) {
		this.canReplacedSubway = canReplacedSubway;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	  
}
