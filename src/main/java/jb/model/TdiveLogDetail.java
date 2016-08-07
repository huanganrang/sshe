
/*
 * @author John
 * @date - 2016-03-24
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "dive_log_detail")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveLogDetail implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveLogDetail";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_LOG_ID = "日志ID";
	public static final String ALIAS_SUMARY = "标题";
	public static final String ALIAS_FILE_SRC = "图片路径";
	public static final String ALIAS_LG_X = "经度";
	public static final String ALIAS_LG_Y = "纬度";
	public static final String ALIAS_ADDTIME = "发布时间";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private String id;
	//@Length(max=36)
	private String logId;
	//@Length(max=256)
	private String sumary;
	//@Length(max=2147483647)
	private String fileSrc;
	//@Length(max=18)
	private String lgX;
	//@Length(max=18)
	private String lgY;
	//
	private Date addtime;
	//columns END


		public TdiveLogDetail(){
		}
		public TdiveLogDetail(String id) {
			this.id = id;
		}
	

	public void setId(String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}
	
	@Column(name = "log_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getLogId() {
		return this.logId;
	}
	
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	@Column(name = "sumary", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getSumary() {
		return this.sumary;
	}
	
	public void setSumary(String sumary) {
		this.sumary = sumary;
	}
	
	@Column(name = "file_src", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getFileSrc() {
		return this.fileSrc;
	}
	
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	
	@Column(name = "lg_x", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getLgX() {
		return this.lgX;
	}
	
	public void setLgX(String lgX) {
		this.lgX = lgX;
	}
	
	@Column(name = "lg_y", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getLgY() {
		return this.lgY;
	}
	
	public void setLgY(String lgY) {
		this.lgY = lgY;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Date getAddtime() {
		return this.addtime;
	}

	@Override
	public void setUpdatetime(Date date) {

	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LogId",getLogId())
			.append("Sumary",getSumary())
			.append("FileSrc",getFileSrc())
			.append("LgX",getLgX())
			.append("LgY",getLgY())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveLogDetail == false) return false;
		if(this == obj) return true;
		DiveLogDetail other = (DiveLogDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

