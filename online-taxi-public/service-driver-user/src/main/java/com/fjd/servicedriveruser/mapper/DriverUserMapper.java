package com.fjd.servicedriveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjd.internalcommon.dto.DriverUser;
import org.springframework.stereotype.Repository;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 19:00
 * @desc:
 */
@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {

    public int select1(String org);
}
