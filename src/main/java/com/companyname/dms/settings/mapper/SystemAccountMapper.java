package com.companyname.dms.settings.mapper;

import com.companyname.dms.settings.domain.SystemAccount;
import org.apache.ibatis.annotations.Param;

public interface SystemAccountMapper {

    SystemAccount login(@Param("username") String username, @Param("password")String password);
}
