package com.cy.store.mapper;

import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//@SpringBootTest: 表示当前类为测试类，不会随项目打包
@SpringBootTest
//@RunWith: 表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void insert() {
        Product product = new Product();
        product.setId(100000426);
        product.setTitle("啦啦啦");
        product.setCategoryId(163);
        product.setPrice(1L);
        product.setNum(11);

        Integer rows = productMapper.insert(product);
        System.out.println(rows);
    }
    @Test
    public void findById() {
        Product res = productMapper.findById(100000425);
        System.out.println(res);
    }
    @Test
    public void findHotList() {
        List<Product> res = productMapper.findHotList();
        System.out.println(res);
    }


}
