package com.companyname.dms.workbench.service.Impl;

import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.workbench.domain.MoveInto;
import com.companyname.dms.workbench.mapper.DormMapper;
import com.companyname.dms.workbench.mapper.MoveIntoMapper;
import com.companyname.dms.workbench.service.MoveIntoService;

public class MoveIntoServiceImpl implements MoveIntoService {

    private MoveIntoMapper moveIntoMapper = SqlSessionUtil.getSqlSession().getMapper(MoveIntoMapper.class);


    @Override
    public boolean saveStuAndDate(MoveInto into) {

        int count = moveIntoMapper.saveStuAndDate(into);

        if (count==1) {
            return true;
        }

        return false;

    }

    @Override
    public boolean deleteByStuId(String id) {

        int count = moveIntoMapper.deleteByStuId(id);

        if (count==1) {
            return true;

        }
        return false;
    }
}
