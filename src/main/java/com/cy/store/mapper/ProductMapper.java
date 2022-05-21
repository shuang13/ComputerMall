package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    /**
     * 插入产品
     * @param product 产品信息
     * @return 查询行数
     */
    Integer insert(Product product);

    /**
     * 通过id查找产品
     * @param id 产品id
     * @return 产品信息
     */
    Product findById(Integer id);

    /**
     * 获取热销排行前四产品
     * @return 热销排行前四产品信息
     */
    List<Product> findHotList();
}
