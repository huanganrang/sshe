
/*
 * @author John
 * @date - 2015-07-06
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dive_store_address")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveStoreAddress implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveStoreAddress";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_STORE_ID = "度假村ID";
	public static final String ALIAS_ADDRESS_ID = "潜点ID";
	public static final String ALIAS_ADDTIME = "addtime";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String storeId;
	//@Length(max=36)
	private java.lang.String addressId;
	//
	private java.util.Date addtime;
	//columns END


		public TdiveStoreAddress(){
		}
		public TdiveStoreAddress(String id) {
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
	
	@Column(name = "store_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getStoreId() {
		return this.storeId;
	}
	
	public void setStoreId(java.lang.String storeId) {
		this.storeId = storeId;
	}
	
	@Column(name = "address_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getAddressId() {
		return this.addressId;
	}
	
	public void setAddressId(java.lang.String addressId) {
		this.addressId = addressId;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
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
			.append("StoreId",getStoreId())
			.append("AddressId",getAddressId())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveStoreAddress == false) return false;
		if(this == obj) return true;
		DiveStoreAddress other = (DiveStoreAddress)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

