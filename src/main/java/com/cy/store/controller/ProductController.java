package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import com.cy.store.service.IProductService;
import com.cy.store.service.IUserService;
import com.cy.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @GetMapping("{id}/details")
    public JsonResult<Product> finById(@PathVariable("id") Integer id){
        System.out.println("id" + id);
        Product data = productService.findById(id);
        System.out.println(data);
        return new JsonResult<>(OK, data);
    }
    @RequestMapping("hot_list")
    public JsonResult<List<Product>> finHotList(){
        List<Product> data = productService.findHotList();
        return new JsonResult<>(OK, data);
    }

}
