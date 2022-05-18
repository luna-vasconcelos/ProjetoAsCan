package com.ascan.ascanflixapi.repository;

import com.ascan.ascanflixapi.domain.model.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Integer> {
}
