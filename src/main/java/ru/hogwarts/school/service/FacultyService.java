package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

@Service
public class FacultyService {

    private Map<Long, Faculty> faculties;

    private Long facultyId = 0L;

    public FacultyService() {
        this.faculties = new HashMap<>();
    }

// add. get. getAll. update. delete.

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++facultyId);
        faculties.put(facultyId, faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long facultyId) {
        return faculties.get(facultyId);
    }

    public Collection<Faculty> getAllFaculties() {
        return Collections.unmodifiableCollection(faculties.values());
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())){
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFacultyById(Long facultyId) {
        return faculties.remove(facultyId);
    }

    public Collection<Faculty> getFacultiesFilteredByColor(String color) {

        List<Faculty> list = new ArrayList();

        for (Faculty faculty : faculties.values()) {
            if (faculty.getColor().equals(color)) {
                list.add(faculty);
            }
        }
        return Collections.unmodifiableCollection(list);
    }

}
