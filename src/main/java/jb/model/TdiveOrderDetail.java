
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
@Table(name = "dive_order_detail")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveOrderDetail implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveOrderDetail";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_ORDER_ID = "订单ID";
	public static final String ALIAS_BUSINESS_ID = "业务ID";
	public static final String ALIAS_BUSINESS_TYPE = "业务类型";
	public static final String ALIAS_NUMBER = "数量";
	public static final String ALIAS_PRICE = "价格";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String orderId;
	//@Length(max=36)
	private java.lang.String businessId;
	//@Length(max=4)
	private java.lang.String businessType;
	//
	private java.lang.Integer number;
	//
	private java.lang.Float price;
	//columns END


		public TdiveOrderDetail(){
		}
		public TdiveOrderDetail(String id) {
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
	
	@Column(name = "order_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "business_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
	
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}
	
	@Column(name = "business_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getBusinessType() {
		return this.businessType;
	}
	
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}
	
	@Column(name = "number", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNumber() {
		return this.number;
	}
	
	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}
	
	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getPrice() {
		return this.price;
	}
	
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrderId",getOrderId())
			.append("BusinessId",getBusinessId())
			.append("BusinessType",getBusinessType())
			.append("Number",getNumber())
			.append("Price",getPrice())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveOrderDetail == false) return false;
		if(this == obj) return true;
		DiveOrderDetail other = (DiveOrderDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

