package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

// add. get. getAll. update. delete.

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student student) {
        studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        Student oldStudent = new Student();
        oldStudent.setId(id);
        oldStudent.setAge(student.getAge());
        oldStudent.setName(student.getName());
        return studentRepository.save(oldStudent);
    }

    public Student deleteStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(id));
        studentRepository.deleteById(id);
        return student;
    }

    public List<Student> getStudentsByAgeBetween(int ageFrom, int ageTo) {
        return studentRepository.getStudentsByAgeBetween(ageFrom, ageTo);
    }

    public Faculty getFacultyByStudentId(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(id));
        return student.getFaculty();
    }

    public Integer getStudentsAmount(){
        return studentRepository.getStudentsAmount();
    }

    public Integer getStudentsAverageAge(){
        return studentRepository.getStudentsAverageAge();
    }

    public List<Student> getStudentsLastFive() {
        return studentRepository.getStudentsLastFive();
    }
}
