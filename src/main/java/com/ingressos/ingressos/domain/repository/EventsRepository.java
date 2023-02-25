package com.ingressos.ingressos.domain.repository;

import com.ingressos.ingressos.domain.repository.entity.Events;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventsRepository extends ReactiveCrudRepository<Events, Integer>{}
