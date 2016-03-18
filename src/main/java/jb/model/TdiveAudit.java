
/*
 * @author John
 * @date - 2015-11-01
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dive_audit")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveAudit implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveAudit";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_BUSINESS_ID = "业务ID";
	public static final String ALIAS_BUSINESS_TYPE = "业务类型";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_AUDIT_STATUS = "审核状态";
	public static final String ALIAS_AUDIT_TIME = "审核时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_ADDTIME = "创建时间";
	
	//date formats
	public static final String FORMAT_AUDIT_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String businessId;
	//@Length(max=4)
	private java.lang.String businessType;
	//@Length(max=36)
	private java.lang.String userId;
	//@Length(max=4)
	private java.lang.String auditStatus;
	//
	private java.util.Date audittime;
	//@Length(max=256)
	private java.lang.String remark;
	//
	private java.util.Date addtime;
	//columns END


		public TdiveAudit(){
		}
		public TdiveAudit(String id) {
			this.id = id;
		}
	

	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "business_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
	
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}
	
	@Column(name = "business_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getBusinessType() {
		return this.businessType;
	}
	
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	@Column(name = "audit_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getAuditStatus() {
		return this.auditStatus;
	}
	
	public void setAuditStatus(java.lang.String auditStatus) {
		this.auditStatus = auditStatus;
	}
	

	@Column(name = "audittime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getAudittime() {
		return this.audittime;
	}
	
	public void setAudittime(java.util.Date audittime) {
		this.audittime = audittime;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BusinessId",getBusinessId())
			.append("BusinessType",getBusinessType())
			.append("UserId",getUserId())
			.append("AuditStatus",getAuditStatus())
			.append("AuditTime",getAuditTime())
			.append("Remark",getRemark())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveAudit == false) return false;
		if(this == obj) return true;
		DiveAudit other = (DiveAudit)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

