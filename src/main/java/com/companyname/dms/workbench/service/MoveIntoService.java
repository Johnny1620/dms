package com.companyname.dms.workbench.service;

import com.companyname.dms.workbench.domain.MoveInto;

public interface MoveIntoService {
    boolean saveStuAndDate(MoveInto into);

    boolean deleteByStuId(String id);
}
