package com.qingmei.agriculture.repository;

import com.qingmei.agriculture.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, String> {
}
