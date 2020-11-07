package com.dragon88.microservices.customer.repository;

import java.util.List;

import com.dragon88.microservices.customer.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
	   List<Customer> findByCustomerName(String customerName); 

}



