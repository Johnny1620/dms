package com.companyname.dms.workbench.service;

import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Dorm;

import java.util.Map;

public interface DormService {
    PaginationVO<Dorm> pageList(Map<String, Object> map);

    boolean save(Dorm d);

    boolean deleteByIds(String[] ids);

    Dorm getDormInfoById(String dormId);

    boolean updateById(Dorm dorm);
}
