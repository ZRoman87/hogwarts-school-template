package ru.hogwarts.school.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.service.StudentService;

public class StudentNotFoundException extends RuntimeException {

    private final long id;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentNotFoundException(long id) {
        logger.error("There is no student with id {}",  id);
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Студент с id = " + id + " не найден!";
    }

}
