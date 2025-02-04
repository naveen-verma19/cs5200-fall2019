package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

import edu.northeastern.cs5200.model.Course;

public interface CourseRepository extends CrudRepository<Course,Integer> {
  @Modifying
  @Transactional
  @Query(value = "TRUNCATE TABLE `course`", nativeQuery = true)
  public void truncateCourse();
}
