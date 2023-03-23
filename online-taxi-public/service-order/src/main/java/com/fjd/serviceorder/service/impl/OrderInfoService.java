package com.fjd.serviceorder.service.impl;

import com.fjd.internalcommon.constant.OrderConstants;
import com.fjd.internalcommon.dto.OrderInfo;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    /**
     * 下订单
     * @param orderRequest
     * @return
     */
    public ResponseResult add(OrderRequest orderRequest){

        OrderInfo orderInfo = new OrderInfo();
        //把原对象orderRequest copy 到目标对象orderInfo
        BeanUtils.copyProperties(orderRequest, orderInfo);

        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success();
    }

}
