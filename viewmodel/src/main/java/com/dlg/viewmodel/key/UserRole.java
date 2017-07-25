package com.dlg.viewmodel.key;

/**
 * 作者：wangdakuan
 * 主要功能：用户角色 1 雇员 2 雇主 3 企业 4 为代理商
 * 创建时间：2017/7/7 09:59
 */
public enum UserRole {
    employee(1),hirer(2),enterprise(3),agent(4);

    public int role;

    UserRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
