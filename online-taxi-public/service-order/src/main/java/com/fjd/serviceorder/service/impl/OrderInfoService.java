package com.fjd.serviceorder.service.impl;

import com.fjd.internalcommon.dto.OrderInfo;
import com.fjd.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-23
 */
@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public String testMapper(){

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAddress("110000");

        orderInfoMapper.insert(orderInfo);
        return "";
    }

}
