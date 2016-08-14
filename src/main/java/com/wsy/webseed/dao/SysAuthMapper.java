package com.wsy.webseed.dao;


import com.wsy.webseed.dao.base.ISqlMapper;
import com.wsy.webseed.domain.SysAuthVo;

import java.util.List;
import java.util.Map;

public interface SysAuthMapper extends ISqlMapper {
    public List<SysAuthVo> query(Map<String, Object> param);

    public Integer save(SysAuthVo authVo);
}
