package jb.pageModel;

import jb.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FmAd implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String goodsName;	
	private java.lang.String url;	
	private java.lang.String local;	
	private java.lang.String status;	
	private java.lang.String channel;	
	private java.lang.String type;	
	private java.lang.String goodsId;	
	private java.lang.String loginId;

	private String loginName;
	private FmGoods fmGoods;

	

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
	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}
	
	public java.lang.String getGoodsName() {
		return this.goodsName;
	}
	public java.lang.String getGoodsNameName() {
		return Application.getString(this.getGoodsName());
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setLocal(java.lang.String local) {
		this.local = local;
	}
	
	public java.lang.String getLocal() {
		return this.local;
	}
	public java.lang.String getLocalName() {
		return Application.getString(this.local);
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
	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}
	
	public java.lang.String getChannel() {
		return this.channel;
	}

	public java.lang.String getChannelName() {
		return Application.getString(this.channel);
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setGoodsId(java.lang.String goodsId) {
		this.goodsId = goodsId;
	}
	
	public java.lang.String getGoodsId() {
		return this.goodsId;
	}
	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}
	
	public java.lang.String getLoginId() {
		return this.loginId;
	}

	public FmGoods getFmGoods() {
		return fmGoods;
	}

	public void setFmGoods(FmGoods fmGoods) {
		this.fmGoods = fmGoods;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
