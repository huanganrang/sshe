package jb.pageModel;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class FmShopUser implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private Date updatetime;			
	private java.lang.Boolean isdeleted;	
	private java.lang.String shopId;	
	private java.lang.String userId;	

	private FmUser fmShopUser;
	private List<FmPurchase> fmShopPurchaseList;
	private List<FmGoods> fmShopGoodsList;
	

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
	public void setShopId(java.lang.String shopId) {
		this.shopId = shopId;
	}
	
	public java.lang.String getShopId() {
		return this.shopId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}

	public FmUser getFmShopUser() {
		return fmShopUser;
	}

	public void setFmShopUser(FmUser fmShopUser) {
		this.fmShopUser = fmShopUser;
	}

	public List<FmPurchase> getFmShopPurchaseList() {
		return fmShopPurchaseList;
	}

	public void setFmShopPurchaseList(List<FmPurchase> fmShopPurchaseList) {
		this.fmShopPurchaseList = fmShopPurchaseList;
	}

	public List<FmGoods> getFmShopGoodsList() {
		return fmShopGoodsList;
	}

	public void setFmShopGoodsList(List<FmGoods> fmShopGoodsList) {
		this.fmShopGoodsList = fmShopGoodsList;
	}

}
