package com.cy.store.service;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@SpringBootTest: 表示当前类为测试类，不会随项目打包
@SpringBootTest
//@RunWith: 表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void addCart() {
        try{
            cartService.addCart(1, 1, 1, "z");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }




}
