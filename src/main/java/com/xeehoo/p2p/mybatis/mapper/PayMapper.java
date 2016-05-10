package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanPay;
import com.xeehoo.p2p.po.LoanUserRepay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public interface PayMapper {

    /**
     *
     * @param pay
     * @return
     */
    public Integer savePay(LoanPay pay);

    /**
     *
     * @param payId
     * @param respcode
     * @return
     */
    public Integer updateRespcode(@Param("payId")Integer payId, @Param("respcode")String respcode);
}
