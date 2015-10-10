
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
@Table(name = "dive_course")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveCourse implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveCourse";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_COURSE_TYPE = "分类";
	public static final String ALIAS_ICON = "图标";
	public static final String ALIAS_PRICE = "价格";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_INTRODUCE = "介绍";
	public static final String ALIAS_FILE_PATH = "视频地址";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_ADDTIME = "addtime";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=128)
	private java.lang.String title;
	//@Length(max=4)
	private java.lang.String courseType;
	//@Length(max=2147483647)
	private java.lang.String icon;
	//
	private java.lang.Float price;
	//@Length(max=2147483647)
	private java.lang.String content;
	//@Length(max=2147483647)
	private java.lang.String introduce;
	//@Length(max=128)
	private java.lang.String filePath;
	//@Length(max=4)
	private java.lang.String status;
	//
	private java.util.Date addtime;
	//@Length(max=36)
	private java.lang.String addUserId;
	//columns END


		public TdiveCourse(){
		}
		public TdiveCourse(String id) {
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
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	@Column(name = "course_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getCourseType() {
		return this.courseType;
	}
	
	public void setCourseType(java.lang.String courseType) {
		this.courseType = courseType;
	}
	
	@Column(name = "icon", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getPrice() {
		return this.price;
	}
	
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "introduce", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getIntroduce() {
		return this.introduce;
	}
	
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	@Column(name = "file_path", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getFilePath() {
		return this.filePath;
	}
	
	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	@Column(name = "add_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(java.lang.String addUserId) {
		this.addUserId = addUserId;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("CourseType",getCourseType())
			.append("Icon",getIcon())
			.append("Price",getPrice())
			.append("Content",getContent())
			.append("Introduce",getIntroduce())
			.append("FilePath",getFilePath())
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
		if(obj instanceof DiveCourse == false) return false;
		if(this == obj) return true;
		DiveCourse other = (DiveCourse)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

