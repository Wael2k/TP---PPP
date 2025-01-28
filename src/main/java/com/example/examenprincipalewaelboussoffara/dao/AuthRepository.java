package com.example.examenprincipalewaelboussoffara.dao;

import com.example.examenprincipalewaelboussoffara.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
}
