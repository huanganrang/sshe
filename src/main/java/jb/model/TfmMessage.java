
/*
 * @author John
 * @date - 2016-08-16
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fm_message")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmMessage implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmMessage";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "修改时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_SUB_TITLE = "副标题";
	public static final String ALIAS_SEND_TYPE = "推送类型";
	public static final String ALIAS_URL = "图片地址";
	public static final String ALIAS_SEND_TIME = "发布时间";
	public static final String ALIAS_LOGIN_ID = "运营人员ID";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATETIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_SEND_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

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

	private java.lang.Boolean issended;
	//@Length(max=65535)
	private java.lang.String content;
	//@Length(max=100)
	private java.lang.String title;
	//@Length(max=100)
	private java.lang.String subTitle;
	//@Length(max=4)
	private java.lang.String sendType;
	//@Length(max=100)
	private java.lang.String url;
	//
	private java.util.Date sendTime;
	//@Length(max=36)
	private java.lang.String loginId;
	//columns END

	private java.lang.String extCfg;
	private String toUser;


		public TfmMessage(){
		}
		public TfmMessage(String id) {
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

	@Column(name = "issended", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public Boolean getIssended() {
		return issended;
	}

	public void setIssended(Boolean issended) {
		this.issended = issended;
	}

	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	@Column(name = "sub_title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getSubTitle() {
		return this.subTitle;
	}
	
	public void setSubTitle(java.lang.String subTitle) {
		this.subTitle = subTitle;
	}
	
	@Column(name = "send_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getSendType() {
		return this.sendType;
	}
	
	public void setSendType(java.lang.String sendType) {
		this.sendType = sendType;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	

	@Column(name = "send_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getSendTime() {
		return this.sendTime;
	}
	
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@Column(name = "login_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getLoginId() {
		return this.loginId;
	}
	
	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}

	@Column(name = "ext_cfg", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getExtCfg() {
		return extCfg;
	}

	public void setExtCfg(String extCfg) {
		this.extCfg = extCfg;
	}
	@Column(name = "to_user", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Updatetime",getUpdatetime())
			.append("Isdeleted",getIsdeleted())
			.append("Content",getContent())
			.append("Title",getTitle())
			.append("SubTitle",getSubTitle())
			.append("SendType",getSendType())
			.append("Url",getUrl())
			.append("SendTime",getSendTime())
			.append("LoginId",getLoginId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FmMessage == false) return false;
		if(this == obj) return true;
		FmMessage other = (FmMessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

