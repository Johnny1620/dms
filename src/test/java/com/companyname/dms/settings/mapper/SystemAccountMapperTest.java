package com.companyname.dms.settings.mapper;

import com.companyname.dms.execption.LoginExecption;
import com.companyname.dms.settings.domain.SystemAccount;
import com.companyname.dms.utils.SqlSessionUtil;
import org.junit.Test;

public class SystemAccountMapperTest {

    @Test
    public void testLogin() {

        SystemAccountMapper systemAccountMapper = SqlSessionUtil.getSqlSession().getMapper(SystemAccountMapper.class);

        SystemAccount account = systemAccountMapper.login("s12", "12");

        if (account==null) {
            System.out.println("===账号密码错误===");
            return;
        }

        System.out.println("===账号密码正确===");

    }
}
