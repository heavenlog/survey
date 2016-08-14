package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SysRoleVo;
import com.wsy.webseed.domain.SysUserRoleVo;
import com.wsy.webseed.domain.SysUserVo;

import java.util.List;


public interface SysUserRoleMapper extends ISqlMapper {

    public void save(List<SysUserRoleVo> userRoles);

    public List<SysUserVo> queryByRoleId(Long roleId);

    public List<SysRoleVo> queryByUserId(Long userId);
}
