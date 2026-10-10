package com.vehiclerental.controller;

import com.vehiclerental.entity.Customer;
import com.vehiclerental.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(service.addCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id,
                                                    @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(service.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}