package com.example.customer.service;


import com.example.customer.dto.CustomerDto;
import com.example.customer.entity.Customer;
import com.example.customer.exception.ResourceNotFoundException;
import com.example.customer.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService implements ICustomerService{

    public CustomerRepository repository;

    public ModelMapper modelMapper;

    public PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerDto EntityToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    public Customer DtoToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        String encodedPassword = passwordEncoder.encode(customerDto.getPassword());
        customerDto.setPassword(encodedPassword);
        Customer customer = DtoToEntity(customerDto);
        repository.save(customer);
        return customerDto;
    }

    public ResponseEntity<CustomerDto> getCustomerById(Long id){
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        CustomerDto customerDto = EntityToDto(customer);
        return ResponseEntity.ok(customerDto);
    }

    public List<CustomerDto> getAllCustomers(){
        List<CustomerDto> list = new ArrayList<>();
        Iterable<Customer> myList = repository.findAll();
        for (Customer customer : myList) {
            list.add(EntityToDto(customer));
        }
        return list;

    }

    public ResponseEntity<Map<String, Boolean>> deleteCustomer(Long id){
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        repository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}

