package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {

    private Map<Long, Student> students;

    private Long studentId = 0L;

    public StudentService() {
        this.students = new HashMap<>();
    }

// add. get. getAll. update. delete.

    public Student addStudent(Student student) {
        student.setId(++studentId);
        students.put(studentId, student);
        return student;
    }

    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    public Collection<Student> getAllStudents() {
        return Collections.unmodifiableCollection(students.values());
    }

    public Student updateStudent(Student student) {

        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudentById(Long studentId) {
        return students.remove(studentId);
    }

    public Collection<Student> getStudentsFilteredByAge(int age) {

        List<Student> list = new ArrayList();

        for (Student student : students.values()
             ) {
            if (student.getAge() == age) {
                list.add(student);
            }
        }
        return Collections.unmodifiableCollection(list);
    }
}
