package com.profnaya.fruitshop.repository;

import com.profnaya.fruitshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
