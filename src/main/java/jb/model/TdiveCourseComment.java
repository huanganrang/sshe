
/*
 * @author John
 * @date - 2015-10-14
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dive_course_comment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveCourseComment implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveCourseComment";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_COURSE_ID = "视频ID";
	public static final String ALIAS_COMMENT = "评论";
	public static final String ALIAS_PID = "评论上级";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_ADDTIME = "评论时间";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String courseId;
	//@Length(max=65535)
	private java.lang.String comment;
	//@Length(max=36)
	private java.lang.String pid;
	//@Length(max=36)
	private java.lang.String userId;
	//
	private java.util.Date addtime;
	//columns END


		public TdiveCourseComment(){
		}
		public TdiveCourseComment(String id) {
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
	
	@Column(name = "course_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCourseId() {
		return this.courseId;
	}
	
	public void setCourseId(java.lang.String courseId) {
		this.courseId = courseId;
	}
	
	@Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getComment() {
		return this.comment;
	}
	
	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}
	
	@Column(name = "pid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getPid() {
		return this.pid;
	}
	
	public void setPid(java.lang.String pid) {
		this.pid = pid;
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
			.append("CourseId",getCourseId())
			.append("Comment",getComment())
			.append("Pid",getPid())
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
		if(obj instanceof DiveCourseComment == false) return false;
		if(this == obj) return true;
		DiveCourseComment other = (DiveCourseComment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

