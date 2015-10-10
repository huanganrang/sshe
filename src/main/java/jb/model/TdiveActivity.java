
/*
 * @author John
 * @date - 2015-07-16
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dive_activity")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveActivity implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveActivity";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_START_DATE = "开始时间";
	public static final String ALIAS_END_DATE = "结束时间";
	public static final String ALIAS_START_ADDR = "始发地";
	public static final String ALIAS_END_ADDR = "目的地";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_STATUS = "活动状态";
	public static final String ALIAS_STAMP = "印章";
	public static final String ALIAS_ADDTIME = "addtime";
	public static final String ALIAS_BUSINESS_TYPE = "业务类型";
	public static final String ALIAS_BUSINESS_ID = "业务ID";
	public static final String ALIAS_ROOM_TYPE = "房型";
	public static final String ALIAS_DIVER_PRICE = "潜水员/位";
	public static final String ALIAS_NON_DRIVE_PRICE = "非潜水员";
	public static final String ALIAS_SINGLE_ROOM_PRICE = "单人房差/位";
	public static final String ALIAS_PEER_NAME = "同行";
	
	//date formats
	public static final String FORMAT_START_DATE = jb.util.Constants.DATE_FORMAT_YMD;
	public static final String FORMAT_END_DATE = jb.util.Constants.DATE_FORMAT_YMD;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=128)
	private java.lang.String name;
	//
	private java.util.Date startDate;
	//
	private java.util.Date endDate;
	//@Length(max=128)
	private java.lang.String startAddr;
	//@Length(max=128)
	private java.lang.String endAddr;
	//@Length(max=2147483647)
	private java.lang.String description;
	//@Length(max=4)
	private java.lang.String status;
	//@Length(max=4)
	private java.lang.String stamp;
	//
	private java.util.Date addtime;
	//@Length(max=36)
	private java.lang.String addUserId;
	//@Length(max=4)
	private java.lang.String businessType;
	//@Length(max=36)
	private java.lang.String businessId;
	//@Length(max=4)
	private java.lang.String roomType;
	//
	private java.lang.Float diverPrice;
	//
	private java.lang.Float nonDrivePrice;
	//
	private java.lang.Float singleRoomPrice;
	//@Length(max=128)
	private java.lang.String peerName;
	//columns END


		public TdiveActivity(){
		}
		public TdiveActivity(String id) {
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
	

	@Column(name = "start_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	

	@Column(name = "end_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name = "start_addr", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getStartAddr() {
		return this.startAddr;
	}
	
	public void setStartAddr(java.lang.String startAddr) {
		this.startAddr = startAddr;
	}
	
	@Column(name = "end_addr", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getEndAddr() {
		return this.endAddr;
	}
	
	public void setEndAddr(java.lang.String endAddr) {
		this.endAddr = endAddr;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "stamp", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStamp() {
		return this.stamp;
	}
	
	public void setStamp(java.lang.String stamp) {
		this.stamp = stamp;
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
	
	@Column(name = "business_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getBusinessType() {
		return this.businessType;
	}
	
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}
	
	@Column(name = "business_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
	
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}
	
	@Column(name = "room_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getRoomType() {
		return this.roomType;
	}
	
	public void setRoomType(java.lang.String roomType) {
		this.roomType = roomType;
	}
	
	@Column(name = "diver_price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getDiverPrice() {
		return this.diverPrice;
	}
	
	public void setDiverPrice(java.lang.Float diverPrice) {
		this.diverPrice = diverPrice;
	}
	
	@Column(name = "non_drive_price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getNonDrivePrice() {
		return this.nonDrivePrice;
	}
	
	public void setNonDrivePrice(java.lang.Float nonDrivePrice) {
		this.nonDrivePrice = nonDrivePrice;
	}
	
	@Column(name = "single_room_price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getSingleRoomPrice() {
		return this.singleRoomPrice;
	}
	
	public void setSingleRoomPrice(java.lang.Float singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}
	
	@Column(name = "peer_name", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getPeerName() {
		return this.peerName;
	}
	
	public void setPeerName(java.lang.String peerName) {
		this.peerName = peerName;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("StartDate",getStartDate())
			.append("EndDate",getEndDate())
			.append("StartAddr",getStartAddr())
			.append("AddrId",getAddrId())
			.append("EndAddr",getEndAddr())
			.append("Description",getDescription())
			.append("Status",getStatus())
			.append("Stamp",getStamp())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveActivity == false) return false;
		if(this == obj) return true;
		DiveActivity other = (DiveActivity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

