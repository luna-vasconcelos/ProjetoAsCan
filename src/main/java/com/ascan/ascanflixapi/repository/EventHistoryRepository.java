package com.ascan.ascanflixapi.repository;

import com.ascan.ascanflixapi.domain.model.EventHistory;
import org.springframework.data.repository.CrudRepository;

public interface EventHistoryRepository extends CrudRepository<EventHistory, Integer> {
}
