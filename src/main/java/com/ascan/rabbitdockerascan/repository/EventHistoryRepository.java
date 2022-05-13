package com.ascan.rabbitdockerascan.repository;

import com.ascan.rabbitdockerascan.model.EventHistory;
import org.springframework.data.repository.CrudRepository;

public interface EventHistoryRepository extends CrudRepository<EventHistory, Integer> {
}
