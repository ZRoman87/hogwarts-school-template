package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }


// add. get. getAll. update. delete.

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was called method to add faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        logger.info("Was called method to get faculty with id {}", id);
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Was called method to get all faculties");
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        logger.info("Was called method to update faculty with id {}", id);
        facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        Faculty oldFaculty = new Faculty();
        oldFaculty.setId(id);
        oldFaculty.setColor(faculty.getColor());
        oldFaculty.setName(faculty.getName());
        return facultyRepository.save(oldFaculty);
    }

    public Faculty deleteFacultyById(Long id) {
        logger.info("Was called method to delete faculty with id {}", id);
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(id));
        facultyRepository.deleteById(id);
        return faculty;
    }

    public List<Faculty> getFacultiesByColorOrName(String color, String name) {
        logger.info("Was called method to get faculty with color {} or name {}", color, name);
        return facultyRepository.getFacultiesByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public List<Student> getStudentsByFacultyId(Long id){
        logger.info("Was called method to get students on faculty with id {}", id);
        return studentRepository.getStudentsByFaculty_Id(id);
    }

}
