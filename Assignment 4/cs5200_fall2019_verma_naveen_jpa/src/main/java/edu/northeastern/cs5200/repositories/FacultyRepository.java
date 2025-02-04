package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import edu.northeastern.cs5200.model.Faculty;
import edu.northeastern.cs5200.model.User;


public interface FacultyRepository extends CrudRepository<Faculty,Integer> {

}
