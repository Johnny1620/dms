package com.companyname.dms.settings.service.Impl;

import com.companyname.dms.execption.LoginExecption;
import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.settings.domain.SystemAccount;
import com.companyname.dms.settings.mapper.DormAccountMapper;
import com.companyname.dms.settings.mapper.SystemAccountMapper;
import com.companyname.dms.settings.service.DormAccountService;
import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;

import java.util.List;
import java.util.Map;

public class DormAccountServiceImpl implements DormAccountService {

    private DormAccountMapper dormAccountMapper = SqlSessionUtil.getSqlSession().getMapper(DormAccountMapper.class);
    @Override
    public PaginationVO<DormAccount> pageList(Map<String,Object> map) {

        int total = dormAccountMapper.getTotalByCondition(map);
        List<DormAccount> alist = dormAccountMapper.getDormAccountListByCondition(map);

        //创建一个vo对象
        PaginationVO<DormAccount> vo = new PaginationVO<DormAccount>();
        vo.setTotal(total);
        vo.setDataList(alist);

        //将vo返回
        return vo;

    }

    @Override
    public boolean save(DormAccount account) {

        System.out.println("业务层“：save");

        int count = dormAccountMapper.save(account);

        if (count==1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(String[] ids) {

        int expect = dormAccountMapper.selectByIds(ids);

        int actual = dormAccountMapper.deleteById(ids);

        if (expect == actual) {
            return true;
        }

        return false;
    }

    @Override
    public DormAccount getDormInfoById(String id) {

        return dormAccountMapper.getDormInfoById(id);

    }

    @Override
    public boolean updateById(DormAccount account) {

        int count = dormAccountMapper.updateById(account);

        if (count==1) {
            return true;
        }
        return false;

    }

    @Override
    public List<Map<String, Object>> getDormNameIdALL() {

        return dormAccountMapper.getDormNameIdALL();
    }

    @Override
    public DormAccount login(String loginAct, String loginPwd) throws LoginExecption {

        DormAccount account = dormAccountMapper.login(loginAct, loginPwd);

        if (account==null) {
            throw new LoginExecption("账号或密码错误");
        }

        return account;
    }


}
