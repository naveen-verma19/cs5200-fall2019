package edu.northeastern.cs5200;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.northeastern.cs5200.daos.UniversityDao;
import edu.northeastern.cs5200.model.Course;
import edu.northeastern.cs5200.model.Enrollment;
import edu.northeastern.cs5200.model.Faculty;
import edu.northeastern.cs5200.model.Section;
import edu.northeastern.cs5200.model.Student;
import edu.northeastern.cs5200.model.User;
import edu.northeastern.cs5200.repositories.CourseRepository;
import edu.northeastern.cs5200.repositories.EnrollmentRepository;
import edu.northeastern.cs5200.repositories.FacultyRepository;
import edu.northeastern.cs5200.repositories.SectionRepository;
import edu.northeastern.cs5200.repositories.StudentRepository;
import edu.northeastern.cs5200.repositories.UserRepository;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UniversityDaoTest {
  @Autowired
   UserRepository userRepository;
  @Autowired
  private FacultyRepository facultyRepository;
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SectionRepository sectionRepository;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private EnrollmentRepository enrollmentRepository;

  private UniversityDao universityDao;

  @Before
  public void setup() throws Exception {
    universityDao= new UniversityDao(userRepository, facultyRepository,
            studentRepository,courseRepository,sectionRepository,enrollmentRepository);
  }

  @Test
  public void stage_005_emptyTheDatabase(){
    universityDao.truncateDatabase();
  }

  @Test
  public void stage_006_createFaculties(){
    Faculty alan = new Faculty("Alan", "Turin", "123A", true);
    Faculty ada = new Faculty("Ada", "Lovelace", "123B", true);
    universityDao.createFaculty(alan);
    universityDao.createFaculty(ada);
  }

  @Test
  public void stage_007_createStudents(){
    Student alice= new Student("Alice","Wonderland",2020,12000);
    Student bob= new Student("Bob","Hope",2021,23000);
    Student charlie= new Student("Charlie","Brown",2019,21000);
    Student dan= new Student("Dan","Craig",2019,0);
    Student edward= new Student("Edward","Scissorhands",2022,11000);
    Student frank= new Student("Frank","Herbert",2018,0);
    Student greg= new Student("Gregory","Peck",2023,10000);

    universityDao.createStudent(alice);
    universityDao.createStudent(bob);
    universityDao.createStudent(charlie);
    universityDao.createStudent(dan);
    universityDao.createStudent(edward);
    universityDao.createStudent(frank);
    universityDao.createStudent(greg);
  }

  @Test
  public void stage_008_createCourses(){
    Course c1= new Course();
    c1.setLabel("CS1234");
    universityDao.createCourse(c1);

    Course c2= new Course();
    c2.setLabel("CS2345");
    universityDao.createCourse(c2);

    Course c3= new Course();
    c3.setLabel("CS3456");
    universityDao.createCourse(c3);

    Course c4= new Course();
    c4.setLabel("CS4567");
    universityDao.createCourse(c4);

    Faculty alan= universityDao.findFacultyByFirstName("Alan");
    universityDao.setAuthorForCourse(alan,c1);
    universityDao.setAuthorForCourse(alan,c2);

    Faculty ada= universityDao.findFacultyByFirstName("Ada");
    universityDao.setAuthorForCourse(ada,c3);
    universityDao.setAuthorForCourse(ada,c4);
  }

  @Test
  public void stage_009_createSections() {

    Section s1=new Section("SEC4321",50);
    Section s2=new Section("SEC5432",50);
    Section s3=new Section("SEC6543",50);
    Section s4=new Section("SEC7654",50);
    universityDao.createSection(s1);
    universityDao.createSection(s2);
    universityDao.createSection(s3);
    universityDao.createSection(s4);


    Course c1= universityDao.findCourseByTitle("CS1234");
    Course c2= universityDao.findCourseByTitle("CS2345");
    Course c3= universityDao.findCourseByTitle("CS3456");


    universityDao.addSectionToCourse(s1,c1);
    universityDao.addSectionToCourse(s2,c1);
    universityDao.addSectionToCourse(s3,c2);
    universityDao.addSectionToCourse(s4,c3);

  }

  @Test
  public void stage_010_enrollStudents() {
    Section s1 = universityDao.findSectionByTitle("SEC4321");
    Section s2 = universityDao.findSectionByTitle("SEC5432");
    Section s3 = universityDao.findSectionByTitle("SEC6543");

    Student alice = universityDao.findStudentByFirstName("Alice");
    Student bob = universityDao.findStudentByFirstName("Bob");
    Student charlie = universityDao.findStudentByFirstName("Charlie");


    //student will not get enrolled if seats = 0 in that section.
    universityDao.enrollStudentInSection(alice, s1);
    universityDao.enrollStudentInSection(alice, s2);

    universityDao.enrollStudentInSection(bob, s2);
    universityDao.enrollStudentInSection(charlie, s3);

  }

  @Test
  public void stage_011_validateTotalUsers() {
    assertEquals(9, universityDao.findAllUsers().size());
  }

  @Test
  public void stage_012_validateTotalFaculty() {
    assertEquals(2, universityDao.findAllFaculty().size());
  }
  @Test
  public void stage_013_validateTotalStudents() {
    assertEquals(7, universityDao.findAllStudents().size());
  }
  @Test
  public void stage_014_validateTotalCourses() {
    assertEquals(4, universityDao.findAllCourses().size());
  }
  @Test
  public void stage_015_validateTotalSections() {
    assertEquals(4, universityDao.findAllSections().size());
  }

  @Test
  public void stage_016_validateCourseAuthorship() {
    Faculty alan= universityDao.findFacultyByFirstName("Alan");
    assertEquals(2,universityDao.findCoursesForAuthor(alan).size());

    Faculty ada= universityDao.findFacultyByFirstName("Ada");
    assertEquals(2,universityDao.findCoursesForAuthor(ada).size());
  }

  @Test
  public void stage_017_validateSectionsEachCourse() {
    Course c1= universityDao.findCourseByTitle("CS1234");
    Course c2= universityDao.findCourseByTitle("CS2345");
    Course c3= universityDao.findCourseByTitle("CS3456");
    Course c4= universityDao.findCourseByTitle("CS4567");


    assertEquals(2,universityDao.findSectionForCourse(c1).size());
    assertEquals(1,universityDao.findSectionForCourse(c2).size());
    assertEquals(1,universityDao.findSectionForCourse(c3).size());
    assertEquals(0,universityDao.findSectionForCourse(c4).size());
  }

  @Test
  public void stage_018_validateStudentsEachSection() {
    Section s1 = universityDao.findSectionByTitle("SEC4321");
    Section s2 = universityDao.findSectionByTitle("SEC5432");
    Section s3 = universityDao.findSectionByTitle("SEC6543");
    Section s4 = universityDao.findSectionByTitle("SEC7654");

    assertEquals(1,universityDao.findStudentsInSection(s1).size());
    assertEquals(2,universityDao.findStudentsInSection(s2).size());
    assertEquals(1,universityDao.findStudentsInSection(s3).size());
    assertEquals(0,universityDao.findStudentsInSection(s4).size());
  }

  @Test
  public void stage_019_validateSectionEachStudent() {
    Student alice = universityDao.findStudentByFirstName("Alice");
    Student bob = universityDao.findStudentByFirstName("Bob");
    Student charlie = universityDao.findStudentByFirstName("Charlie");
    Student dan = universityDao.findStudentByFirstName("Dan");
    Student ed = universityDao.findStudentByFirstName("Edward");
    Student frank = universityDao.findStudentByFirstName("Frank");
    Student gregory = universityDao.findStudentByFirstName("Gregory");

    assertEquals(2,universityDao.findSectionsForStudent(alice).size());
    assertEquals(1,universityDao.findSectionsForStudent(bob).size());
    assertEquals(1,universityDao.findSectionsForStudent(charlie).size());
    assertEquals(0,universityDao.findSectionsForStudent(dan).size());
    assertEquals(0,universityDao.findSectionsForStudent(ed).size());
    assertEquals(0,universityDao.findSectionsForStudent(frank).size());
    assertEquals(0,universityDao.findSectionsForStudent(gregory).size());
  }

  @Test
  public void stage_020_validateSeatsEachSection() {
    Section s1 = universityDao.findSectionByTitle("SEC4321");
    Section s2 = universityDao.findSectionByTitle("SEC5432");
    Section s3 = universityDao.findSectionByTitle("SEC6543");
    Section s4 = universityDao.findSectionByTitle("SEC7654");

    assertEquals(49,s1.getSeats());
    assertEquals(48,s2.getSeats());
    assertEquals(49,s3.getSeats());
    assertEquals(50,s4.getSeats());

  }

  }