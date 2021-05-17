package com.example.monthlyperformancereport.services;

import com.example.monthlyperformancereport.dtos.EmployeesScoreViewDto;
import com.example.monthlyperformancereport.dtos.EmployeeSeedDto;

import java.util.List;

public interface EmployeeService {
    void seedEmployees(EmployeeSeedDto[] employeeSeedDtos);

    List<EmployeesScoreViewDto> getReport();
}
