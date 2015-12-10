package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanUserInvestment;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by WIN10 on 2015/10/24.
 */
public interface ProductMapper {
    /**
     * 分页查询产品信息
     *
     * @param map 查询条件及分页信息(offset, pagesize)
     * @return
     */
    public List<LoanProduct> getProductPager(Map<String, Object> map);

    /**
     * 查询产品记录数
     *
     * @param map
     * @return
     */
    public Integer getTotalProduct(Map<String, Object> map);

    /**
     *
     * @param productId
     * @return
     */
    public LoanProduct getProduct(Integer productId);


    /**
     *
     * @param productId
     * @param amount
     * @return
     */
    public Integer updateProductAmount(@Param("productId")Integer productId, @Param("amount")BigDecimal amount);

    /**
     *
     * @param investment
     * @return
     */
    public Integer saveUserInvestment(LoanUserInvestment investment);

    /**
     *
     * @return

    public List<LoanProduct> getProductByType(Map<String, Object> map);*/
}
