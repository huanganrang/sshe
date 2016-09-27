package jb.pageModel;

import jb.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FmGoods implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String name;	
	private java.lang.Float price;	
	private java.lang.String unit;	
	private java.lang.Float minBatch;	
	private java.lang.String status;	
	private java.lang.String bornArea;	
	private java.lang.String storage;	
	private Date offlineTime;			
	private java.lang.String contactor;	
	private java.lang.String contactorMobile;	
	private java.lang.String goalArea;	
	private Date preOnlineTime;			
	private java.lang.String transactionArea;	
	private Date sendTime;			
	private Date transactionTime;			
	private java.lang.String carNo;	
	private java.lang.String images;	
	private java.lang.String description;	
	private java.lang.String diameter;	
	private java.lang.String diameterUnit;	
	private java.lang.String color;	
	private java.lang.String isPack;	
	private java.lang.String pack;	
	private java.lang.String formatDesc;	
	private java.lang.String onlineStatus;	
	private java.lang.String userId;
	//发布人信息
	private FmUser fmUser;
	private java.lang.String parentId;	
	private java.lang.Integer accessNum;	
	private java.lang.String source;	
	private java.lang.String outerId;	
	private java.lang.String outerNumber;	
	private java.lang.String grade;	
	private java.lang.String extFields;
	private String typeName;
	private String[] goodsIdList;



	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public Date getUpdatetime() {
		return this.updatetime;
	}
	public void setIsdeleted(java.lang.Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public java.lang.Boolean getIsdeleted() {
		return this.isdeleted;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}

	public java.lang.String getNameName() {
		return Application.getString(this.name);
	}

	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	public java.lang.Float getPrice() {
		return this.price;
	}
	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}
	
	public java.lang.String getUnit() {
		return this.unit;
	}

	public java.lang.String getUnitName() {
		return Application.getString(this.unit);
	}

	public void setMinBatch(java.lang.Float minBatch) {
		this.minBatch = minBatch;
	}
	
	public java.lang.Float getMinBatch() {
		return this.minBatch;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}

	public java.lang.String getStatusName() {
		return Application.getString(this.status);
	}

	public void setBornArea(java.lang.String bornArea) {
		this.bornArea = bornArea;
	}
	
	public java.lang.String getBornArea() {
		return this.bornArea;
	}
	public void setStorage(java.lang.String storage) {
		this.storage = storage;
	}
	
	public java.lang.String getStorage() {
		return this.storage;
	}
	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}
	
	public Date getOfflineTime() {
		return this.offlineTime;
	}
	public void setContactor(java.lang.String contactor) {
		this.contactor = contactor;
	}
	
	public java.lang.String getContactor() {
		return this.contactor;
	}
	public void setContactorMobile(java.lang.String contactorMobile) {
		this.contactorMobile = contactorMobile;
	}
	
	public java.lang.String getContactorMobile() {
		return this.contactorMobile;
	}
	public void setGoalArea(java.lang.String goalArea) {
		this.goalArea = goalArea;
	}
	
	public java.lang.String getGoalArea() {
		return this.goalArea;
	}
	public void setPreOnlineTime(Date preOnlineTime) {
		this.preOnlineTime = preOnlineTime;
	}
	
	public Date getPreOnlineTime() {
		return this.preOnlineTime;
	}
	public void setTransactionArea(java.lang.String transactionArea) {
		this.transactionArea = transactionArea;
	}
	
	public java.lang.String getTransactionArea() {
		return this.transactionArea;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	public Date getSendTime() {
		return this.sendTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	public Date getTransactionTime() {
		return this.transactionTime;
	}
	public void setCarNo(java.lang.String carNo) {
		this.carNo = carNo;
	}
	
	public java.lang.String getCarNo() {
		return this.carNo;
	}
	public void setImages(java.lang.String images) {
		this.images = images;
	}
	
	public java.lang.String getImages() {
		return this.images;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setDiameter(java.lang.String diameter) {
		this.diameter = diameter;
	}
	
	public java.lang.String getDiameter() {
		return this.diameter;
	}
	public void setDiameterUnit(java.lang.String diameterUnit) {
		this.diameterUnit = diameterUnit;
	}
	
	public java.lang.String getDiameterUnit() {
		return this.diameterUnit;
	}

	public java.lang.String getDiameterUnitName() {
		return Application.getString(this.diameterUnit);
	}

	public void setColor(java.lang.String color) {
		this.color = color;
	}
	
	public java.lang.String getColor() {
		return this.color;
	}

	public java.lang.String getColorName() {
		return Application.getString(this.color);
	}

	public void setIsPack(java.lang.String isPack) {
		this.isPack = isPack;
	}
	
	public java.lang.String getIsPack() {
		return this.isPack;
	}

	public java.lang.String getIsPackName() {
		return Application.getString(this.isPack);
	}

	public void setPack(java.lang.String pack) {
		this.pack = pack;
	}
	
	public java.lang.String getPack() {
		return this.pack;
	}

	public java.lang.String getPackName() {
		return Application.getString(this.pack);
	}

	public void setFormatDesc(java.lang.String formatDesc) {
		this.formatDesc = formatDesc;
	}
	
	public java.lang.String getFormatDesc() {
		return this.formatDesc;
	}
	public void setOnlineStatus(java.lang.String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	
	public java.lang.String getOnlineStatus() {
		return this.onlineStatus;
	}

	public java.lang.String getOnlineStatusName() {
		return Application.getString(this.onlineStatus);
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}
	
	public java.lang.String getParentId() {
		return this.parentId;
	}
	public void setAccessNum(java.lang.Integer accessNum) {
		this.accessNum = accessNum;
	}
	
	public java.lang.Integer getAccessNum() {
		return this.accessNum;
	}
	public void setSource(java.lang.String source) {
		this.source = source;
	}
	
	public java.lang.String getSource() {
		return this.source;
	}
	public void setOuterId(java.lang.String outerId) {
		this.outerId = outerId;
	}
	
	public java.lang.String getOuterId() {
		return this.outerId;
	}
	public void setOuterNumber(java.lang.String outerNumber) {
		this.outerNumber = outerNumber;
	}
	
	public java.lang.String getOuterNumber() {
		return this.outerNumber;
	}
	public void setGrade(java.lang.String grade) {
		this.grade = grade;
	}
	
	public java.lang.String getGrade() {
		return this.grade;
	}

	public java.lang.String getGradeName() {
		return Application.getString(this.grade);
	}

	public String getExtFields() {
		return extFields;
	}

	public void setExtFields(String extFields) {
		this.extFields = extFields;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String[] getImageList() {
		if (this.images == null) return new String[]{};
		return this.images.split("[;]");
	}

	public FmUser getFmUser() {
		return fmUser;
	}

	public void setFmUser(FmUser fmUser) {
		this.fmUser = fmUser;
	}

	public String[] getGoodsIdList() {
		return goodsIdList;
	}

	public void setGoodsIdList(String[] goodsIdList) {
		this.goodsIdList = goodsIdList;
	}
}
