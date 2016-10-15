
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
@Table(name = "fm_purchase")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfmPurchase implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FmPurchase";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "修改时间";
	public static final String ALIAS_ISDELETED = "是否删除,1删除，0未删除";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_START_PRICE = "期望最低价";
	public static final String ALIAS_END_PRICE = "期望最高价";
	public static final String ALIAS_UNIT = "单位";
	public static final String ALIAS_MIN_NUM = "最少数量";
	public static final String ALIAS_MAX_NUM = "最高数量";
	public static final String ALIAS_STATUS = "状态（预售，现货，在途）";
	public static final String ALIAS_BORN_AREA = "商品产地";
	public static final String ALIAS_TRANSACTION_AREA = "交易地点";
	public static final String ALIAS_START_TIME = "收货开始时间";
	public static final String ALIAS_END_TIME = "收货结束时间";
	public static final String ALIAS_IMAGES = "商品图片地址（5张）";
	public static final String ALIAS_REQUIRE = "要求";
	public static final String ALIAS_DIAMETER = "直径";
	public static final String ALIAS_DIAMETER_UNIT = "直径单位";
	public static final String ALIAS_COLOR = "颜色";
	public static final String ALIAS_IS_PACK = "是否套袋(1/0)";
	public static final String ALIAS_PACK = "包装";
	public static final String ALIAS_FORMAT_DESC = "规格描述";
	public static final String ALIAS_VOICE_URL = "语音地址";
	public static final String ALIAS_VOICE_DURATION = "语音时长";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_ONLINE_STATUS = "上下线状态";
	public static final String ALIAS_EXTFIELDS = "规格的json格式";
	public static final String ALIAS_TOP = "置顶";

	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATETIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_START_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_END_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

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
	private java.lang.Float startPrice;
	//
	private java.lang.Float endPrice;
	//@Length(max=4)
	private java.lang.String unit;
	//
	private java.lang.Float minNum;
	//
	private java.lang.Float maxNum;
	//@Length(max=4)
	private java.lang.String status;
	//@Length(max=36)
	private java.lang.String bornArea;
	//@Length(max=100)
	private java.lang.String transactionArea;
	//
	private java.util.Date startTime;
	//
	private java.util.Date endTime;
	//@Length(max=500)
	private java.lang.String images;
	//@Length(max=500)
	private java.lang.String require;
	//@Length(max=100)
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
	//@Length(max=100)
	private java.lang.String voiceUrl;
	//
	private java.lang.Integer voiceDuration;
	//@Length(max=36)
	private java.lang.String userId;
	//@Length(max=4)
	private java.lang.String onlineStatus;
	//@Length(max=500)
	private java.lang.String extFields;
	//columns END
	private Boolean topIndex;

		public TfmPurchase(){
		}
		public TfmPurchase(String id) {
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
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "start_price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getStartPrice() {
		return this.startPrice;
	}
	
	public void setStartPrice(java.lang.Float startPrice) {
		this.startPrice = startPrice;
	}
	
	@Column(name = "end_price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getEndPrice() {
		return this.endPrice;
	}
	
	public void setEndPrice(java.lang.Float endPrice) {
		this.endPrice = endPrice;
	}
	
	@Column(name = "unit", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getUnit() {
		return this.unit;
	}
	
	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}
	
	@Column(name = "min_num", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getMinNum() {
		return this.minNum;
	}
	
	public void setMinNum(java.lang.Float minNum) {
		this.minNum = minNum;
	}
	
	@Column(name = "max_num", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getMaxNum() {
		return this.maxNum;
	}
	
	public void setMaxNum(java.lang.Float maxNum) {
		this.maxNum = maxNum;
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
	
	@Column(name = "transaction_area", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTransactionArea() {
		return this.transactionArea;
	}
	
	public void setTransactionArea(java.lang.String transactionArea) {
		this.transactionArea = transactionArea;
	}
	

	@Column(name = "start_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	

	@Column(name = "end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "images", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getImages() {
		return this.images;
	}
	
	public void setImages(java.lang.String images) {
		this.images = images;
	}
	
	@Column(name = "require", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getRequire() {
		return this.require;
	}
	
	public void setRequire(java.lang.String require) {
		this.require = require;
	}
	
	@Column(name = "diameter", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
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
	
	@Column(name = "voice_url", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getVoiceUrl() {
		return this.voiceUrl;
	}
	
	public void setVoiceUrl(java.lang.String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	
	@Column(name = "voice_duration", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getVoiceDuration() {
		return this.voiceDuration;
	}
	
	public void setVoiceDuration(java.lang.Integer voiceDuration) {
		this.voiceDuration = voiceDuration;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	@Column(name = "online_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getOnlineStatus() {
		return this.onlineStatus;
	}
	
	public void setOnlineStatus(java.lang.String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	@Column(name = "ext_fields", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getExtFields() {
		return extFields;
	}

	public void setExtFields(String extFields) {
		this.extFields = extFields;
	}

	@Column(name = "top_index", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public Boolean getTopIndex() {
		return topIndex;
	}

	public void setTopIndex(Boolean topIndex) {
		this.topIndex = topIndex;
	}
/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Updatetime",getUpdatetime())
			.append("Isdeleted",getIsdeleted())
			.append("Name",getName())
			.append("StartPrice",getStartPrice())
			.append("EndPrice",getEndPrice())
			.append("Unit",getUnit())
			.append("MinNum",getMinNum())
			.append("MaxNum",getMaxNum())
			.append("Status",getStatus())
			.append("BornArea",getBornArea())
			.append("TransactionArea",getTransactionArea())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("Images",getImages())
			.append("Require",getRequire())
			.append("Diameter",getDiameter())
			.append("DiameterUnit",getDiameterUnit())
			.append("Color",getColor())
			.append("IsPack",getIsPack())
			.append("Pack",getPack())
			.append("FormatDesc",getFormatDesc())
			.append("VoiceUrl",getVoiceUrl())
			.append("VoiceDuration",getVoiceDuration())
			.append("UserId",getUserId())
			.append("OnlineStatus",getOnlineStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FmPurchase == false) return false;
		if(this == obj) return true;
		FmPurchase other = (FmPurchase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

