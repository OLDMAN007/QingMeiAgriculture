package com.qingmei.agriculture.repository;

import com.qingmei.agriculture.entity.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {
    List<Measurement> findByName(String name);
}
