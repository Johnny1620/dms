package com.companyname.dms.workbench.mapper;

import com.companyname.dms.workbench.domain.Dorm;

import java.util.List;
import java.util.Map;

public interface DormMapper {
    int getTotalByCondition(Map<String, Object> map);

    List<Dorm> getDormListByCondition(Map<String, Object> map);

    int save(Dorm d);

    int selectByIds(String[] ids);

    int deleteByIds(String[] ids);

    Dorm getDormInfoById(String dormId);

    int updateById(Dorm dorm);
}
