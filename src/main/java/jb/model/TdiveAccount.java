
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
@Table(name = "dive_account")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveAccount implements java.io.Serializable,IEntity{

	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveAccount";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_NAME = "帐号";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_ICON = "图片地址";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_CITY = "城市";
	public static final String ALIAS_PERSONALITY = "个性签名";
	public static final String ALIAS_EMAIL = "邮箱";
	public static final String ALIAS_ADDTIME = "addtime";
	public static final String ALIAS_RECOMMEND = "推荐人";
	public static final String ALIAS_HX_PASSWORD = "环信登录密码";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=18)
	private java.lang.String userName;
	//@Length(max=128)
	private java.lang.String password;
	//@Length(max=128)
	private java.lang.String icon;
	//@Length(max=128)
	private java.lang.String nickname;
	//@Length(max=4)
	private java.lang.String sex;
	//@Length(max=36)
	private java.lang.String city;
	//@Length(max=256)
	private java.lang.String personality;
	//@Email @Length(max=128)
	private java.lang.String email;
	private java.lang.String recommend;
	private java.lang.String hxPassword;
	private java.lang.String hxStatus;
	private java.util.Date birthday;
	
	//
	private Double longitude;
	//
	private Double latitude;
	//
	private java.util.Date addtime;
	//columns END


		public TdiveAccount(){
		}
		public TdiveAccount(String id) {
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
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	@Column(name = "icon", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	@Column(name = "nickname", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getNickname() {
		return this.nickname;
	}
	
	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}
	
	@Column(name = "sex", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	
	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getCity() {
		return this.city;
	}
	
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	
	@Column(name = "personality", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getPersonality() {
		return this.personality;
	}
	
	public void setPersonality(java.lang.String personality) {
		this.personality = personality;
	}
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	@Column(name = "recommend", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRecommend() {
		return recommend;
	}
	
	public void setRecommend(java.lang.String recommend) {
		this.recommend = recommend;
	}
	
	@Column(name = "hx_password", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getHxPassword() {
		return hxPassword;
	}
	
	public void setHxPassword(java.lang.String hxPassword) {
		this.hxPassword = hxPassword;
	}
	
	@Column(name = "hx_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getHxStatus() {
		return hxStatus;
	}
	
	public void setHxStatus(java.lang.String hxStatus) {
		this.hxStatus = hxStatus;
	}
	
	@Column(name = "birthday", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "longitude", unique = false, nullable = true, insertable = true, updatable = true, length = 14)
	public Double getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Column(name = "latitude", unique = false, nullable = true, insertable = true, updatable = true, length = 14)
	public Double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
			.append("UserName",getUserName())
			.append("Password",getPassword())
			.append("Icon",getIcon())
			.append("Nickname",getNickname())
			.append("Sex",getSex())
			.append("City",getCity())
			.append("Personality",getPersonality())
			.append("Email",getEmail())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveAccount == false) return false;
		if(this == obj) return true;
		DiveAccount other = (DiveAccount)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

