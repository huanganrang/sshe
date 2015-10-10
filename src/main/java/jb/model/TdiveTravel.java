
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
@Table(name = "dive_travel")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveTravel implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveTravel";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "船宿名称";
	public static final String ALIAS_SUMARY = "简介";
	public static final String ALIAS_PRICE = "价格";
	public static final String ALIAS_ICON = "图片";
	public static final String ALIAS_DESCRIPTION = "文案";
	public static final String ALIAS_FEATURE = "特点";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_ADDTIME = "创建时间";
	public static final String ALIAS_NAME_EN = "英文名";
	public static final String ALIAS_COUNTRY_NAME = "国家";
	public static final String ALIAS_COUNTRY_NAME_EN = "国家英文名";
	public static final String ALIAS_AREA_NAME = "地区";
	public static final String ALIAS_AREA_NAME_EN = "地区英文名";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=128)
	private java.lang.String name;
	//@Length(max=256)
	private java.lang.String sumary;
	//
	private java.lang.Float price;
	//@Length(max=2147483647)
	private java.lang.String icon;
	//@Length(max=2147483647)
	private java.lang.String description;
	//@Length(max=4)
	private java.lang.String feature;
	//@Length(max=4)
	private java.lang.String status;
	//
	private java.util.Date addtime;
	//@Length(max=36)
	private java.lang.String addUserId;
	//@Length(max=128)
	private java.lang.String nameEn;
	//@Length(max=128)
	private java.lang.String countryName;
	//@Length(max=128)
	private java.lang.String countryNameEn;
	//@Length(max=128)
	private java.lang.String areaName;
	//@Length(max=128)
	private java.lang.String areaNameEn;
	//columns END


		public TdiveTravel(){
		}
		public TdiveTravel(String id) {
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
	
	@Column(name = "sumary", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getSumary() {
		return this.sumary;
	}
	
	public void setSumary(java.lang.String sumary) {
		this.sumary = sumary;
	}
	
	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getPrice() {
		return this.price;
	}
	
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	@Column(name = "icon", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	@Column(name = "feature", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getFeature() {
		return this.feature;
	}
	
	public void setFeature(java.lang.String feature) {
		this.feature = feature;
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
	
	@Column(name = "name_en", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getNameEn() {
		return this.nameEn;
	}
	
	public void setNameEn(java.lang.String nameEn) {
		this.nameEn = nameEn;
	}
	
	@Column(name = "country_name", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getCountryName() {
		return this.countryName;
	}
	
	public void setCountryName(java.lang.String countryName) {
		this.countryName = countryName;
	}
	
	@Column(name = "country_name_en", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getCountryNameEn() {
		return this.countryNameEn;
	}
	
	public void setCountryNameEn(java.lang.String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}
	
	@Column(name = "area_name", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getAreaName() {
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}
	
	@Column(name = "area_name_en", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getAreaNameEn() {
		return this.areaNameEn;
	}
	
	public void setAreaNameEn(java.lang.String areaNameEn) {
		this.areaNameEn = areaNameEn;
	}
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Sumary",getSumary())
			.append("Price",getPrice())
			.append("Icon",getIcon())
			.append("Description",getDescription())
			.append("Area",getArea())
			.append("Feature",getFeature())
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
		if(obj instanceof DiveTravel == false) return false;
		if(this == obj) return true;
		DiveTravel other = (DiveTravel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

