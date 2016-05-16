package jb.pageModel;

import java.util.Date;

import jb.listener.Application;

public class DiveAccount implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String userName;	
	private java.lang.String password;	
	private java.lang.String icon;	
	private java.lang.String nickname;	
	private java.lang.String sex;	
	private java.lang.String city;	
	private java.lang.String personality;	
	private java.lang.String email;	
	private java.lang.String channel;	
	private java.lang.String recommend;
	private java.lang.String hxPassword;
	private java.lang.String hxStatus;
	private java.util.Date birthday;
	private Double longitude;
	private Double latitude;
	private Date addtime;	
	
	private java.lang.String realname;
	private java.lang.String mobile;
	private java.lang.String address;

	private int logNum; // 潜水日志数量
	private String qrCodePath; // 二维码地址
	private String certificate; // 潜水等级信息
	
	private String searchValue;
	private String searchType;
	
	public String getSexZh() {
		return Application.getString(this.sex);
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}
	
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	
	public java.lang.String getCity() {
		return this.city;
	}
	public void setPersonality(java.lang.String personality) {
		this.personality = personality;
	}
	
	public java.lang.String getPersonality() {
		return this.personality;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public java.lang.String getChannel() {
		return channel;
	}

	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	
	public int getLogNum() {
		return logNum;
	}

	public void setLogNum(int logNum) {
		this.logNum = logNum;
	}
	
	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}
	
	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	public java.lang.String getRecommend() {
		return recommend;
	}

	public void setRecommend(java.lang.String recommend) {
		this.recommend = recommend;
	}

	public java.lang.String getHxPassword() {
		return hxPassword;
	}

	public void setHxPassword(java.lang.String hxPassword) {
		this.hxPassword = hxPassword;
	}
	public java.lang.String getHxStatus() {
		return hxStatus;
	}

	public void setHxStatus(java.lang.String hxStatus) {
		this.hxStatus = hxStatus;
	}
	
	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	public java.lang.String getRealname() {
		return realname;
	}

	public void setRealname(java.lang.String realname) {
		this.realname = realname;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}
