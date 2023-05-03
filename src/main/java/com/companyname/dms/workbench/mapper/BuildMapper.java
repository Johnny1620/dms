package com.companyname.dms.workbench.mapper;

import com.companyname.dms.workbench.domain.Build;

import java.util.List;
import java.util.Map;

public interface BuildMapper {
    int save(Build b);

    int getTotalByCondition(Map<String, Object> map);

    List<Map<String, Object>> getBuildListByCondition(Map<String, Object> map);

    int selectByIds(String[] ids);

    int deleteByIds(String[] ids);

    Build getBuildById(String id);

    Map<String, Object> getBuildAndDormNameById(String id);

    int update(Build build);
}
