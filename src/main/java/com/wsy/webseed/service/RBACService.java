package com.wsy.webseed.service;

import com.wsy.webseed.domain.SysAuthVo;
import com.wsy.webseed.domain.SysRoleVo;
import com.wsy.webseed.domain.SysUserVo;

import java.util.List;

/**
 * Created by wangsiyuan1 on 2016/8/13.
 */
public interface RBACService {
    public List<SysAuthVo> queryByLoginName(String loginName);

    public SysUserVo querySysUserByLoginName(String loginName);

    public List<SysRoleVo> queryRolesByUserId(Long userId);
}
