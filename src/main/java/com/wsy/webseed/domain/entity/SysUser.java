package com.wsy.webseed.domain.entity;


import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.wsy.webseed.util.PaginationRow;

import java.util.Date;


public class SysUser implements java.io.Serializable, PaginationRow {

    private static final long serialVersionUID = 5283986634527499220L;

    private Long id;
    private String loginName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Integer sex;
    private Date updateTime;
    private Date createTime;

    public SysUser() {
    }

    public SysUser(Long id, String loginName, String password, Date updateTime, Date createTime) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public SysUser(Long id, String loginName, String password, String name, String phone, String email,
                   Date updateTime, Date createTime) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public SysUser(String loginName, String password, String name, String phone, String email, Date updateTime, Date createTime) {
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public SimplePropertyPreFilter getSimplePropertyPreFilter() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(SysUser.class, "id", "loginName", "name", "phone",
                "email", "updateTime", "createTime");
        return filter;
    }

}
