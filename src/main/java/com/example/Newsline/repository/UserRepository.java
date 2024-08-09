package com.example.Newsline.repository;

import com.example.Newsline.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Page<User> findAll(Pageable pageable);
}
