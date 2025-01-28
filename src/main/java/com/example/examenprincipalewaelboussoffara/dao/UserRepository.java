package com.example.examenprincipalewaelboussoffara.dao;

import com.example.examenprincipalewaelboussoffara.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);
    @Query("SELECT u FROM User u WHERE u.events IS NOT EMPTY")
    List<User> findUsersWithEvents();


}
