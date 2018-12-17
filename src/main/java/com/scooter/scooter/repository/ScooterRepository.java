package com.scooter.scooter.repository;

import com.scooter.scooter.domain.Scooter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScooterRepository extends CrudRepository<Scooter, Long> {
}
