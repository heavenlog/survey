package com.wsy.webseed.domain;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.wsy.webseed.domain.entity.SysAuth;
import com.wsy.webseed.util.PaginationRow;

import java.util.List;

public class SysAuthVo extends SysAuth implements java.io.Serializable, PaginationRow {
    private static final long serialVersionUID = -4928090824649463747L;

    public SysAuthVo() {
    }

    @Override
    public SimplePropertyPreFilter getSimplePropertyPreFilter() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(SysAuthVo.class, "id", "pid", "name", "url",
                "updateTime", "createTime");
        return filter;
    }

}
