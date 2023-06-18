package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping //http://localhost:8080/faculty
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty){
        Faculty addedFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(addedFaculty);
    }

    @GetMapping("{FacultyId}") //http://localhost:8080/faculty/0
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long FacultyId){
        Faculty faculty = facultyService.getFacultyById(FacultyId);
        if (faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/all") // http://localhost:8080/faculty
    public ResponseEntity<Collection<Faculty>> getAllFaculties(){
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PutMapping //http://localhost:8080/faculty
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}") // http://localhost:8080/faculty/0
    public ResponseEntity<Faculty> deleteFacultyById(@PathVariable Long facultyId){
        Faculty Faculty = facultyService.deleteFacultyById(facultyId);
        if (Faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultiesFilteredByAge(@RequestParam(required = false) String facultyColor){
        Collection<Faculty> faculties = facultyService.getFacultiesFilteredByColor(facultyColor);
        if (faculties == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }

}
