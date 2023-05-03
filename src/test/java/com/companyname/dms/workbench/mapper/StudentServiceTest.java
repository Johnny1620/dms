package com.companyname.dms.workbench.mapper;

import com.companyname.dms.utils.ServiceFactory;
import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Student;
import com.companyname.dms.workbench.service.Impl.StudentServiceImpl;
import com.companyname.dms.workbench.service.StudentService;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceTest {

    private StudentMapper studentMapper = (StudentMapper) SqlSessionUtil.getSqlSession().getMapper(StudentMapper.class);

    @Test
    public void testGetStuListByInto() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("stuName", "");

        map.put("skipCount",1);
        map.put("pageSize",2);

        int total = studentMapper.getTotalByInto(map);
        List<Map<String,Object>> stulist = studentMapper.getStuListByInto(map);

        //创建一个vo对象
        PaginationVO<Map<String,Object>> vo = new PaginationVO<Map<String,Object>>();
        vo.setTotal(total);
        vo.setDataList(stulist);

        System.out.println(vo);
    }
    @Test
    public void testGetNotIntoStu() {

        List<Map<String, Object>> l = studentMapper.getNotIntoStu();
        System.out.println(l);

    }

    @Test
    public void testPageList() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "");
        map.put("gender", "");
        map.put("position","");

        map.put("skipCount",1);
        map.put("pageSize",2);


        int total = studentMapper.getTotalByCondition(map);
        List<Student> slist = studentMapper.getDormAccountListByCondition(map);

        //创建一个vo对象
        PaginationVO<Student> vo = new PaginationVO<Student>();
        vo.setTotal(total);
        vo.setDataList(slist);

        System.out.println(vo);
    }
    @Test
    public void testSave() {

        Student stu = new Student();
        stu.setName("test");
        stu.setGender("wu");
        stu.setAge(23);
        stu.setGrade("10");
        stu.setMajor("10");
        stu.setCreateDate("2010-2-1");
        stu.setState("1");
        stu.setDormId("b-1");

        int count = studentMapper.save(stu);
        SqlSessionUtil.getSqlSession().commit();

        if (count==1) {
            System.out.println("成功");

        } else {
            System.out.println("失败");
        }


    }
    @Test
    public void testUpdateById() {


        Student stu = studentMapper.getStuInfoById("1");

        stu.setName("xiaom12");

        System.out.println(stu);

        int count = studentMapper.updateById(stu);

        SqlSessionUtil.getSqlSession().commit();

        if (count==1) {

            System.out.println("成功");
            return;

        }
        System.out.println("失败");


    }

}
