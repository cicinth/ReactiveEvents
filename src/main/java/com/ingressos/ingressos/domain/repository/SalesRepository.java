package com.ingressos.ingressos.domain.repository;

import com.ingressos.ingressos.domain.repository.entity.Sales;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalesRepository extends ReactiveCrudRepository<Sales, Integer> {
}
