package com.companyname.dms.settings.service.Impl;

import com.companyname.dms.execption.LoginExecption;
import com.companyname.dms.settings.domain.SystemAccount;
import com.companyname.dms.settings.mapper.SystemAccountMapper;
import com.companyname.dms.settings.service.SystemAccountService;
import com.companyname.dms.utils.SqlSessionUtil;

public class SystemAccountServiceImpl implements SystemAccountService {

    private SystemAccountMapper systemAccountMapper = SqlSessionUtil.getSqlSession().getMapper(SystemAccountMapper.class);

    @Override
    public SystemAccount login(String loginAct, String loginPwd) throws LoginExecption {

        SystemAccount account = systemAccountMapper.login(loginAct, loginPwd);

        if (account==null) {
            throw new LoginExecption("账号或密码错误");
        }

        return account;
    }
}
