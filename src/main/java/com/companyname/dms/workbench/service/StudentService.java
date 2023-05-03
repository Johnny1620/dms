package com.companyname.dms.workbench.service;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PaginationVO<Student> pageList(Map<String, Object> map);

    boolean save(Student stu);

    boolean delete(String[] ids);

    Student getStuInfoById(String id);

    boolean updateById(Student stu);


    List<Map<String, Object>> getNotIntoStu();

    PaginationVO<Map<String, Object>> pageListByInto(Map<String, Object> map);

    List<Map<String, Object>> getIntoStu();

    PaginationVO<Map<String, Object>> pageListByOut(Map<String, Object> map);
}
