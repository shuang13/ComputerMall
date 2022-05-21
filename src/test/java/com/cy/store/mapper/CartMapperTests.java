package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

//@SpringBootTest: 表示当前类为测试类，不会随项目打包
@SpringBootTest
//@RunWith: 表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;
    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setCid(2);

        cart.setUid(1);
        cart.setPid(1);
        Integer rows = cartMapper.insert(cart);
        System.out.println(rows);
    }
    @Test
    public void updateNumByCid(){
        Integer row = cartMapper.updateNumByCid(2,1,"z",new Date());
        System.out.println(row);

    }
    @Test
    public void findByPidAndUid(){
        Cart res = cartMapper.findByPidAndUid(1,1);
        System.out.println(res);
    }
    @Test
    public void findCartListByUid(){
        List<Cart> res = cartMapper.findCartListByUid(1);
        System.out.println(res);
    }
    @Test
    public void findVOByUid() {
        List<CartVO> res = cartMapper.findVOByUid(24);
        System.out.println(res);
    }




}
