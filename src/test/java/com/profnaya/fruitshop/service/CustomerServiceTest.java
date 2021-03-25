package com.profnaya.fruitshop.service;

import com.profnaya.fruitshop.api.v1.mapper.CustomerMapper;
import com.profnaya.fruitshop.api.v1.model.CustomerDTO;
import com.profnaya.fruitshop.domain.Customer;
import com.profnaya.fruitshop.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    public static final Long ID = 2L;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);

    }

    @Test
    void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(3, customerDTOS.size());
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRST_NAME);
        customer.setLastname(LAST_NAME);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(FIRST_NAME, customerDTO.getFirstname());
        assertEquals(LAST_NAME, customerDTO.getLastname());
    }
}