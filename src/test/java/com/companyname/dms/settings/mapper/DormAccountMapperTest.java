package com.companyname.dms.settings.mapper;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DormAccountMapperTest {

    private DormAccountMapper dormAccountMapper = SqlSessionUtil.getSqlSession().getMapper(DormAccountMapper.class);

    @Test
    public void testSave() {

        DormAccount a = new DormAccount();
        a.setId(999L);
        a.setUsername("aeae");
        a.setPassword("120");
        a.setName("zzzz");
        a.setGender("g");
        a.setPhone("120120");
        a.setPosition("B-1");


        int count = dormAccountMapper.save(a);
        SqlSessionUtil.getSqlSession().commit();
        if (count==1) {
            System.out.println("==成功="+count);

            SqlSessionUtil.myClose(SqlSessionUtil.getSqlSession());
            return;
        }
        System.out.println("==失败");
        SqlSessionUtil.myClose(SqlSessionUtil.getSqlSession());
    }
/*    @Test
    public void testPageList() {



        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "");
        map.put("gender", "");
        map.put("position","");
        map.put("skipCount",0);
        map.put("pageSize",2);

        int total = dormAccountMapper.getTotalByCondition(map);
        List<DormAccount> alist = dormAccountMapper.getDormAccountListByCondition(map);

        //创建一个vo对象
        PaginationVO<DormAccount> vo = new PaginationVO<DormAccount>();
        vo.setTotal(total);
        vo.setDataList(alist);
        System.out.println(vo.toString());


    }*/
}
