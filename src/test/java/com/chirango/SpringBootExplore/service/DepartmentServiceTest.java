package com.chirango.SpringBootExplore.service;

import com.chirango.SpringBootExplore.entity.Department;
import com.chirango.SpringBootExplore.exception.DepartmentNotFoundException;
import com.chirango.SpringBootExplore.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("UNC Chapel Hill")
                .departmentCode("ITIS-6144")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentName("IT")).thenReturn(department);
    }



    @Test
    public void fetchDepartmentByName_whenValidDepartmentName_thenReturnDepartment() {
        String departmentName = "CSE";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    public void fetchDepartmentById_whenValidDepartmentId_thenReturnDepartment() throws DepartmentNotFoundException {
        Long departmentId = 1L;
        Department found = departmentService.fetchDepartmentById(departmentId);
        assertEquals(departmentId, found.getDepartmentId());
    }
}