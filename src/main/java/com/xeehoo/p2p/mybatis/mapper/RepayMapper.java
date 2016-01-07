package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanProductRepay;
import com.xeehoo.p2p.po.LoanUserRepay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public interface RepayMapper {
    /**
     *
     * @param userRepays
     * @return
     */
    public Integer saveUserRepays(@Param("userRepays")List<LoanUserRepay> userRepays);

    /**
     *
     * @param productRepay
     * @return
     */
    public Integer saveProductRepay(LoanProductRepay productRepay);

    /**
     *
     * @param investId
     * @return
     */
    public List<LoanUserRepay> getUserRepays(Integer investId);

    /**
     *
     * @param productId
     * @return
     */
    public List<LoanProductRepay> getProductRepay(Integer productId);

}
