package com.example.customer.service;

import com.example.customer.dto.CustomerDto;
import com.example.customer.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICustomerService {

    public Customer DtoToEntity(CustomerDto customerDto);



    public ResponseEntity<CustomerDto> getCustomerById(Long id);

    public List<CustomerDto> getAllCustomers();

    public ResponseEntity  <Map<String, Boolean>>  deleteCustomer(Long id);

    public CustomerDto createCustomer(CustomerDto customerDto);
}
