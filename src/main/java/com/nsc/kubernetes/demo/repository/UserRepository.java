package com.nsc.kubernetes.demo.repository;

import com.nsc.kubernetes.demo.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, String> {
}
