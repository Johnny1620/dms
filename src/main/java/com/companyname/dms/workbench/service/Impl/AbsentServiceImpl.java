package com.companyname.dms.workbench.service.Impl;

import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Absent;
import com.companyname.dms.workbench.mapper.AbsentMapper;
import com.companyname.dms.workbench.mapper.BuildMapper;
import com.companyname.dms.workbench.service.AbsentService;

import java.util.List;
import java.util.Map;

public class AbsentServiceImpl implements AbsentService {

    private AbsentMapper absentMapper = SqlSessionUtil.getSqlSession().getMapper(AbsentMapper.class);


    @Override
    public PaginationVO<Absent> pageList(int skipCount, int pageSize) {

        int total = absentMapper.getTotal();
        List<Absent> mlist = absentMapper.getAbsentList(skipCount,pageSize);

        //创建一个vo对象
        PaginationVO<Absent> vo = new PaginationVO<Absent>();
        vo.setTotal(total);
        vo.setDataList(mlist);

        //将vo返回
        return vo;
    }

    @Override
    public boolean save(Absent a) {

        int count = absentMapper.save(a);

        if (count==1) {
            return true;

        }
        return false;

    }

    @Override
    public boolean delete(String[] names) {

        int expect = absentMapper.selectByNames(names);
        int actual = absentMapper.deleteByNames(names);

        if (expect==actual) {

            return true;

        }
        return false;

    }


}
