package com.companyname.dms.workbench.mapper;

import com.companyname.dms.workbench.domain.MoveInto;

public interface MoveIntoMapper {
    int saveStuAndDate(MoveInto into);

    int deleteByStuId(String id);
}
