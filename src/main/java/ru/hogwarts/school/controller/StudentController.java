package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @PostMapping // POST http://localhost:8080/student
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student addedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(addedStudent);
    }

    @GetMapping("{id}") // GET http://localhost:8080/student/1
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/all") // GET http://localhost:8080/student/all
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}") // PUT http://localhost:8080/student/1
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/student/1
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        Student deletedStudent = studentService.deleteStudentById(id);
        return ResponseEntity.ok(deletedStudent);
    }

    @GetMapping("/filter") // GET http://localhost:8080/student/filter?ageFrom=18&ageTo=25
    public ResponseEntity<List<Student>> getStudentsByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo) {
        return ResponseEntity.ok(studentService.getStudentsByAgeBetween(ageFrom, ageTo));
    }

    @GetMapping("{id}/faculty") // GET http://localhost:8080/student/1/faculty
    public ResponseEntity<Faculty> getFacultyByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getFacultyByStudentId(id));
    }

    @PostMapping(value = "{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }

    }

    @GetMapping("/amount") // GET http://localhost:8080/student/amount
    public ResponseEntity<Integer> getStudentsAmount() {
        return ResponseEntity.ok(studentService.getStudentsAmount());
    }

    @GetMapping("/average-age") // GET http://localhost:8080/student/average-age
    public ResponseEntity<Integer> getStudentsAverageAge() {
        return ResponseEntity.ok(studentService.getStudentsAverageAge());
    }

    @GetMapping("/last-five") // GET http://localhost:8080/student/last-five
    public ResponseEntity<List<Student>> getStudentsLastFive() {
        return ResponseEntity.ok(studentService.getStudentsLastFive());
    }

    @GetMapping("/all/A") // GET http://localhost:8080/student/all/А
    public ResponseEntity<List<String>> getStudentsNameStartedWithA() {
        return ResponseEntity.ok(studentService.getStudentsNameStartedWithA());
    }

    @GetMapping("/all/sout") // GET http://localhost:8080/student/all/sout
    public ResponseEntity<Collection<Student>> getAllStudentsToSout() {
        return ResponseEntity.ok(studentService.getAllStudentsToSout());
    }

    @GetMapping("/all/sout/sync") // GET http://localhost:8080/student/all/sout/sync
    public ResponseEntity<Collection<Student>> getAllStudentsToSoutSync() {
        return ResponseEntity.ok(studentService.getAllStudentsToSoutSync());
    }

}
