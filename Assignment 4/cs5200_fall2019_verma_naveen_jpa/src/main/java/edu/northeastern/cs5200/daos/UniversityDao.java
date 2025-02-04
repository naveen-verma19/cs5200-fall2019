package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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

public class UniversityDao {

  @Autowired
  private UserRepository userRepository;
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

  public UniversityDao(UserRepository u, FacultyRepository f, StudentRepository st,
                       CourseRepository c, SectionRepository s, EnrollmentRepository e){
    this.userRepository=u;
    this.facultyRepository=f;
    this.studentRepository=st;
    this.courseRepository=c;
    this.sectionRepository=s;
    this.enrollmentRepository=e;
  }

  @Transactional
  public void truncateDatabase() {
    userRepository.truncateAllUsers();
    courseRepository.truncateCourse();
    enrollmentRepository.truncateEnrollment();
    sectionRepository.truncateSection();
  }

  public Faculty createFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);

  }

  public Section createSection(Section section) {
    return sectionRepository.save(section);
  }

  public void addSectionToCourse(Section section, Course course) {
    Optional<Course> opt = courseRepository.findById(course.getId());
    if (opt.isPresent()) {
      Course c = opt.get();

      section.setFaculty(course.getFaculty());
      section.setCourse(course);
      sectionRepository.save(section);
    } else {
      throw new IllegalArgumentException("Course is invalid");
    }
  }

  public Course setAuthorForCourse(Faculty faculty, Course course) {
    Optional<Course> opt = courseRepository.findById(course.getId());
    if (opt.isPresent()) {
      Course c = opt.get();
      c.setFaculty(faculty);
      return courseRepository.save(c);
    } else {
      throw new IllegalArgumentException("Course is invalid");
    }
  }

  public Boolean enrollStudentInSection(Student student, Section section) {
    int seats = section.getSeats();
    if (seats == 0) return false;
    else {
      Enrollment e = new Enrollment();
      e.setSection(section);
      e.setStudent(student);
      enrollmentRepository.save(e);
      section.setSeats(seats - 1);
      sectionRepository.save(section);
      return true;
    }
  }

  public List<User> findAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  public List<Faculty> findAllFaculty() {
    return (List<Faculty>) facultyRepository.findAll();
  }

  public List<Student> findAllStudents() {
    return (List<Student>) studentRepository.findAll();
  }

  public List<Course> findAllCourses() {
    return (List<Course>) courseRepository.findAll();
  }

  public List<Section> findAllSections() {
    return (List<Section>) sectionRepository.findAll();
  }

  public List<Course> findCoursesForAuthor(Faculty faculty) {
   List<Course> clist= (List<Course>)courseRepository.findAll();
   return clist.stream()
           .filter(x->x.getFaculty().getId()==faculty.getId())
           .collect(Collectors.toList());

  }

  public List<Section> findSectionForCourse(Course course) {
    List<Section> clist= (List<Section>)sectionRepository.findAll();
    return clist.stream()
            .filter(x->x.getCourse().getId()==course.getId())
            .collect(Collectors.toList());
  }

  public List<Student> findStudentsInSection(Section section) {
    List<Enrollment> enrolled = (List<Enrollment>) enrollmentRepository.findAll();
    return enrolled.stream().filter(x -> x.getSection().getId()==section.getId())
            .map(x -> x.getStudent()).collect(Collectors.toList());
  }

  public List<Section> findSectionsForStudent(Student student) {
    List<Enrollment> enrolled = (List<Enrollment>) enrollmentRepository.findAll();
    return enrolled.stream().filter(x -> x.getStudent().getId()==student.getId())
            .map(x -> x.getSection()).collect(Collectors.toList());
  }

  /**
   * Other Helper Methods
  **/
  public Faculty findFacultyByFirstName(String fname){
    return findAllFaculty().stream().filter(x->x.getFirstName().equals(fname)).collect(Collectors.toList()).get(0);
  }

  public Student findStudentByFirstName(String fname){
    return findAllStudents().stream().filter(x->x.getFirstName().equals(fname)).collect(Collectors.toList()).get(0);
  }

  public Section findSectionByTitle(String title){
    return findAllSections().stream().filter(x->x.getTitle().equals(title)).collect(Collectors.toList()).get(0);
  }

  public Course findCourseByTitle(String title){
    return findAllCourses().stream().filter(x->x.getLabel().equals(title)).collect(Collectors.toList()).get(0);
  }

}
