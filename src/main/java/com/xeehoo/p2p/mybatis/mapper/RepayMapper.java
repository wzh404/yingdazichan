package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserRepay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public interface RepayMapper {
    /**
     * 生成还款计划
     *
     * @param userRepays
     * @return
     */
    public Integer saveUserRepays(@Param("userRepays")List<LoanUserRepay> userRepays);


    /**
     * 获取当天的还款计划。
     *
     * @return
     */
    public List<LoanUserRepay> getUserRepayNow();

    /**
     * 修改还款结果状态。
     *
     * @param repayId
     * @param respCode
     * @param seqno
     * @return
     */
    public Integer updateRepayResponse(@Param("repayId")Integer repayId, @Param("respCode")String respCode, @Param("seqno")String seqno);

    /**
     * 查询产品还款计划
     *
     * @param cond
     * @return
     */
    public List<LoanUserRepay> getProductRepay(Map<String, Object> cond);

    /**
     * 查询产品还款计划数
     *
     * @param cond
     * @return
     */
    public Integer getTotalProductRepay(Map<String, Object> cond);
}
