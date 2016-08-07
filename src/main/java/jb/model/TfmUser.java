
/*
 * @author John
 * @date - 2016-08-07
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fm_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmUser implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmUser";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "修改时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_ACCOUNT = "账号";
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_LOCAL_AREA = "地址";
	public static final String ALIAS_ICON = "头像";
	public static final String ALIAS_PHONE = "手机号";
	public static final String ALIAS_REAL_NAME = "真实姓名";
	public static final String ALIAS_CARD_ID = "身份证号";
	public static final String ALIAS_USER_ROLE = "用户类型（普通用户/采购商、供应商）";
	public static final String ALIAS_HX_PASSWORD = "环信密码";
	public static final String ALIAS_HX_STATUS = "环信注册状态（1：成功，2：失败）";
	
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
	//@Length(max=100)
	private java.lang.String account;
	//@Length(max=36)
	private java.lang.String nickName;
	//@Length(max=36)
	private java.lang.String localArea;
	//@Length(max=100)
	private java.lang.String icon;
	//@Length(max=20)
	private java.lang.String phone;
	//@Length(max=20)
	private java.lang.String realName;
	//@Length(max=20)
	private java.lang.String cardId;
	//@Length(max=4)
	private java.lang.String userRole;
	//@Length(max=36)
	private java.lang.String hxPassword;
	//
	private java.lang.Integer hxStatus;
	//columns END


		public TfmUser(){
		}
		public TfmUser(String id) {
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
	
	@Column(name = "account", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAccount() {
		return this.account;
	}
	
	public void setAccount(java.lang.String account) {
		this.account = account;
	}
	
	@Column(name = "nick_name", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	
	@Column(name = "local_area", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getLocalArea() {
		return this.localArea;
	}
	
	public void setLocalArea(java.lang.String localArea) {
		this.localArea = localArea;
	}
	
	@Column(name = "icon", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	@Column(name = "phone", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	@Column(name = "real_name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	@Column(name = "card_id", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getCardId() {
		return this.cardId;
	}
	
	public void setCardId(java.lang.String cardId) {
		this.cardId = cardId;
	}
	
	@Column(name = "user_role", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getUserRole() {
		return this.userRole;
	}
	
	public void setUserRole(java.lang.String userRole) {
		this.userRole = userRole;
	}
	
	@Column(name = "hx_password", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getHxPassword() {
		return this.hxPassword;
	}
	
	public void setHxPassword(java.lang.String hxPassword) {
		this.hxPassword = hxPassword;
	}
	
	@Column(name = "hx_status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getHxStatus() {
		return this.hxStatus;
	}
	
	public void setHxStatus(java.lang.Integer hxStatus) {
		this.hxStatus = hxStatus;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Updatetime",getUpdatetime())
			.append("Isdeleted",getIsdeleted())
			.append("Account",getAccount())
			.append("NickName",getNickName())
			.append("LocalArea",getLocalArea())
			.append("Icon",getIcon())
			.append("Phone",getPhone())
			.append("RealName",getRealName())
			.append("CardId",getCardId())
			.append("UserRole",getUserRole())
			.append("HxPassword",getHxPassword())
			.append("HxStatus",getHxStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FmUser == false) return false;
		if(this == obj) return true;
		FmUser other = (FmUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

