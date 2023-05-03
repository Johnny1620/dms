package com.companyname.dms.workbench.mapper;

import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Build;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildMapperTest {

    private BuildMapper buildMapper = SqlSessionUtil.getSqlSession().getMapper(BuildMapper.class);
    @Test
    public void testPageList() {

        Map<String,Object> map = new HashMap<>();
        map.put("name", "");
        map.put("info", "");
        map.put("date","");

        map.put("skipCount",1);
        map.put("pageSize",2);

        int total = buildMapper.getTotalByCondition(map);
        List<Map<String,Object>> mlist = buildMapper.getBuildListByCondition(map);

        //创建一个vo对象
        PaginationVO<Map<String,Object>> vo = new PaginationVO<Map<String,Object>>();
        vo.setTotal(total);
        vo.setDataList(mlist);

        //将vo返回
        System.out.println(vo);

    }
    @Test
    public void testSave() {

        Build b = new Build();
        b.setName("a");
        b.setInfo("a");
        b.setAccount_id(3L);
        b.setDate("2023-4-1");
        int count = buildMapper.save(b);
        SqlSessionUtil.getSqlSession().commit();

        if (count == 1) {
            System.out.println("成功===");
            return;
        }
        System.out.println("失败===");
    }
    @Test
    public void testGetBuildAndDormNameById() {
        Map<String,Object> map = buildMapper.getBuildAndDormNameById("2");
        System.out.println(map);

    }
    @Test
    public void testUpdate() {

        Build build = new Build();
        build.setId(Long.valueOf("1"));
        build.setName("1");
        build.setInfo("1");
        build.setDate("");

        build.setAccount_id(Long.valueOf("3"));

        int count = buildMapper.update(build);
        SqlSessionUtil.getSqlSession().commit();

        if (count==1) {
            System.out.println("===成功");
            return;
        }
        System.out.println("===失败");


    }
}
