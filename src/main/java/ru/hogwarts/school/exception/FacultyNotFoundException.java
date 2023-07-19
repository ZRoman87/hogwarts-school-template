package ru.hogwarts.school.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.service.StudentService;

public class FacultyNotFoundException extends RuntimeException {

    private final long id;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public FacultyNotFoundException(long id) {
        logger.error("There is no faculty with id {}",  id);
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Факультет с id = " + id + " не найден!";
    }

}
