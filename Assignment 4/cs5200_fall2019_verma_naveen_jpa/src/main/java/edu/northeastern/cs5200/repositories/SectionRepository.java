package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

import edu.northeastern.cs5200.model.Section;

public interface SectionRepository extends CrudRepository<Section,Integer> {

  @Modifying
  @Transactional
  @Query(value = "TRUNCATE TABLE `section`", nativeQuery = true)
  public void truncateSection();
}
