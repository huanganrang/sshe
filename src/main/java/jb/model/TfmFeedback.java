
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
@Table(name = "fm_feedback")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmFeedback implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmFeedback";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "反馈提交时间";
	public static final String ALIAS_UPDATETIME = "处理时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_CONTENT = "用户问题";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_STATUS = "处理状态";
	public static final String ALIAS_REPLY = "回复结果";
	
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
	//@Length(max=1000)
	private java.lang.String content;
	//@Length(max=36)
	private java.lang.String userId;
	//@Length(max=4)
	private java.lang.String status;
	//@Length(max=1000)
	private java.lang.String reply;
	//columns END

	private String userAccount;

	private String mobile;

	private String loginId;

		public TfmFeedback(){
		}
		public TfmFeedback(String id) {
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
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "reply", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getReply() {
		return this.reply;
	}
	
	public void setReply(java.lang.String reply) {
		this.reply = reply;
	}



	@Column(name = "user_account", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(java.lang.String userAccount) {
		this.userAccount = userAccount;
	}


	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "login_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}
}

