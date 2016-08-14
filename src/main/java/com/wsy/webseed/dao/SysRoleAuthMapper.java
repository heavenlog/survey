package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SysAuthVo;
import com.wsy.webseed.domain.SysRoleAuthVo;
import com.wsy.webseed.domain.SysRoleVo;

import java.util.List;

public interface SysRoleAuthMapper extends ISqlMapper {

    public int save(SysRoleAuthVo vo);

    public void delById(Long id);

    public List<SysAuthVo> queryByRoleId(Long roleId);

    public List<SysRoleVo> queryByAnthId(Long authId);
}
