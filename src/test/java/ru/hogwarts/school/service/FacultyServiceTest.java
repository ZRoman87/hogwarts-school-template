package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.hogwarts.school.constants.ServiceTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    private FacultyRepository facultyRepository;
    private FacultyService facultyService;

    @BeforeEach
    public void beforeEach() {
        facultyRepository = mock(FacultyRepository.class);
        facultyService = new FacultyService(facultyRepository);
    }

    @Test
    public void shouldReturnCorrectFacultyAfterAdd() {
        when(facultyRepository.save(any())).thenReturn(FACULTY_1);
        Faculty actual = facultyService.addFaculty(FACULTY_1);
        Faculty expected = FACULTY_1;

        assertEquals(expected, actual);
        verify(facultyRepository, times(1)).save(any());
    }

    @Test
    public void shouldReturnCorrectFacultyAfterUpdate() {
        when(facultyRepository.save(any())).thenReturn(FACULTY_1);
        when(facultyRepository.findById(1L)).thenReturn(Optional.ofNullable(FACULTY_1));
        Faculty actual = facultyService.updateFaculty(1L, FACULTY_1);
        Faculty expected = FACULTY_1;

        assertEquals(expected, actual);
        verify(facultyRepository, times(1)).save(any());
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToUpdateNotExistFaculty() {
        when(facultyRepository.findById(any())).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> facultyService.updateFaculty(1L,FACULTY_1)
        );
    }

    @Test
    public void shouldReturnCorrectListOfFacultiesAfterGetAll() {
        when(facultyRepository.findAll()).thenReturn(FACULTY_LIST);
        Collection<Faculty> actual = facultyService.getAllFaculties();
        List<Faculty> expected = FACULTY_LIST;

        assertEquals(expected, actual);
        verify(facultyRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnCorrectFacultyAfterAfterFindById() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.ofNullable(FACULTY_1));
        Faculty actual = facultyService.getFacultyById(1L);
        Faculty expected = FACULTY_1;

        assertEquals(expected, actual);
        verify(facultyRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToFindNotExistFaculty() {
        when(facultyRepository.findById(any())).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> facultyService.getFacultyById(1L)
        );
    }
    @Test
    public void shouldReturnCorrectFacultyAfterDelete() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.ofNullable(FACULTY_1));
        Faculty actual = facultyService.deleteFacultyById(1L);
        Faculty expected = FACULTY_1;

        assertEquals(expected, actual);
        verify(facultyRepository, times(1)).findById(1L);
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToDeleteNotExistFaculty() {
        when(facultyRepository.findById(any())).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> facultyService.deleteFacultyById(1L)
        );
    }



}
