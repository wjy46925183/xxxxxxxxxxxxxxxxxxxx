package com.dlg.data.home.model;

import java.io.Serializable;

/**
 * 作者：小明
 * 主要功能：零工条件列表查询
 * 创建时间：2017/7/4 0004 19:10
 */
public class HistoryBean implements Serializable {
    private String demandType;//雇主需求类型
    private String postName;//岗位名称
    private String jobType;//零工类型
    private String jobTypeName;//零工类型名称
    private String jobMeterUnit;//计量单位
    private String price;//最低报酬
    private String id;//零工条件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getJobMeterUnit() {

        return jobMeterUnit;
    }

    public void setJobMeterUnit(String jobMeterUnit) {
        this.jobMeterUnit = jobMeterUnit;
    }

    public String getJobTypeName() {

        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public String getJobType() {

        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getPostName() {

        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


    public String getDemandType() {
        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }
}
