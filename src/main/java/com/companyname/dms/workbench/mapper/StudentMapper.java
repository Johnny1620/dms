package com.companyname.dms.workbench.mapper;

import com.companyname.dms.workbench.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int getTotalByCondition(Map<String, Object> map);

    List<Student> getDormAccountListByCondition(Map<String, Object> map);

    int save(Student stu);

    int selectByIds(String[] ids);

    int deleteById(String[] ids);

    Student getStuInfoById(String id);

    int updateById(Student stu);

    List<Map<String, Object>> getNotIntoStu();

    int getTotalByInto(Map<String, Object> map);


    List<Map<String, Object>> getStuListByInto(Map<String, Object> map);

    List<Map<String, Object>> getIntoStu();

    int getTotalByOut(Map<String, Object> map);

    List<Map<String, Object>> getStuListByOut(Map<String, Object> map);
}
