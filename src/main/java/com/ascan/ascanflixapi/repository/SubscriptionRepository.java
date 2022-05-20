package com.ascan.ascanflixapi.repository;

import com.ascan.ascanflixapi.domain.model.Subscription;
import com.ascan.ascanflixapi.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    Optional<Subscription> findByUser(User user);
}
