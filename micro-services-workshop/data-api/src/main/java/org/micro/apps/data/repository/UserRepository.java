package org.micro.apps.data.repository;

import org.micro.apps.common.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tibinatomy
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new org.micro.apps.common.dto.User(u.id, u.email, u.password, u.role, u.name , u.token, u.created, u.updated) from User u where u.email = ?1")
    List<User> findByEmail(String email);

}
