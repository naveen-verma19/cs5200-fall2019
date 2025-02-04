package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

import edu.northeastern.cs5200.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {
  @Modifying
  @Transactional
  @Query(value = "TRUNCATE TABLE `user`", nativeQuery = true)
  public void truncateAllUsers();
}
