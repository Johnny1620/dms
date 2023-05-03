package com.companyname.dms.settings.service;

import com.companyname.dms.execption.LoginExecption;
import com.companyname.dms.settings.domain.SystemAccount;

public interface SystemAccountService {
    SystemAccount login(String loginAct, String loginPwd) throws LoginExecption;
}
