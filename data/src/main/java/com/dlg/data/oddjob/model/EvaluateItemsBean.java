package com.dlg.data.oddjob.model;

/**
 * wangjinya
 * 雇主和雇员评价 实体类
 */

public class EvaluateItemsBean {
    /**
     * dataWeight : 5
     * dataValue : 工作态度好
     * dataName : 雇主评价雇员
     * id : 47
     * specialIdentification : 5
     * dataCode : employer.evaluation_8
     * groupCode : employer.evaluation
     */

    private int dataWeight;//星数
    private String dataValue;//
    private String dataName;
    private String id;
    private int specialIdentification;
    private String dataCode;
    private String groupCode;

    public int getDataWeight() {
        return dataWeight;
    }

    public void setDataWeight(int dataWeight) {
        this.dataWeight = dataWeight;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpecialIdentification() {
        return specialIdentification;
    }

    public void setSpecialIdentification(int specialIdentification) {
        this.specialIdentification = specialIdentification;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
