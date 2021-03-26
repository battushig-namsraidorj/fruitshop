package com.profnaya.fruitshop.controller.v1;

import com.profnaya.fruitshop.api.v1.model.CustomerDTO;
import com.profnaya.fruitshop.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.profnaya.fruitshop.controller.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    public static final String FIRSTNAME = "John";
    public static final String LASTNAME = "Doe";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(FIRSTNAME);
        customer1.setLastname(LASTNAME);
        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Mary");
        customer2.setLastname("Jones");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
        verify(customerService, times(1)).getAllCustomers();

    }

    @Test
    void testGetCustomerById() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)));


    }

    @Test
    void testCreateNewCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setCustomUrl(CustomerController.BASE_URL + "/1");

        when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setCustomUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(1L, customer)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(MockMvcRequestBuilders.put(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    void testDeleteCustomerById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }
}