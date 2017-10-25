package com.management.product.service;

import com.management.product.entity.Product;
import com.management.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class ProductServiceImpl extends DataServiceImpl<Product> implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public Product getByTitle(String title) throws IllegalArgumentException, NullPointerException {
        if (isBlank(title)) {
            throw new IllegalArgumentException("Incorect name of product");
        }
        Product product = repository.findByTitle(title);
        if (product == null) {
            throw new NullPointerException("Haven`t product with such name " + title + " in database");
        }
        return product;
    }

    @Override
    public void removeByTitle(String title) {
        if (isNotBlank(title)) {
            repository.deleteByTitle(title);
        }
    }
}