package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.model.Student;


public interface StudentRepository extends CrudRepository<Student,Integer> {
}
