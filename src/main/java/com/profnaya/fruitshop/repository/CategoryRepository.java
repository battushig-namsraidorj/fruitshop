package com.profnaya.fruitshop.repository;

import com.profnaya.fruitshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
