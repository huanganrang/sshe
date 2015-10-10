
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
@Table(name = "dive_store")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveStore implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveStore";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "潜店名称";
	public static final String ALIAS_ICON = "图标";
	public static final String ALIAS_SUMARY = "简介";
	public static final String ALIAS_DESCRIPTION = "介绍";
	public static final String ALIAS_SERVER_SCOPE = "服务范围";
	public static final String ALIAS_AREA = "地区";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_ADDTIME = "addtime";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=128)
	private java.lang.String name;
	//@Length(max=2147483647)
	private java.lang.String icon;
	//@Length(max=2147483647)
	private java.lang.String sumary;
	//@Length(max=2147483647)
	private java.lang.String description;
	//@Length(max=2147483647)
	private java.lang.String serverScope;
	//@Length(max=4)
	private java.lang.String area;
	//@Length(max=4)
	private java.lang.String status;
	//
	private java.util.Date addtime;
	//@Length(max=36)
	private java.lang.String addUserId;
	//columns END


		public TdiveStore(){
		}
		public TdiveStore(String id) {
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
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "icon", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	@Column(name = "sumary", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getSumary() {
		return this.sumary;
	}
	
	public void setSumary(java.lang.String sumary) {
		this.sumary = sumary;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	@Column(name = "serverScope", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getServerScope() {
		return this.serverScope;
	}
	
	public void setServerScope(java.lang.String serverScope) {
		this.serverScope = serverScope;
	}
	
	@Column(name = "area", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getArea() {
		return this.area;
	}
	
	public void setArea(java.lang.String area) {
		this.area = area;
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
			.append("Name",getName())
			.append("Icon",getIcon())
			.append("Sumary",getSumary())
			.append("ServerScope",getServerScope())
			.append("Area",getArea())
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
		if(obj instanceof DiveStore == false) return false;
		if(this == obj) return true;
		DiveStore other = (DiveStore)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

