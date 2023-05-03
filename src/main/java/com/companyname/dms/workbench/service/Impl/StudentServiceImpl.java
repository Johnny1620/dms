package com.companyname.dms.workbench.service.Impl;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Student;
import com.companyname.dms.workbench.mapper.StudentMapper;
import com.companyname.dms.workbench.service.StudentService;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper = (StudentMapper) SqlSessionUtil.getSqlSession().getMapper(StudentMapper.class);


    @Override
    public PaginationVO<Student> pageList(Map<String, Object> map) {


        int total = studentMapper.getTotalByCondition(map);
        List<Student> slist = studentMapper.getDormAccountListByCondition(map);

        //创建一个vo对象
        PaginationVO<Student> vo = new PaginationVO<Student>();
        vo.setTotal(total);
        vo.setDataList(slist);

        //将vo返回
        return vo;

    }

    @Override
    public boolean save(Student stu) {
        System.out.println("业务层“：save");

        int count = studentMapper.save(stu);

        if (count==1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(String[] ids) {

        int expect = studentMapper.selectByIds(ids);

        int actual = studentMapper.deleteById(ids);

        if (expect == actual) {
            return true;
        }

        return false;
    }

    @Override
    public Student getStuInfoById(String id) {

        return studentMapper.getStuInfoById(id);

    }

    @Override
    public boolean updateById(Student stu) {

        int count = studentMapper.updateById(stu);

        if (count==1) {
            return true;
        }
        return false;

    }

    /**
     *
     * return 返回没有入住的学生的姓名和id
     * */
    @Override
    public List<Map<String, Object>> getNotIntoStu() {

        return studentMapper.getNotIntoStu();
    }

    @Override
    public PaginationVO<Map<String, Object>> pageListByInto(Map<String, Object> map) {

        int total = studentMapper.getTotalByInto(map);
        List<Map<String,Object>> stulist = studentMapper.getStuListByInto(map);

        //创建一个vo对象
        PaginationVO<Map<String,Object>> vo = new PaginationVO<Map<String,Object>>();
        vo.setTotal(total);
        vo.setDataList(stulist);

        //将vo返回
        return vo;


    }

    @Override
    public List<Map<String, Object>> getIntoStu() {

        return studentMapper.getIntoStu();
    }

    @Override
    public PaginationVO<Map<String, Object>> pageListByOut(Map<String, Object> map) {

        int total = studentMapper.getTotalByOut(map);
        List<Map<String,Object>> stulist = studentMapper.getStuListByOut(map);

        //创建一个vo对象
        PaginationVO<Map<String,Object>> vo = new PaginationVO<Map<String,Object>>();
        vo.setTotal(total);
        vo.setDataList(stulist);

        //将vo返回
        return vo;

    }


}
