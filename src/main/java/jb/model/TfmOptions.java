
/*
 * @author John
 * @date - 2016-08-27
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fm_options")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmOptions implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmOptions";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "修改时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_PROPERTIES_ID = "属性ID";
	public static final String ALIAS_VALUE = "值";
	public static final String ALIAS_SEQ = "排序";
	
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
	private java.lang.String propertiesId;
	//@Length(max=100)
	private java.lang.String value;
	//
	private java.lang.Integer seq;
	//columns END


		public TfmOptions(){
		}
		public TfmOptions(String id) {
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
	
	@Column(name = "properties_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getPropertiesId() {
		return this.propertiesId;
	}
	
	public void setPropertiesId(java.lang.String propertiesId) {
		this.propertiesId = propertiesId;
	}
	
	@Column(name = "value", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getValue() {
		return this.value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	@Column(name = "seq", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSeq() {
		return this.seq;
	}
	
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Updatetime",getUpdatetime())
			.append("Isdeleted",getIsdeleted())
			.append("PropertiesId",getPropertiesId())
			.append("Value",getValue())
			.append("Seq",getSeq())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FmOptions == false) return false;
		if(this == obj) return true;
		FmOptions other = (FmOptions)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

