package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product item : list) {
            item.setPriority(null);
            item.setCreatedUser(null);
            item.setCreatedTime(null);
            item.setModifiedTime(null);
            item.setModifiedUser(null);
        }
        return list;

    }

    @Override
    public Product findById(Integer id) {
        Product res = productMapper.findById(id);
        if(res == null) {
            throw new ProductNotFoundException("未找到商品信息");
        }
        res.setPriority(null);
        res.setCreatedUser(null);
        res.setCreatedTime(null);
        res.setModifiedTime(null);
        res.setModifiedUser(null);
        return res;

    }
}
