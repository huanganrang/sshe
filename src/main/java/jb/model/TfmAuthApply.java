
/*
 * @author John
 * @date - 2016-08-13
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fm_auth_apply")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmAuthApply implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmAuthApply";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "修改时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_TYPE = "认证类型";
	public static final String ALIAS_USER_NAME = "负责人姓名";
	public static final String ALIAS_PHONE = "手机号";
	public static final String ALIAS_COMPANY_TYPE = "企业经营类型";
	public static final String ALIAS_IMAGES = "图片";
	public static final String ALIAS_USER_CARD = "身份证号";
	public static final String ALIAS_AUTH_REMARK = "认证结果";
	public static final String ALIAS_STATUS = "状态（审核状态）";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATETIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotNull 
	private java.util.Date addtime;
	//@NotNull 
	private java.util.Date updatetime;
	//@NotNull 
	private java.lang.Boolean isdeleted;
	//@Length(max=36)
	private java.lang.String userId;
	//@Length(max=4)
	private java.lang.String type;
	//@Length(max=10)
	private java.lang.String userName;
	//@Length(max=20)
	private java.lang.String phone;
	//@Length(max=4)
	private java.lang.String companyType;
	//@Length(max=500)
	private java.lang.String images;
	//@Length(max=36)
	private java.lang.String userCard;
	//@Length(max=100)
	private java.lang.String authRemark;
	//@Length(max=4)
	private java.lang.String status;
	//columns END


		public TfmAuthApply(){
		}
		public TfmAuthApply(String id) {
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
	

	@Column(name = "addtime", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	

	@Column(name = "updatetime", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Column(name = "isdeleted", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getIsdeleted() {
		return this.isdeleted;
	}
	
	public void setIsdeleted(java.lang.Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	@Column(name = "phone", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	@Column(name = "company_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getCompanyType() {
		return this.companyType;
	}
	
	public void setCompanyType(java.lang.String companyType) {
		this.companyType = companyType;
	}
	
	@Column(name = "images", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getImages() {
		return this.images;
	}
	
	public void setImages(java.lang.String images) {
		this.images = images;
	}
	
	@Column(name = "user_card", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserCard() {
		return this.userCard;
	}
	
	public void setUserCard(java.lang.String userCard) {
		this.userCard = userCard;
	}
	
	@Column(name = "auth_remark", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAuthRemark() {
		return this.authRemark;
	}
	
	public void setAuthRemark(java.lang.String authRemark) {
		this.authRemark = authRemark;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Updatetime",getUpdatetime())
			.append("Isdeleted",getIsdeleted())
			.append("UserId",getUserId())
			.append("Type",getType())
			.append("UserName",getUserName())
			.append("Phone",getPhone())
			.append("CompanyType",getCompanyType())
			.append("Images",getImages())
			.append("UserCard",getUserCard())
			.append("AuthRemark",getAuthRemark())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FmAuthApply == false) return false;
		if(this == obj) return true;
		FmAuthApply other = (FmAuthApply)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

