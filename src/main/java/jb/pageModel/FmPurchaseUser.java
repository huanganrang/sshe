package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FmPurchaseUser implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String purchaseId;	
	private java.lang.String userId;	

	private FmUser fmUser;
	

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
	public void setPurchaseId(java.lang.String purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public java.lang.String getPurchaseId() {
		return this.purchaseId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}

	public FmUser getFmUser() {
		return fmUser;
	}

	public void setFmUser(FmUser fmUser) {
		this.fmUser = fmUser;
	}

}
