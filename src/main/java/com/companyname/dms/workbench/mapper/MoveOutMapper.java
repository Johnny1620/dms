package com.companyname.dms.workbench.mapper;

import com.companyname.dms.workbench.domain.MoveOut;

public interface MoveOutMapper {
    int saveStuAndDate(MoveOut out);

    int deleteByStuId(String id);
}
