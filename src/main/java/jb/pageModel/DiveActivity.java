package jb.pageModel;

import java.util.Date;
import java.util.List;

import jb.listener.Application;

public class DiveActivity implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String name;	
	private Date startDate;			
	private Date endDate;			
	private java.lang.String startAddr;	
	private java.lang.String endAddr;	
	private java.lang.String description;	
	private java.lang.String status;	
	private java.lang.String stamp;	
	private Date addtime;		
	private java.lang.String addUserId;
	private java.lang.String businessType;	
	private java.lang.String businessId;	
	private java.lang.String roomType;	
	private java.lang.Float diverPrice;	
	private java.lang.Float nonDrivePrice;	
	private java.lang.Float singleRoomPrice;	
	private java.lang.String peerName;

	private int commentNum; //评论数量
	private int collectNum; //收藏数量
	private int applyNum;  //报名人数
	private int praiseNum;  //赞人数
	
	private boolean isCollect;
	private boolean isPraise;
	private boolean isApply; 
	
	//报名者
	private List<DiveAccount> applies;
	//评论
	private List<DiveActivityComment> diveActivityCommentList;

	private Object business;
	
	public String getRoomTypeZh() {
		return Application.getString(this.roomType);
	}
	
	public String getBusinessTypeZh() {
		return Application.getString(this.businessType);
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	public void setStartAddr(java.lang.String startAddr) {
		this.startAddr = startAddr;
	}
	
	public java.lang.String getStartAddr() {
		return this.startAddr;
	}
	public void setEndAddr(java.lang.String endAddr) {
		this.endAddr = endAddr;
	}
	
	public java.lang.String getEndAddr() {
		return this.endAddr;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		if(this.startDate != null && this.endDate != null) {
			Date now = new Date();
			if(now.before(this.startDate)) {
				return "AT01"; // 报名中
			} else if(now.after(this.endDate)) {
				return "AT03"; // 已结束
			} else {
				return "AT02"; // 进行中
			}
		}
		
		return this.status;
	}
	
	public void setStamp(java.lang.String stamp) {
		this.stamp = stamp;
	}
	
	public java.lang.String getStamp() {
		return this.stamp;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	
	public java.lang.String getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(java.lang.String addUserId) {
		this.addUserId = addUserId;
	}
	

	public java.lang.String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	public java.lang.String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	public java.lang.String getRoomType() {
		return roomType;
	}

	public void setRoomType(java.lang.String roomType) {
		this.roomType = roomType;
	}

	public java.lang.Float getDiverPrice() {
		return diverPrice;
	}

	public void setDiverPrice(java.lang.Float diverPrice) {
		this.diverPrice = diverPrice;
	}

	public java.lang.Float getNonDrivePrice() {
		return nonDrivePrice;
	}

	public void setNonDrivePrice(java.lang.Float nonDrivePrice) {
		this.nonDrivePrice = nonDrivePrice;
	}

	public java.lang.Float getSingleRoomPrice() {
		return singleRoomPrice;
	}

	public void setSingleRoomPrice(java.lang.Float singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}

	public java.lang.String getPeerName() {
		return peerName;
	}

	public void setPeerName(java.lang.String peerName) {
		this.peerName = peerName;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public List<DiveAccount> getApplies() {
		return applies;
	}

	public void setApplies(List<DiveAccount> applies) {
		this.applies = applies;
	}

	public List<DiveActivityComment> getDiveActivityCommentList() {
		return diveActivityCommentList;
	}

	public void setDiveActivityCommentList(
			List<DiveActivityComment> diveActivityCommentList) {
		this.diveActivityCommentList = diveActivityCommentList;
	}

	public boolean isCollect() {
		return isCollect;
	}

	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
	
	public boolean isPraise() {
		return isPraise;
	}

	public void setPraise(boolean isPraise) {
		this.isPraise = isPraise;
	}
	
	public boolean isApply() {
		return isApply;
	}

	public void setApply(boolean isApply) {
		this.isApply = isApply;
	}

	public Object getBusiness() {
		return business;
	}

	public void setBusiness(Object business) {
		this.business = business;
	}
	
	
}
