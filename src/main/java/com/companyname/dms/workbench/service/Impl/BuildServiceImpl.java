package com.companyname.dms.workbench.service.Impl;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Build;
import com.companyname.dms.workbench.mapper.BuildMapper;
import com.companyname.dms.workbench.service.BuildService;

import java.util.List;
import java.util.Map;

public class BuildServiceImpl implements BuildService {

    private BuildMapper buildMapper = SqlSessionUtil.getSqlSession().getMapper(BuildMapper.class);

    @Override
    public boolean save(Build b) {

        int count = buildMapper.save(b);

        if (count == 1) {
            return true;
        }
        return false;

    }

    @Override
    public PaginationVO<Map<String,Object>> pageList(Map<String, Object> map) {

        int total = buildMapper.getTotalByCondition(map);
        List<Map<String,Object>> mlist = buildMapper.getBuildListByCondition(map);

        //创建一个vo对象
        PaginationVO<Map<String,Object>> vo = new PaginationVO<Map<String,Object>>();
        vo.setTotal(total);
        vo.setDataList(mlist);

        //将vo返回
        return vo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {

        int expect = buildMapper.selectByIds(ids);
        int actual = buildMapper.deleteByIds(ids);

        if (expect==actual) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> getBuildAndDormNameById(String id) {

        return buildMapper.getBuildAndDormNameById(id);
    }

    @Override
    public boolean update(Build build) {

        int count = buildMapper.update(build);
        if (count==1) {
            return true;
        }
        return false;
    }


}
