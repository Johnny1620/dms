package com.companyname.dms.settings.mapper;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.settings.service.DormAccountService;
import com.companyname.dms.vo.PaginationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormAccountMapper {

    int getTotalByCondition(Map<String,Object> map);

    List<DormAccount> getDormAccountListByCondition(Map<String,Object> map);

    int save(DormAccount account);

    int selectByIds(String[] ids);

    int deleteById(String[] ids);

    DormAccount getDormInfoById(String id);

    int updateById(DormAccount account);

    Map<String, Object> selectAll();

    List<Map<String, Object>> getDormNameIdALL();

    DormAccount login(@Param("username") String username, @Param("password")String password);


}
