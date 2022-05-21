package com.cy.store.controller;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.utils.JsonResult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;
    @RequestMapping("carts/add_to_cart")
    public JsonResult<Void> addCart(Integer pid, Integer amount, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addCart(uid, pid, amount, username);
        return new JsonResult<>(OK);
    }
    @RequestMapping("carts")
    public JsonResult<List<CartVO>> getCartList(HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        List<CartVO> data = cartService.getCartListByUid(uid);
        return new JsonResult<>(OK, data);

    }
    @PostMapping("carts/{cid}/num/add")
    public JsonResult<Integer> addCartNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Integer data = cartService.addCartNum(cid, 1, username);
        return new JsonResult<>(OK, data);

    }




}
