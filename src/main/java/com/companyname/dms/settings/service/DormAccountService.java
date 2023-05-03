package com.companyname.dms.settings.service;

import com.companyname.dms.execption.LoginExecption;
import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.vo.PaginationVO;

import java.util.List;
import java.util.Map;

public interface DormAccountService {
    PaginationVO<DormAccount> pageList(Map<String,Object> map);
    boolean save(DormAccount account);

    boolean delete(String[] ids);

    DormAccount getDormInfoById(String id);

    boolean updateById(DormAccount account);


    List<Map<String, Object>> getDormNameIdALL();

    DormAccount login(String loginAct, String loginPwd) throws LoginExecption;
}
