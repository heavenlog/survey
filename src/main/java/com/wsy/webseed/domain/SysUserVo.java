package com.wsy.webseed.domain;


import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.wsy.webseed.domain.entity.SysUser;
import com.wsy.webseed.util.PaginationRow;


public class SysUserVo extends SysUser implements java.io.Serializable, PaginationRow {

    private static final long serialVersionUID = 5283986634527499220L;


    @Override
    public SimplePropertyPreFilter getSimplePropertyPreFilter() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(SysUserVo.class, "id", "loginName", "name", "phone",
                "email", "updateTime", "createTime");
        return filter;
    }

}
