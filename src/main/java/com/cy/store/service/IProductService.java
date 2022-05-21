package com.cy.store.service;

import com.cy.store.entity.Product;
import com.cy.store.entity.User;

import java.util.List;

public interface IProductService {
    public List<Product> findHotList();

    public Product findById(Integer id);
}
