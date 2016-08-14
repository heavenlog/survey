package com.wsy.webseed.dao;


import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SysRoleVo;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends ISqlMapper {

    public List<SysRoleVo> query(Map<String, Object> param);

    public int save(SysRoleVo role);

    public int edit(SysRoleVo role);

    public int del(Long id);
}
