package com.qingmei.agriculture.repository;

import com.qingmei.agriculture.entity.Commodity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommodityRepository extends CrudRepository<Commodity, String> {
    List<Commodity> findByComName(String comName);
//    Optional<Commodity> findById(UUID id);
}
