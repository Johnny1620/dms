package com.companyname.dms.workbench.service;

import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Absent;

import java.util.Map;

public interface AbsentService {


    PaginationVO<Absent> pageList(int skipCount, int pageSize);

    boolean save(Absent a);


    boolean delete(String[] names);
}
