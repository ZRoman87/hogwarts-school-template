package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.hogwarts.school.constants.ServiceTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService StudentService;

    @BeforeEach
    public void beforeEach() {
        studentRepository = mock(StudentRepository.class);
        StudentService = new StudentService(studentRepository);
    }

    @Test
    public void shouldReturnCorrectStudentAfterAdd() {
        when(studentRepository.save(any())).thenReturn(STUDENT_1);
        Student actual = StudentService.addStudent(STUDENT_1);
        Student expected = STUDENT_1;

        assertEquals(expected, actual);
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void shouldReturnCorrectStudentAfterUpdate() {
        when(studentRepository.save(any())).thenReturn(STUDENT_1);
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(STUDENT_1));
        Student actual = StudentService.updateStudent(1L, STUDENT_1);
        Student expected = STUDENT_1;

        assertEquals(expected, actual);
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToUpdateNotExistStudent() {
        when(studentRepository.findById(any())).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> StudentService.updateStudent(1L,STUDENT_1)
        );
    }

    @Test
    public void shouldReturnCorrectListOfFacultiesAfterGetAll() {
        when(studentRepository.findAll()).thenReturn(STUDENT_LIST);
        Collection<Student> actual = StudentService.getAllStudents();
        List<Student> expected = STUDENT_LIST;

        assertEquals(expected, actual);
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnCorrectStudentAfterAfterFindById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(STUDENT_1));
        Student actual = StudentService.getStudentById(1L);
        Student expected = STUDENT_1;

        assertEquals(expected, actual);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToFindNotExistStudent() {
        when(studentRepository.findById(any())).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> StudentService.getStudentById(1L)
        );
    }
    @Test
    public void shouldReturnCorrectStudentAfterDelete() {
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(STUDENT_1));
        Student actual = StudentService.deleteStudentById(1L);
        Student expected = STUDENT_1;

        assertEquals(expected, actual);
        verify(studentRepository, times(1)).findById(1L);
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToDeleteNotExistStudent() {
        when(studentRepository.findById(any())).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> StudentService.deleteStudentById(1L)
        );
    }



}
