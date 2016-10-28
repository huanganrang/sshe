
/*
 * @author John
 * @date - 2016-08-07
 */

package jb.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "fm_goods")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmGoods implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmGoods";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "发布时间";
	public static final String ALIAS_UPDATETIME = "修改时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_PRICE = "单价";
	public static final String ALIAS_UNIT = "单位";
	public static final String ALIAS_MIN_BATCH = "起批量";
	public static final String ALIAS_STATUS = "状态（预售，现货，在途）";
	public static final String ALIAS_BORN_AREA = "商品产地";
	public static final String ALIAS_STORAGE = "存放地点";
	public static final String ALIAS_OFFLINE_TIME = "下架时间";
	public static final String ALIAS_CONTACTOR = "联系人姓名";
	public static final String ALIAS_CONTACTOR_MOBILE = "联系人手机号";
	public static final String ALIAS_GOAL_AREA = "发往地区";
	public static final String ALIAS_PRE_ONLINE_TIME = "预计上市时间";
	public static final String ALIAS_TRANSACTION_AREA = "交易地点";
	public static final String ALIAS_SEND_TIME = "发货时间";
	public static final String ALIAS_TRANSACTION_TIME = "交货时间";
	public static final String ALIAS_CAR_NO = "车牌号";
	public static final String ALIAS_IMAGES = "商品图片地址（4张）";
	public static final String ALIAS_DESCRIPTION = "商品描述";
	public static final String ALIAS_DIAMETER = "直径";
	public static final String ALIAS_DIAMETER_UNIT = "直径单位";
	public static final String ALIAS_COLOR = "颜色";
	public static final String ALIAS_IS_PACK = "是否套袋(1/0)";
	public static final String ALIAS_PACK = "包装";
	public static final String ALIAS_FORMAT_DESC = "规格描述";
	public static final String ALIAS_ONLINE_STATUS = "上架状态（上架、下架）";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_PARENT_ID = "parentId";
	public static final String ALIAS_ACCESS_NUM = "访问数";
	public static final String ALIAS_SOURCE = "来源";
	public static final String ALIAS_OUTER_ID = "外部ID";
	public static final String ALIAS_OUTER_NUMBER = "外部号（批次号）";
	public static final String ALIAS_GRADE = "商品级别（有机、无机）";
	public static final String ALIAS_EXTFIELDS = "规格的json格式";

	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATETIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_OFFLINE_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_PRE_ONLINE_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_SEND_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_TRANSACTION_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

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
	//@Length(max=8)
	private java.lang.String name;
	//
	private java.lang.Float price;
	//@Length(max=4)
	private java.lang.String unit;
	//
	private java.lang.Float minBatch;
	//@Length(max=4)
	private java.lang.String status;
	//@Length(max=36)
	private java.lang.String bornArea;
	//@Length(max=100)
	private java.lang.String storage;
	//
	private java.util.Date offlineTime;
	//@Length(max=20)
	private java.lang.String contactor;
	//@Length(max=20)
	private java.lang.String contactorMobile;
	//@Length(max=36)
	private java.lang.String goalArea;
	//
	private java.util.Date preOnlineTime;
	//@Length(max=100)
	private java.lang.String transactionArea;
	//
	private java.util.Date sendTime;
	//
	private java.util.Date transactionTime;
	//@Length(max=20)
	private java.lang.String carNo;
	//@Length(max=500)
	private java.lang.String images;
	//@Length(max=500)
	private java.lang.String description;
	//@Length(max=10)
	private java.lang.String diameter;
	//@Length(max=4)
	private java.lang.String diameterUnit;
	//@Length(max=4)
	private java.lang.String color;
	//@Length(max=1)
	private java.lang.String isPack;
	//@Length(max=4)
	private java.lang.String pack;
	//@Length(max=500)
	private java.lang.String formatDesc;
	//@Length(max=4)
	private java.lang.String onlineStatus;
	//@Length(max=36)
	private java.lang.String userId;
	//@Length(max=36)
	private java.lang.String parentId;
	//
	private java.lang.Integer accessNum;
	//@Length(max=4)
	private java.lang.String source;
	//@Length(max=36)
	private java.lang.String outerId;
	//@Length(max=36)
	private java.lang.String outerNumber;
	//@Length(max=4)
	private java.lang.String grade;
	//@Length(max=500)
	private java.lang.String extFields;
	//columns END


		public TfmGoods(){
		}
		public TfmGoods(String id) {
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
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getPrice() {
		return this.price;
	}
	
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	@Column(name = "unit", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getUnit() {
		return this.unit;
	}
	
	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}
	
	@Column(name = "min_batch", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getMinBatch() {
		return this.minBatch;
	}
	
	public void setMinBatch(java.lang.Float minBatch) {
		this.minBatch = minBatch;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "born_area", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getBornArea() {
		return this.bornArea;
	}
	
	public void setBornArea(java.lang.String bornArea) {
		this.bornArea = bornArea;
	}
	
	@Column(name = "storage", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getStorage() {
		return this.storage;
	}
	
	public void setStorage(java.lang.String storage) {
		this.storage = storage;
	}
	

	@Column(name = "offline_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getOfflineTime() {
		return this.offlineTime;
	}
	
	public void setOfflineTime(java.util.Date offlineTime) {
		this.offlineTime = offlineTime;
	}
	
	@Column(name = "contactor", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getContactor() {
		return this.contactor;
	}
	
	public void setContactor(java.lang.String contactor) {
		this.contactor = contactor;
	}
	
	@Column(name = "contactor_mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getContactorMobile() {
		return this.contactorMobile;
	}
	
	public void setContactorMobile(java.lang.String contactorMobile) {
		this.contactorMobile = contactorMobile;
	}
	
	@Column(name = "goal_area", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGoalArea() {
		return this.goalArea;
	}
	
	public void setGoalArea(java.lang.String goalArea) {
		this.goalArea = goalArea;
	}
	

	@Column(name = "pre_online_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getPreOnlineTime() {
		return this.preOnlineTime;
	}
	
	public void setPreOnlineTime(java.util.Date preOnlineTime) {
		this.preOnlineTime = preOnlineTime;
	}
	
	@Column(name = "transaction_area", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTransactionArea() {
		return this.transactionArea;
	}
	
	public void setTransactionArea(java.lang.String transactionArea) {
		this.transactionArea = transactionArea;
	}
	

	@Column(name = "send_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getSendTime() {
		return this.sendTime;
	}
	
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}
	

	@Column(name = "transaction_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getTransactionTime() {
		return this.transactionTime;
	}
	
	public void setTransactionTime(java.util.Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	@Column(name = "car_no", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getCarNo() {
		return this.carNo;
	}
	
	public void setCarNo(java.lang.String carNo) {
		this.carNo = carNo;
	}
	
	@Column(name = "images", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getImages() {
		return this.images;
	}
	
	public void setImages(java.lang.String images) {
		this.images = images;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	@Column(name = "diameter", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getDiameter() {
		return this.diameter;
	}
	
	public void setDiameter(java.lang.String diameter) {
		this.diameter = diameter;
	}
	
	@Column(name = "diameter_unit", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getDiameterUnit() {
		return this.diameterUnit;
	}
	
	public void setDiameterUnit(java.lang.String diameterUnit) {
		this.diameterUnit = diameterUnit;
	}
	
	@Column(name = "color", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getColor() {
		return this.color;
	}
	
	public void setColor(java.lang.String color) {
		this.color = color;
	}
	
	@Column(name = "is_pack", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.String getIsPack() {
		return this.isPack;
	}
	
	public void setIsPack(java.lang.String isPack) {
		this.isPack = isPack;
	}
	
	@Column(name = "pack", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getPack() {
		return this.pack;
	}
	
	public void setPack(java.lang.String pack) {
		this.pack = pack;
	}
	
	@Column(name = "format_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getFormatDesc() {
		return this.formatDesc;
	}
	
	public void setFormatDesc(java.lang.String formatDesc) {
		this.formatDesc = formatDesc;
	}
	
	@Column(name = "online_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getOnlineStatus() {
		return this.onlineStatus;
	}
	
	public void setOnlineStatus(java.lang.String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "access_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAccessNum() {
		return this.accessNum;
	}
	
	public void setAccessNum(java.lang.Integer accessNum) {
		this.accessNum = accessNum;
	}
	
	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getSource() {
		return this.source;
	}
	
	public void setSource(java.lang.String source) {
		this.source = source;
	}
	
	@Column(name = "outer_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getOuterId() {
		return this.outerId;
	}
	
	public void setOuterId(java.lang.String outerId) {
		this.outerId = outerId;
	}
	
	@Column(name = "outer_number", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getOuterNumber() {
		return this.outerNumber;
	}
	
	public void setOuterNumber(java.lang.String outerNumber) {
		this.outerNumber = outerNumber;
	}
	
	@Column(name = "grade", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getGrade() {
		return this.grade;
	}
	
	public void setGrade(java.lang.String grade) {
		this.grade = grade;
	}

	@Column(name = "ext_fields", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getExtFields() {
		return extFields;
	}

	public void setExtFields(String extFields) {
		this.extFields = extFields;
	}

	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Updatetime",getUpdatetime())
			.append("Isdeleted",getIsdeleted())
			.append("Name",getName())
			.append("Price",getPrice())
			.append("Unit",getUnit())
			.append("MinBatch",getMinBatch())
			.append("Status",getStatus())
			.append("BornArea",getBornArea())
			.append("Storage",getStorage())
			.append("OfflineTime",getOfflineTime())
			.append("Contactor",getContactor())
			.append("ContactorMobile",getContactorMobile())
			.append("GoalArea",getGoalArea())
			.append("PreOnlineTime",getPreOnlineTime())
			.append("TransactionArea",getTransactionArea())
			.append("SendTime",getSendTime())
			.append("TransactionTime",getTransactionTime())
			.append("CarNo",getCarNo())
			.append("Images",getImages())
			.append("Description",getDescription())
			.append("Diameter",getDiameter())
			.append("DiameterUnit",getDiameterUnit())
			.append("Color",getColor())
			.append("IsPack",getIsPack())
			.append("Pack",getPack())
			.append("FormatDesc",getFormatDesc())
			.append("OnlineStatus",getOnlineStatus())
			.append("UserId",getUserId())
			.append("ParentId",getParentId())
			.append("AccessNum",getAccessNum())
			.append("Source",getSource())
			.append("OuterId",getOuterId())
			.append("OuterNumber",getOuterNumber())
			.append("Grade",getGrade())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FmGoods == false) return false;
		if(this == obj) return true;
		FmGoods other = (FmGoods)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

