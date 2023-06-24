package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacutyRepository;

import java.util.*;

@Service
public class FacultyService {
private final FacutyRepository facultyRepository;

    public FacultyService(FacutyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

// add. get. getAll. update. delete.

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFacultyById(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

}
