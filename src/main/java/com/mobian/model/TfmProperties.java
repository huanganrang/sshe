
/*
 * @author John
 * @date - 2016-08-27
 */

package com.mobian.model;

import javax.persistence.*;

import com.mobian.util.Constants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fm_properties")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmProperties implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmProperties";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "修改时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_NAME = "属性名称";
	public static final String ALIAS_GOOD_NAME = "商品名称";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_FIELD_TYPE = "字段类型";
	public static final String ALIAS_REQUIRE = "是否必填";
	public static final String ALIAS_DEFAULT_VALUE = "默认值";
	
	//date formats
	public static final String FORMAT_ADDTIME = Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATETIME = Constants.DATE_FORMAT_FOR_ENTITY;
	

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
	//@Length(max=50)
	private java.lang.String name;
	//@Length(max=8)
	private java.lang.String goodName;
	//@Length(max=500)
	private java.lang.String description;
	//@Length(max=4)
	private java.lang.String fieldType;
	//
	private java.lang.Boolean require;
	//@Length(max=200)
	private java.lang.String defaultValue;
	//columns END

	private java.lang.String groupId;

	private Integer seq;


		public TfmProperties(){
		}
		public TfmProperties(String id) {
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
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "good_name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getGoodName() {
		return this.goodName;
	}
	
	public void setGoodName(java.lang.String goodName) {
		this.goodName = goodName;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	@Column(name = "field_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getFieldType() {
		return this.fieldType;
	}
	
	public void setFieldType(java.lang.String fieldType) {
		this.fieldType = fieldType;
	}
	
	@Column(name = "require", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getRequire() {
		return this.require;
	}
	
	public void setRequire(java.lang.Boolean require) {
		this.require = require;
	}
	
	@Column(name = "default_value", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getDefaultValue() {
		return this.defaultValue;
	}
	
	public void setDefaultValue(java.lang.String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Column(name = "group_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(name = "seq", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}

