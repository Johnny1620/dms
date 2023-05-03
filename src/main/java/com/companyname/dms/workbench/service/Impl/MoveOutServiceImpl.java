package com.companyname.dms.workbench.service.Impl;

import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.workbench.domain.MoveOut;
import com.companyname.dms.workbench.mapper.MoveIntoMapper;
import com.companyname.dms.workbench.mapper.MoveOutMapper;
import com.companyname.dms.workbench.service.MoveOutService;

public class MoveOutServiceImpl implements MoveOutService {
    private MoveOutMapper moveOutMapper = SqlSessionUtil.getSqlSession().getMapper(MoveOutMapper.class);


    @Override
    public boolean saveStuAndDate(MoveOut out) {

        int count = moveOutMapper.saveStuAndDate(out);

        if (count==1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByStuId(String id) {

        int count = moveOutMapper.deleteByStuId(id);

        if (count==1) {
            return true;

        }
        return false;
    }
}
