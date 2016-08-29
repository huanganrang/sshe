package jb.pageModel;

import jb.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FmAuthApply implements java.io.Serializable {

    private static final long serialVersionUID = 5454155825314635342L;

    private java.lang.String id;
    private Date addtime;
    private Date updatetime;
    private java.lang.Boolean isdeleted;
    private java.lang.String userId;
    private java.lang.String type;
    private java.lang.String userName;
    private java.lang.String phone;
    private java.lang.String companyType;
    private java.lang.String images;
    private java.lang.String userCard;
    private java.lang.String authRemark;
    private java.lang.String status;
    private FmUser fmUser;


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

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public java.lang.String getType() {
        return this.type;
    }

    public java.lang.String getTypeName() {
        return Application.getString(this.type, this.type);
    }

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    public java.lang.String getUserName() {
        return this.userName;
    }

    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    public java.lang.String getPhone() {
        return this.phone;
    }

    public void setCompanyType(java.lang.String companyType) {
        this.companyType = companyType;
    }

    public java.lang.String getCompanyType() {
        return this.companyType;
    }

    public java.lang.String getCompanyTypeName() {
        return Application.getString(this.companyType, this.companyType);
    }

    public void setImages(java.lang.String images) {
        this.images = images;
    }

    public java.lang.String getImages() {
        return this.images;
    }

    public String[] getImageList() {
        if (this.images == null) return new String[]{};
        return this.images.split("[;]");
    }

    public void setUserCard(java.lang.String userCard) {
        this.userCard = userCard;
    }

    public java.lang.String getUserCard() {
        return this.userCard;
    }

    public void setAuthRemark(java.lang.String authRemark) {
        this.authRemark = authRemark;
    }

    public java.lang.String getAuthRemark() {
        return this.authRemark;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public java.lang.String getStatusName() {
        return Application.getString(this.status, this.status);
    }

    public FmUser getFmUser() {
        return fmUser;
    }

    public void setFmUser(FmUser fmUser) {
        this.fmUser = fmUser;
    }
}
