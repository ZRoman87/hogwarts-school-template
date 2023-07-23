package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public Object flag = new Object();

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

// add. get. getAll. update. delete.

    public Student addStudent(Student student) {
        logger.info("Was called method to add student");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        //logger.info("Was called method to get student with id {}", id);
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was called method to get all students");
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student student) {
        logger.info("Was called method to update student with id {}", id);
        studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        Student oldStudent = new Student();
        oldStudent.setId(id);
        oldStudent.setAge(student.getAge());
        oldStudent.setName(student.getName());
        return studentRepository.save(oldStudent);
    }

    public Student deleteStudentById(Long id) {
        logger.info("Was called method to delete student with id {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
        return student;
    }

    public List<Student> getStudentsByAgeBetween(int ageFrom, int ageTo) {
        logger.info("Was called method to get student by age between {} and {}", ageFrom, ageTo);
        return studentRepository.getStudentsByAgeBetween(ageFrom, ageTo);
    }

    public Faculty getFacultyByStudentId(Long id) {
        logger.info("Was called method to get faculty for student with id {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(id));
        return student.getFaculty();
    }

    public Integer getStudentsAmount() {
        logger.info("Was called method to get students amount");
        return studentRepository.getStudentsAmount();
    }

    public Integer getStudentsAverageAge() {
        logger.info("Was called method to get students average age");
        return studentRepository.getStudentsAverageAge();
    }

    public List<Student> getStudentsLastFive() {
        logger.info("Was called method to get last five students");
        return studentRepository.getStudentsLastFive();
    }

    public List<String> getStudentsNameStartedWithA() {
        return getAllStudents().stream().
                filter(e -> e.getName().startsWith("А")).
                map(v -> v.getName().toUpperCase()).
                sorted().
                distinct().
                collect(Collectors.toList());
    }

}
