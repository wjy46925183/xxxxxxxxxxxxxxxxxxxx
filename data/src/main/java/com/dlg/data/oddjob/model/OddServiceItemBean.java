package com.dlg.data.oddjob.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：零工服务列表数据对象
 * 创建时间：2017/7/13 13:13
 */
public class OddServiceItemBean implements Serializable{

    /**
     * id : 27633475063786734464263093215
     * active : 1
     * version : 2
     * createTime : 1498013684000
     * createUserId : 27625710326921910807359539058
     * modifyTime : 1498013826000
     * modifyUserId : null
     * userId : 27625710326921910807359539058
     * demandType : 1
     * serviceName : 我的服务01(修改)
     * serviceType : job.type_5
     * serviceTypeName : 教育培训
     * serviceMeterUnit : 1
     * price : 100
     * serviceDescription : 描述就是为了不让为空
     * imagesUrlList : [" /763380/27633478070606018462410978278.jpg"]
     */

    private String id; //id
    private int active;
    private int version;
    private long createTime; //创建时间
    private String createUserId; //创建者ID
    private long modifyTime; //修改时间
    private String modifyUserId; //修改者ID
    private String userId; //用户ID
    private int demandType;  //需求类型(1.工作日,2.双休日,3.计件)
    private String serviceName; //服务名称
    private String serviceType; //服务类型
    private String serviceTypeName; //服务类型名称
    private int serviceMeterUnit;
    private double price;
    private String serviceDescription; //描述
    private List<String> imagesUrlList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDemandType() {
        return demandType;
    }

    public void setDemandType(int demandType) {
        this.demandType = demandType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public int getServiceMeterUnit() {
        return serviceMeterUnit;
    }

    public void setServiceMeterUnit(int serviceMeterUnit) {
        this.serviceMeterUnit = serviceMeterUnit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public List<String> getImagesUrlList() {
        return imagesUrlList;
    }

    public void setImagesUrlList(List<String> imagesUrlList) {
        this.imagesUrlList = imagesUrlList;
    }
}
