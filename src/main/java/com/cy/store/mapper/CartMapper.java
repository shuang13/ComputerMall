package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    Integer insert(Cart cart);

    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    Cart findByPidAndUid(Integer pid, Integer uid);

    List<Cart> findCartListByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByUid(Integer uid);
}
