package com.companyname.dms.workbench.service;

import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Build;

import java.util.Map;

public interface BuildService {
    boolean save(Build b);

    PaginationVO<Map<String,Object>> pageList(Map<String, Object> map);

    boolean deleteByIds(String[] ids);


    Map<String, Object> getBuildAndDormNameById(String id);

    boolean update(Build build);
}
