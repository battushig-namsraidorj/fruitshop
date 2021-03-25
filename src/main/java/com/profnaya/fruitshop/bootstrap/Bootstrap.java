package com.profnaya.fruitshop.bootstrap;

import com.profnaya.fruitshop.domain.Category;
import com.profnaya.fruitshop.domain.Customer;
import com.profnaya.fruitshop.repository.CategoryRepository;
import com.profnaya.fruitshop.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        loadData();


    }

    private void loadData() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category data loaded: " + categoryRepository.count());

        Customer john = new Customer();
        john.setFirstname("John");
        john.setLastname("Doe");

        Customer mary = new Customer();
        mary.setFirstname("Mary");
        mary.setLastname("Jones");

        customerRepository.save(john);
        customerRepository.save(mary);

        System.out.println("Customer data loaded: " + customerRepository.count());
    }
}
