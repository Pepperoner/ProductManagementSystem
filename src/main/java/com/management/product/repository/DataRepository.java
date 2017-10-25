package com.management.product.repository;

import com.management.product.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository<T extends Model> extends JpaRepository<T, Long> {
}
