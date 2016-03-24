package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class DiveLogDetail implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private String id;
	private String logId;
	private String sumary;
	private String fileSrc;
	private String lgX;
	private String lgY;
	private Date addtime;			

	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}

	
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	public String getLogId() {
		return this.logId;
	}
	public void setSumary(String sumary) {
		this.sumary = sumary;
	}
	
	public String getSumary() {
		return this.sumary;
	}
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	
	public String getFileSrc() {
		return this.fileSrc;
	}
	public void setLgX(String lgX) {
		this.lgX = lgX;
	}
	
	public String getLgX() {
		return this.lgX;
	}
	public void setLgY(String lgY) {
		this.lgY = lgY;
	}
	
	public String getLgY() {
		return this.lgY;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}

}
