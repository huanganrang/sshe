
/*
 * @author John
 * @date - 2015-07-11
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dive_activity_apply")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveActivityApply implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveActivityApply";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ACTIVITY_ID = "活动ID";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_ADDTIME = "报名时间";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String activityId;
	//@Length(max=512)
	private java.lang.String remark;
	//@Length(max=36)
	private java.lang.String userId;
	//
	private java.util.Date addtime;
	//columns END


		public TdiveActivityApply(){
		}
		public TdiveActivityApply(String id) {
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
	
	@Column(name = "activity_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getActivityId() {
		return this.activityId;
	}
	
	public void setActivityId(java.lang.String activityId) {
		this.activityId = activityId;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
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
			.append("ActivityId",getActivityId())
			.append("Remark",getRemark())
			.append("UserId",getUserId())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveActivityApply == false) return false;
		if(this == obj) return true;
		DiveActivityApply other = (DiveActivityApply)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

