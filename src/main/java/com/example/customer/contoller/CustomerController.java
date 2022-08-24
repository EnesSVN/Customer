package com.example.customer.contoller;


import com.example.customer.dto.CustomerDto;
import com.example.customer.service.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class CustomerController {

    private ICustomerService service;

    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    @PostMapping("/create/customer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        service.createCustomer(customerDto);
        return ResponseEntity.ok().body("Customer Created Successfully");
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> list = service.getAllCustomers();
        return list;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        ResponseEntity<CustomerDto> customer = service.getCustomerById(id);
        return customer;
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        Map<String, Boolean> customer = new HashMap<>();
        customer.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(customer);
    }
}

