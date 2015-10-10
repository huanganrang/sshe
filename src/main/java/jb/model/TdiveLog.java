
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
@Table(name = "dive_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TdiveLog implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DiveLog";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_LOG_TYPE = "日志类型";
	public static final String ALIAS_FILE_SRC = "纸质图片";
	public static final String ALIAS_ACCOUNT_ID = "会员ID";
	public static final String ALIAS_DIVE_TYPE = "潜水类型";
	public static final String ALIAS_DIVE_DATE = "潜水时间";
	public static final String ALIAS_DIVE_ADDRESS = "潜水地点";
	public static final String ALIAS_WEATHER = "天气";
	public static final String ALIAS_WATER_TEMPERATURE = "水温";
	public static final String ALIAS_SEEING = "能见度";
	public static final String ALIAS_IN_TIME = "入水时间";
	public static final String ALIAS_OUT_TIME = "出水时间";
	public static final String ALIAS_DIVE_HEIGHT = "潜水深度";
	public static final String ALIAS_DIVE_WEITH = "潜水距离";
	public static final String ALIAS_WEATHER_TEMPERATURE = "天气温度";
	public static final String ALIAS_WIND_POWER = "风力";
	public static final String ALIAS_GAS_START = "开始氧量";
	public static final String ALIAS_GAS_END = "结束氧量";
	public static final String ALIAS_HIGH_GAS = "含氧量";
	public static final String ALIAS_ADDTIME = "addtime";
	
	//date formats
	public static final String FORMAT_DIVE_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_IN_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_OUT_TIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=4)
	private java.lang.String logType;
	//@Length(max=256)
	private java.lang.String fileSrc;
	//@Length(max=36)
	private java.lang.String accountId;
	//@Length(max=4)
	private java.lang.String diveType;
	//
	private java.util.Date diveDate;
	//@Length(max=4)
	private java.lang.String weather;
	//
	private java.lang.Float waterTemperature;
	//
	private java.lang.Float seeing;
	//
	private java.util.Date inTime;
	//
	private java.util.Date outTime;
	//
	private java.lang.Float diveHeight;
	//
	private java.lang.Float diveWeith;
	//
	private java.lang.Float weatherTemperature;
	//@Length(max=4)
	private java.lang.String windPower;
	//
	private java.lang.Float gasStart;
	//
	private java.lang.Float gasEnd;
	//
	private java.util.Date addtime;
	//@Length(max=256)
	private java.lang.String diveAddress;
	//@Length(max=128)
	private java.lang.String highGas;
	//@Length(max=256)
	private java.lang.String sumary;
	//columns END


		public TdiveLog(){
		}
		public TdiveLog(String id) {
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
	
	@Column(name = "log_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getLogType() {
		return this.logType;
	}
	
	public void setLogType(java.lang.String logType) {
		this.logType = logType;
	}
	
	@Column(name = "file_src", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public java.lang.String getFileSrc() {
		return this.fileSrc;
	}
	
	public void setFileSrc(java.lang.String fileSrc) {
		this.fileSrc = fileSrc;
	}
	
	@Column(name = "account_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getAccountId() {
		return this.accountId;
	}
	
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "dive_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getDiveType() {
		return this.diveType;
	}
	
	public void setDiveType(java.lang.String diveType) {
		this.diveType = diveType;
	}
	

	@Column(name = "dive_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getDiveDate() {
		return this.diveDate;
	}
	
	public void setDiveDate(java.util.Date diveDate) {
		this.diveDate = diveDate;
	}
	
	@Column(name = "weather", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getWeather() {
		return this.weather;
	}
	
	public void setWeather(java.lang.String weather) {
		this.weather = weather;
	}
	
	@Column(name = "water_temperature", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getWaterTemperature() {
		return this.waterTemperature;
	}
	
	public void setWaterTemperature(java.lang.Float waterTemperature) {
		this.waterTemperature = waterTemperature;
	}
	
	@Column(name = "seeing", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getSeeing() {
		return this.seeing;
	}
	
	public void setSeeing(java.lang.Float seeing) {
		this.seeing = seeing;
	}
	

	@Column(name = "in_time", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getInTime() {
		return this.inTime;
	}
	
	public void setInTime(java.util.Date inTime) {
		this.inTime = inTime;
	}
	

	@Column(name = "out_time", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getOutTime() {
		return this.outTime;
	}
	
	public void setOutTime(java.util.Date outTime) {
		this.outTime = outTime;
	}
	
	@Column(name = "dive_height", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getDiveHeight() {
		return this.diveHeight;
	}
	
	public void setDiveHeight(java.lang.Float diveHeight) {
		this.diveHeight = diveHeight;
	}
	
	@Column(name = "dive_weith", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getDiveWeith() {
		return this.diveWeith;
	}
	
	public void setDiveWeith(java.lang.Float diveWeith) {
		this.diveWeith = diveWeith;
	}
	
	@Column(name = "weather_temperature", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getWeatherTemperature() {
		return this.weatherTemperature;
	}
	
	public void setWeatherTemperature(java.lang.Float weatherTemperature) {
		this.weatherTemperature = weatherTemperature;
	}
	
	@Column(name = "wind_power", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getWindPower() {
		return this.windPower;
	}
	
	public void setWindPower(java.lang.String windPower) {
		this.windPower = windPower;
	}
	
	@Column(name = "gas_start", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getGasStart() {
		return this.gasStart;
	}
	
	public void setGasStart(java.lang.Float gasStart) {
		this.gasStart = gasStart;
	}
	
	@Column(name = "gas_end", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getGasEnd() {
		return this.gasEnd;
	}
	
	public void setGasEnd(java.lang.Float gasEnd) {
		this.gasEnd = gasEnd;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	@Column(name = "dive_address", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getDiveAddress() {
		return diveAddress;
	}
	
	public void setDiveAddress(java.lang.String diveAddress) {
		this.diveAddress = diveAddress;
	}
	
	@Column(name = "high_gas", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getHighGas() {
		return highGas;
	}
	
	public void setHighGas(java.lang.String highGas) {
		this.highGas = highGas;
	}
	
	@Column(name = "sumary", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getSumary() {
		return sumary;
	}
	
	public void setSumary(java.lang.String sumary) {
		this.sumary = sumary;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LogType",getLogType())
			.append("FileSrc",getFileSrc())
			.append("UserId",getUserId())
			.append("DiveType",getDiveType())
			.append("DiveDate",getDiveDate())
			.append("Weather",getWeather())
			.append("WaterTemperature",getWaterTemperature())
			.append("Seeing",getSeeing())
			.append("InTime",getInTime())
			.append("OutTime",getOutTime())
			.append("DiveHeight",getDiveHeight())
			.append("DiveWeith",getDiveWeith())
			.append("WeatherTemperature",getWeatherTemperature())
			.append("WindPower",getWindPower())
			.append("GasStart",getGasStart())
			.append("GasEnd",getGasEnd())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DiveLog == false) return false;
		if(this == obj) return true;
		DiveLog other = (DiveLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

