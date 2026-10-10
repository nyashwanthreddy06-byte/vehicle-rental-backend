package com.vehiclerental.service;

import com.vehiclerental.entity.Customer;
import com.vehiclerental.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Customer existing = getCustomerById(id);
        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setAddress(updatedCustomer.getAddress());
        return repository.save(existing);
    }

    public void deleteCustomer(Integer id) {
        getCustomerById(id); // Ensure exists
        repository.deleteById(id);
    }
}