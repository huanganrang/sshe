package jb.pageModel;

import java.util.Date;

import jb.listener.Application;

public class DiveCourse implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String title;	
	private java.lang.String courseType;	
	private java.lang.String icon;	
	private java.lang.Float price;	
	private java.lang.String content;	
	private java.lang.String introduce;	
	private java.lang.String filePath;	
	private java.lang.String status;	
	private Date addtime;			
	private java.lang.String addUserId;

	private boolean isPay;
	
	public String getCourseTypeZh() {
		return Application.getString(this.courseType);
	}
	
	public String getStatusZh() {
		return Application.getString(this.status);
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setCourseType(java.lang.String courseType) {
		this.courseType = courseType;
	}
	
	public java.lang.String getCourseType() {
		return this.courseType;
	}
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	public java.lang.Float getPrice() {
		return this.price;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	public java.lang.String getIntroduce() {
		return this.introduce;
	}
	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}
	
	public java.lang.String getFilePath() {
		return this.filePath;
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
	public java.lang.String getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(java.lang.String addUserId) {
		this.addUserId = addUserId;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	
	
	public boolean isPay() {
		return isPay;
	}

	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}
}
