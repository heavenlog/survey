package com.wsy.webseed.service.impl;

import com.wsy.webseed.dao.SysRoleAuthMapper;
import com.wsy.webseed.dao.SysUserMapper;
import com.wsy.webseed.dao.SysUserRoleMapper;
import com.wsy.webseed.domain.SysAuthVo;
import com.wsy.webseed.domain.SysRoleVo;
import com.wsy.webseed.domain.SysUserVo;
import com.wsy.webseed.domain.entity.SysRole;
import com.wsy.webseed.service.RBACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsiyuan1 on 2016/8/13.
 */
@Service
public class RBACServiceImpl implements RBACService{
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleAuthMapper sysRoleAuthMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysAuthVo> queryByLoginName(String loginName) {
        List<SysAuthVo> list = new ArrayList<SysAuthVo>();
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("loginName", loginName);
        List<SysUserVo> userVos = sysUserMapper.query(param);
        if(userVos.size() < 1) {
            return list;
        } else {
            List<SysRoleVo> roles = sysUserRoleMapper.queryByUserId(userVos.get(0).getId());
            for(SysRole role: roles) {
                List<SysAuthVo> auths = sysRoleAuthMapper.queryByRoleId(role.getId());
                list.addAll(auths);
            }
        }
        return list;
    }

    @Override
    public SysUserVo querySysUserByLoginName(String loginName) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loginName", loginName);
        List<SysUserVo> list = sysUserMapper.query(param);
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<SysRoleVo> queryRolesByUserId(Long userId) {
        return sysUserRoleMapper.queryByUserId(userId);
    }
}
