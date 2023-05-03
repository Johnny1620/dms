package com.companyname.dms.workbench.service.Impl;

import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Dorm;
import com.companyname.dms.workbench.mapper.DormMapper;
import com.companyname.dms.workbench.service.DormService;

import java.util.List;
import java.util.Map;

public class DormServiceImpl implements DormService {

    private DormMapper dormMapper = SqlSessionUtil.getSqlSession().getMapper(DormMapper.class);


    @Override
    public PaginationVO<Dorm> pageList(Map<String, Object> map) {

        int total = dormMapper.getTotalByCondition(map);
        List<Dorm> dlist = dormMapper.getDormListByCondition(map);

        //创建一个vo对象
        PaginationVO<Dorm> vo = new PaginationVO<Dorm>();
        vo.setTotal(total);
        vo.setDataList(dlist);

        //将vo返回
        return vo;
    }

    @Override
    public boolean save(Dorm d) {

        int count = dormMapper.save(d);

        if (count==1) {
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteByIds(String[] ids) {

        int expect = dormMapper.selectByIds(ids);

        int actual = dormMapper.deleteByIds(ids);

        if (expect == actual) {
            return true;
        }
        return false;

    }

    @Override
    public Dorm getDormInfoById(String dormId) {

        return dormMapper.getDormInfoById(dormId);
    }

    @Override
    public boolean updateById(Dorm dorm) {

        int count = dormMapper.updateById(dorm);

        if (count==1) {
            return true;
        }
        return false;

    }
}
