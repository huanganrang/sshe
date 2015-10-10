package jb.pageModel;

import java.util.Date;

import jb.listener.Application;

public class DiveTravel implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String name;	
	private java.lang.String sumary;	
	private java.lang.Float price;	
	private java.lang.String icon;	
	private java.lang.String description;	
	private java.lang.String feature;	
	private java.lang.String status;	
	private Date addtime;	
	private java.lang.String addUserId;
	private java.lang.String nameEn;	
	private java.lang.String countryName;	
	private java.lang.String countryNameEn;	
	private java.lang.String areaName;	
	private java.lang.String areaNameEn;

	private boolean isCollect;
	private int collectNum;
	private String searchValue;
	
	public String getStatusZh() {
		return Application.getString(this.status);
	}
	public String getFeatureZh() {
		return Application.getString(this.feature);
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setSumary(java.lang.String sumary) {
		this.sumary = sumary;
	}
	
	public java.lang.String getSumary() {
		return this.sumary;
	}
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	public java.lang.Float getPrice() {
		return this.price;
	}
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setFeature(java.lang.String feature) {
		this.feature = feature;
	}
	
	public java.lang.String getFeature() {
		return this.feature;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	public java.lang.String getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(java.lang.String addUserId) {
		this.addUserId = addUserId;
	}
	public void setNameEn(java.lang.String nameEn) {
		this.nameEn = nameEn;
	}
	
	public java.lang.String getNameEn() {
		return this.nameEn;
	}
	public void setCountryName(java.lang.String countryName) {
		this.countryName = countryName;
	}
	
	public java.lang.String getCountryName() {
		return this.countryName;
	}
	public void setCountryNameEn(java.lang.String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}
	
	public java.lang.String getCountryNameEn() {
		return this.countryNameEn;
	}
	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}
	
	public java.lang.String getAreaName() {
		return this.areaName;
	}
	public void setAreaNameEn(java.lang.String areaNameEn) {
		this.areaNameEn = areaNameEn;
	}
	
	public java.lang.String getAreaNameEn() {
		return this.areaNameEn;
	}
	public boolean isCollect() {
		return isCollect;
	}

	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
