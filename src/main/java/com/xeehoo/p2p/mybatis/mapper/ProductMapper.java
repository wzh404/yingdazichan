package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanProduct;

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
}
