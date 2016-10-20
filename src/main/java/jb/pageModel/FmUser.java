package jb.pageModel;

import jb.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FmUser implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String account;	
	private java.lang.String nickName;	
	private java.lang.String localArea;	
	private java.lang.String icon;	
	private java.lang.String phone;	
	private java.lang.String realName;	
	private java.lang.String cardId;	
	private java.lang.String userRole;	
	private java.lang.String hxPassword;	
	private java.lang.Integer hxStatus;	
	private java.lang.String authStatus;	
	private java.lang.String status;
	private Boolean collected;

	

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
	public void setAccount(java.lang.String account) {
		this.account = account;
	}
	
	public java.lang.String getAccount() {
		return this.account;
	}
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	public void setLocalArea(java.lang.String localArea) {
		this.localArea = localArea;
	}
	
	public java.lang.String getLocalArea() {
		return this.localArea;
	}
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	public void setCardId(java.lang.String cardId) {
		this.cardId = cardId;
	}
	
	public java.lang.String getCardId() {
		return this.cardId;
	}
	public void setUserRole(java.lang.String userRole) {
		this.userRole = userRole;
	}
	
	public java.lang.String getUserRole() {
		return this.userRole;
	}

	public java.lang.String getUserRoleName() {
		return Application.getString(this.userRole);
	}

	public void setHxPassword(java.lang.String hxPassword) {
		this.hxPassword = hxPassword;
	}
	
	public java.lang.String getHxPassword() {
		return this.hxPassword;
	}
	public void setHxStatus(java.lang.Integer hxStatus) {
		this.hxStatus = hxStatus;
	}
	
	public java.lang.Integer getHxStatus() {
		return this.hxStatus;
	}
	public void setAuthStatus(java.lang.String authStatus) {
		this.authStatus = authStatus;
	}
	
	public java.lang.String getAuthStatus() {
		return this.authStatus;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}

	public Boolean getCollected() {
		return collected;
	}

	public void setCollected(Boolean collected) {
		this.collected = collected;
	}
}
