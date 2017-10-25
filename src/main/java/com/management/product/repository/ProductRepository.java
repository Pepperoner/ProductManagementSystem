package com.management.product.repository;

import com.management.product.entity.Product;

public interface ProductRepository extends DataRepository<Product> {

    Product findByTitle(String title);

    void deleteByTitle(String title);
}
