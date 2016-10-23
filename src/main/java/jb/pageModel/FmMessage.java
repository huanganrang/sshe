package jb.pageModel;

import jb.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FmMessage implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;
	private java.lang.Boolean issended;
	private java.lang.String content;	
	private java.lang.String title;	
	private java.lang.String subTitle;	
	private java.lang.String sendType;	
	private java.lang.String url;	
	private Date sendTime;			
	private java.lang.String loginId;
	private java.lang.String extCfg;
	private String toUser;

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public Date getUpdatetime() {
		return this.updatetime;
	}
	public void setIsdeleted(java.lang.Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public java.lang.Boolean getIsdeleted() {
		return this.isdeleted;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setSubTitle(java.lang.String subTitle) {
		this.subTitle = subTitle;
	}
	
	public java.lang.String getSubTitle() {
		return this.subTitle;
	}
	public void setSendType(java.lang.String sendType) {
		this.sendType = sendType;
	}
	
	public java.lang.String getSendType() {
		return this.sendType;
	}
	public java.lang.String getSendTypeName() {
		return Application.getString(this.sendType);
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	public Date getSendTime() {
		return this.sendTime;
	}
	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}
	
	public java.lang.String getLoginId() {
		return this.loginId;
	}

	public Boolean getIssended() {
		return issended;
	}

	public void setIssended(Boolean issended) {
		this.issended = issended;
	}

	public String getExtCfg() {
		return extCfg;
	}

	public void setExtCfg(String extCfg) {
		this.extCfg = extCfg;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
}
