package com.management.product.service;

import com.management.product.entity.Product;

public interface ProductService extends DataService<Product> {

    Product getByTitle(String title);

    void removeByTitle(String title);
}
