package ru.hogwarts.school.constants;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public class ServiceTestConstants {

    public static Faculty FACULTY_1 = new Faculty(1L,"Faculty 1","Red" );
    public static final List<Faculty> FACULTY_LIST = List.of(
            FACULTY_1
    );

    public static Student STUDENT_1 = new Student();
    public static final List<Student> STUDENT_LIST = List.of(
            STUDENT_1
    );

}
