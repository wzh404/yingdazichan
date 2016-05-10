package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanPay;
import com.xeehoo.p2p.po.LoanTransfer;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangzunhui on 2016/1/7.
 */
public interface TransferMapper {
    /**
     *
     * @param transfer
     * @return
     */
    public Integer saveTransfer(LoanTransfer transfer);

    /**
     *
     * @param transferId
     * @param userId
     * @param amount
     * @param fee
     * @return
     */
    public Integer updateTransfer(@Param("transferId")Integer transferId,
                                  @Param("userId")Integer userId,
                                  @Param("amount")BigDecimal amount,
                                  @Param("fee")BigDecimal fee,
                                  @Param("seqno")String seqno);

    /**
     *
     * @param transferId
     * @return
     */
    public LoanTransfer getTransfer(Integer transferId);


    public List<LoanTransfer> getTransfers();
}
