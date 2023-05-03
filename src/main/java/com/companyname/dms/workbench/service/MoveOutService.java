package com.companyname.dms.workbench.service;

import com.companyname.dms.workbench.domain.MoveOut;

public interface MoveOutService {
    boolean saveStuAndDate(MoveOut out);

    boolean deleteByStuId(String id);
}
