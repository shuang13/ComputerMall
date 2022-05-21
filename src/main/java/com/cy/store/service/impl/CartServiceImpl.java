package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.ProductNotFoundException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart res = cartMapper.findByPidAndUid(pid, uid);
        Date date = new Date();
        if(res == null) {
            Cart newCart = new Cart();
            newCart.setPid(pid);
            newCart.setUid(uid);

            newCart.setNum(amount);
            Product product = productMapper.findById(pid);
            System.out.println("add" + product);

            if(product == null) {
                throw new ProductNotFoundException("未找到该商品");
            }
            newCart.setPrice(product.getPrice());
            newCart.setCreatedUser(username);
            newCart.setModifiedUser(username);
            newCart.setCreatedTime(date);
            newCart.setModifiedTime(date);
            Integer row = cartMapper.insert(newCart);
            System.out.println("add" + row);

            if(row != 1) {
                throw new InsertException("插入购物车异常");
            }
        } else {
            Integer num = res.getNum() + amount;
            Integer row = cartMapper.updateNumByCid(res.getCid(), num, username, date);
            if(row != 1) {
                throw new UpdateException("更新购物车数量异常");
            }
        }


    }

    @Override
    public List<CartVO> getCartListByUid(Integer uid) {
        List<CartVO> res = cartMapper.findVOByUid(uid);
        return res;
    }

    @Override
    public Integer addCartNum(Integer cid, Integer amount, String username) {
        Cart res = cartMapper.findByCid(cid);
        Integer num = res.getNum() + amount;
        Integer row = cartMapper.updateNumByCid(cid, num, username, new Date());
        if(row != 1) {
            throw new UpdateException("更新购物车数量异常");
        }
        return num;
    }
}
