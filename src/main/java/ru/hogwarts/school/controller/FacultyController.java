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

    @PostMapping // POST http://localhost:8080/faculty
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty){
        Faculty addedFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(addedFaculty);
    }

    @GetMapping("{FacultyId}") //GET http://localhost:8080/faculty/0
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long FacultyId){
        Faculty faculty = facultyService.getFacultyById(FacultyId);
        if (faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/all") // GET http://localhost:8080/faculty
    public ResponseEntity<Collection<Faculty>> getAllFaculties(){
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PutMapping //PUT http://localhost:8080/faculty
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}") // DELETE http://localhost:8080/faculty/0
    public ResponseEntity deleteFacultyById(@PathVariable Long facultyId){
        facultyService.deleteFacultyById(facultyId);
        return ResponseEntity.ok().build();
    }

}
