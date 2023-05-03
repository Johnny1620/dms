package com.companyname.dms.workbench.mapper;

import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.workbench.domain.Dorm;
import org.junit.Test;

public class DormMapperTest {

    private DormMapper dormMapper = SqlSessionUtil.getSqlSession().getMapper(DormMapper.class);

    @Test
    public void testUpdate() {

/*        Dorm dorm = new Dorm();
        dorm.setDormId(request.getParameter("dormId"));
        dorm.setType(request.getParameter("type"));
        dorm.setResidue(Integer.valueOf(request.getParameter("residue")));
        dorm.setPhone(request.getParameter("phone"));
        dorm.setBuildName(request.getParameter("buildName"));


        int count = dormMapper.updateById(dorm);

        if (count==1) {
            System.out.println("===成功");
        }
        System.out.println("===成功");*/
    }
}
