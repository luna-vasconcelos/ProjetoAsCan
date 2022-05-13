package com.ascan.ascanflixapi.repository;

import com.ascan.ascanflixapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


}
