
/*
 * @author John
 * @date - 2015-07-06
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dive_certificate_authority")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveCertificateAuthority implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveCertificateAuthority";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ACCOUNT_ID = "用户id";
	public static final String ALIAS_ORG_CODE = "机构代码";
	public static final String ALIAS_LEVEL_CODE = "等级";
	public static final String ALIAS_AUTH_DATE = "发证日期";
	public static final String ALIAS_REVERSE_SIDE = "潜水证反面图片";
	public static final String ALIAS_RIGHT_SIDE = "潜水证正面图片";
	public static final String ALIAS_AUDIT_DATE = "审核日期";
	public static final String ALIAS_STATUS = "审核状态";
	public static final String ALIAS_ADDTIME = "申请时间";
	
	//date formats
	public static final String FORMAT_AUTH_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_AUDIT_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String accountId;
	//@Length(max=4)
	private java.lang.String orgCode;
	//@Length(max=4)
	private java.lang.String levelCode;
	//
	private java.util.Date authDate;
	//@Length(max=128)
	private java.lang.String reverseSide;
	//@Length(max=128)
	private java.lang.String rightSide;
	//
	private java.util.Date auditDate;
	//@Length(max=4)
	private java.lang.String status;
	//
	private java.util.Date addtime;
	//columns END


		public TdiveCertificateAuthority(){
		}
		public TdiveCertificateAuthority(String id) {
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
	
	@Column(name = "account_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getAccountId() {
		return this.accountId;
	}
	
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "org_code", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getOrgCode() {
		return this.orgCode;
	}
	
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Column(name = "level_code", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getLevelCode() {
		return this.levelCode;
	}
	
	public void setLevelCode(java.lang.String levelCode) {
		this.levelCode = levelCode;
	}
	

	@Column(name = "auth_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getAuthDate() {
		return this.authDate;
	}
	
	public void setAuthDate(java.util.Date authDate) {
		this.authDate = authDate;
	}
	
	@Column(name = "reverse_side", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getReverseSide() {
		return this.reverseSide;
	}
	
	public void setReverseSide(java.lang.String reverseSide) {
		this.reverseSide = reverseSide;
	}
	
	@Column(name = "right_side", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getRightSide() {
		return this.rightSide;
	}
	
	public void setRightSide(java.lang.String rightSide) {
		this.rightSide = rightSide;
	}
	

	@Column(name = "audit_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getAuditDate() {
		return this.auditDate;
	}
	
	public void setAuditDate(java.util.Date auditDate) {
		this.auditDate = auditDate;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
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
			.append("OrgCode",getOrgCode())
			.append("LevelCode",getLevelCode())
			.append("AuthDate",getAuthDate())
			.append("ReverseSide",getReverseSide())
			.append("RightSide",getRightSide())
			.append("AuditDate",getAuditDate())
			.append("Status",getStatus())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveCertificateAuthority == false) return false;
		if(this == obj) return true;
		DiveCertificateAuthority other = (DiveCertificateAuthority)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

