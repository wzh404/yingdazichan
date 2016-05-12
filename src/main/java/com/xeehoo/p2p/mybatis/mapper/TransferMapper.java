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
     * 债权转让申请
     *
     * @param transfer
     * @return
     */
    public Integer saveTransfer(LoanTransfer transfer);

    /**
     * 债权转让完成
     *
     * @param transferId
     * @param userId
     * @param amount
     * @param fee
     * @return
     */
    public Integer updateTransfer(@Param("transferId") Integer transferId,
                                  @Param("userId") Integer userId,
                                  @Param("amount") BigDecimal amount,
                                  @Param("fee") BigDecimal fee,
                                  @Param("seqno") String seqno);

    /**
     * 根据ID查询债权转让
     *
     * @param transferId
     * @return
     */
    public LoanTransfer getTransfer(Integer transferId);

    /**
     * 债权转让列表
     *
     * @return
     */
    public List<LoanTransfer> getTransfers();

    /**
     * 取消债权转让申请
     *
     * @param investId
     * @return
     */
    public Integer cancelTransferRequest(@Param("investId") Integer investId);
}
