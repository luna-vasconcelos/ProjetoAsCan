package com.ascan.rabbitdockerascan.repository;

import com.ascan.rabbitdockerascan.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


}
