package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FmOptions implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String propertiesId;	
	private java.lang.String value;	
	private java.lang.Integer seq;	

	

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
	public void setPropertiesId(java.lang.String propertiesId) {
		this.propertiesId = propertiesId;
	}
	
	public java.lang.String getPropertiesId() {
		return this.propertiesId;
	}
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}
	
	public java.lang.Integer getSeq() {
		return this.seq;
	}

}
