package com.companyname.dms.workbench.mapper;

import com.companyname.dms.workbench.domain.Absent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AbsentMapper {


    int getTotal();

    List<Absent> getAbsentList(@Param("skipCount")int skipCount, @Param("pageSize") int pageSize);

    int save(Absent a);

    int selectByNames(String[] names);

    int deleteByNames(String[] names);
}
