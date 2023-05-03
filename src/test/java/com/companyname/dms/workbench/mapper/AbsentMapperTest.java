package com.companyname.dms.workbench.mapper;

import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Absent;
import org.junit.Test;

import java.util.List;

public class AbsentMapperTest {
    private AbsentMapper absentMapper = SqlSessionUtil.getSqlSession().getMapper(AbsentMapper.class);

    @Test
    public void testPageList() {


        int total = absentMapper.getTotal();
        List<Absent> mlist = absentMapper.getAbsentList(0, 2);

        //创建一个vo对象
        PaginationVO<Absent> vo = new PaginationVO<Absent>();

        vo.setTotal(total);
        vo.setDataList(mlist);

        System.out.println(vo);
    }
}
