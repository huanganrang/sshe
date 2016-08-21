package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FmMarquee implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String imageName;	
	private java.lang.String url;	
	private java.lang.Integer seq;	
	private java.lang.String loginId;
	private String loginName;
	private java.lang.String goodsId;

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
	public void setImageName(java.lang.String imageName) {
		this.imageName = imageName;
	}
	
	public java.lang.String getImageName() {
		return this.imageName;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}
	
	public java.lang.Integer getSeq() {
		return this.seq;
	}
	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}
	
	public java.lang.String getLoginId() {
		return this.loginId;
	}
	public void setGoodsId(java.lang.String goodsId) {
		this.goodsId = goodsId;
	}
	
	public java.lang.String getGoodsId() {
		return this.goodsId;
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
