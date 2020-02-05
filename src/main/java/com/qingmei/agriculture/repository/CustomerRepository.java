package com.qingmei.agriculture.repository;

import com.qingmei.agriculture.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    List<Customer> findByCusName(String cusName);

}
