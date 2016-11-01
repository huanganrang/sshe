package jb.pageModel;

import jb.listener.Application;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class FmPurchase implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String name;	
	private java.lang.Float startPrice;	
	private java.lang.Float endPrice;	
	private java.lang.String unit;	
	private java.lang.Float minNum;	
	private java.lang.Float maxNum;	
	private java.lang.String status;	
	private java.lang.String bornArea;	
	private java.lang.String transactionArea;	
	private Date startTime;			
	private Date endTime;			
	private java.lang.String images;	
	private java.lang.String require;	
	private java.lang.String diameter;	
	private java.lang.String diameterUnit;	
	private java.lang.String color;	
	private java.lang.String isPack;	
	private java.lang.String pack;	
	private java.lang.String formatDesc;	
	private java.lang.String voiceUrl;	
	private java.lang.Integer voiceDuration;	
	private java.lang.String userId;	
	private java.lang.String onlineStatus;
	private java.lang.String extFields;
	private int fmPurchaseUserCount;
	private List<FmPurchaseUser> fmPurchaseUserList;
	private FmUser fmUser;
	private Boolean isAttention;
	private String typeName; //商品品类
	private Boolean topIndex;

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

	public void setStartPrice(java.lang.Float startPrice) {
		this.startPrice = startPrice;
	}
	
	public java.lang.Float getStartPrice() {
		return this.startPrice;
	}
	public void setEndPrice(java.lang.Float endPrice) {
		this.endPrice = endPrice;
	}
	
	public java.lang.Float getEndPrice() {
		return this.endPrice;
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
	public void setMinNum(java.lang.Float minNum) {
		this.minNum = minNum;
	}
	
	public java.lang.Float getMinNum() {
		return this.minNum;
	}
	public void setMaxNum(java.lang.Float maxNum) {
		this.maxNum = maxNum;
	}
	
	public java.lang.Float getMaxNum() {
		return this.maxNum;
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
	public void setTransactionArea(java.lang.String transactionArea) {
		this.transactionArea = transactionArea;
	}
	
	public java.lang.String getTransactionArea() {
		return this.transactionArea;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	public void setImages(java.lang.String images) {
		this.images = images;
	}
	
	public java.lang.String getImages() {
		return this.images;
	}
	public String[] getImageList() {
		if (this.images == null) return new String[]{};
		return this.images.split("[;,]");
	}
	public void setRequire(java.lang.String require) {
		this.require = require;
	}
	
	public java.lang.String getRequire() {
		return this.require;
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
	public void setVoiceUrl(java.lang.String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	
	public java.lang.String getVoiceUrl() {
		return this.voiceUrl;
	}
	public void setVoiceDuration(java.lang.Integer voiceDuration) {
		this.voiceDuration = voiceDuration;
	}
	
	public java.lang.Integer getVoiceDuration() {
		return this.voiceDuration;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
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

	public String getExtFields() {
		return extFields;
	}

	public void setExtFields(String extFields) {
		this.extFields = extFields;
	}

	public List<FmPurchaseUser> getFmPurchaseUserList() {
		return fmPurchaseUserList;
	}

	public void setFmPurchaseUserList(List<FmPurchaseUser> fmPurchaseUserList) {
		this.fmPurchaseUserList = fmPurchaseUserList;
	}

	public FmUser getFmUser() {
		return fmUser;
	}

	public void setFmUser(FmUser fmUser) {
		this.fmUser = fmUser;
	}

	public Boolean getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Boolean isAttention) {
		this.isAttention = isAttention;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getFmPurchaseUserCount() {
		return fmPurchaseUserCount;
	}

	public void setFmPurchaseUserCount(int fmPurchaseUserCount) {
		this.fmPurchaseUserCount = fmPurchaseUserCount;
	}

	public Boolean getTopIndex() {
		return topIndex;
	}

	public void setTopIndex(Boolean topIndex) {
		this.topIndex = topIndex;
	}

}
