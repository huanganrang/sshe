
/*
 * @author John
 * @date - 2015-07-22
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dive_order")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveOrder implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveOrder";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_ACCOUNT_ID = "用户ID";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_EXPRESS_NAME = "快递公司";
	public static final String ALIAS_EXPRESS_NO = "快递单号";
	public static final String ALIAS_PAY_WAY = "支付方式";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_PAY_STATUS = "支付状态";
	public static final String ALIAS_ORDER_STATUS = "订单状态";
	public static final String ALIAS_SEND_STATUS = "发货状态";
	public static final String ALIAS_PAYTIME = "支付时间";
	public static final String ALIAS_ADDTIME = "下单时间";
	
	//date formats
	public static final String FORMAT_PAYTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String accountId;
	//@Length(max=256)
	private java.lang.String address;
	//@Length(max=128)
	private java.lang.String expressName;
	//@Length(max=128)
	private java.lang.String expressNo;
	//@Length(max=128)
	private java.lang.String payWay;
	//@Length(max=256)
	private java.lang.String remark;
	//@Length(max=4)
	private java.lang.String payStatus;
	//@Length(max=4)
	private java.lang.String orderStatus;
	//@Length(max=4)
	private java.lang.String sendStatus;
	//
	private java.util.Date paytime;
	//
	private java.util.Date addtime;
	//columns END


		public TdiveOrder(){
		}
		public TdiveOrder(String id) {
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
	
	@Column(name = "account_Id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getAccountId() {
		return this.accountId;
	}
	
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	@Column(name = "express_name", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getExpressName() {
		return this.expressName;
	}
	
	public void setExpressName(java.lang.String expressName) {
		this.expressName = expressName;
	}
	
	@Column(name = "express_no", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getExpressNo() {
		return this.expressNo;
	}
	
	public void setExpressNo(java.lang.String expressNo) {
		this.expressNo = expressNo;
	}
	
	@Column(name = "payWay", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getPayWay() {
		return this.payWay;
	}
	
	public void setPayWay(java.lang.String payWay) {
		this.payWay = payWay;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	@Column(name = "pay_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getPayStatus() {
		return this.payStatus;
	}
	
	public void setPayStatus(java.lang.String payStatus) {
		this.payStatus = payStatus;
	}
	
	@Column(name = "order_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getOrderStatus() {
		return this.orderStatus;
	}
	
	public void setOrderStatus(java.lang.String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Column(name = "send_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getSendStatus() {
		return this.sendStatus;
	}
	
	public void setSendStatus(java.lang.String sendStatus) {
		this.sendStatus = sendStatus;
	}
	

	@Column(name = "paytime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getPaytime() {
		return this.paytime;
	}
	
	public void setPaytime(java.util.Date paytime) {
		this.paytime = paytime;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AccountId",getAccountId())
			.append("Address",getAddress())
			.append("ExpressName",getExpressName())
			.append("ExpressNo",getExpressNo())
			.append("PayWay",getPayWay())
			.append("Remark",getRemark())
			.append("PayStatus",getPayStatus())
			.append("OrderStatus",getOrderStatus())
			.append("SendStatus",getSendStatus())
			.append("Paytime",getPaytime())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveOrder == false) return false;
		if(this == obj) return true;
		DiveOrder other = (DiveOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

