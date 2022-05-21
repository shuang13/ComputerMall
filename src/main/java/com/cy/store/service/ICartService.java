package com.cy.store.service;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    public void addCart(Integer uid, Integer pid, Integer amount, String username);

    public List<CartVO> getCartListByUid(Integer uid);
    public Integer addCartNum(Integer cid, Integer amount, String username);
}
