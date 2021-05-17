package com.example.monthlyperformancereport.services.impl;

import com.example.monthlyperformancereport.dtos.EmployeeSeedDto;
import com.example.monthlyperformancereport.dtos.EmployeesScoreViewDto;
import com.example.monthlyperformancereport.entities.Employee;
import com.example.monthlyperformancereport.entities.Report;
import com.example.monthlyperformancereport.repositories.EmployeeRepository;
import com.example.monthlyperformancereport.services.EmployeeService;
import com.example.monthlyperformancereport.services.ReportService;
import com.example.monthlyperformancereport.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final ReportService reportService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, ValidationUtil validationUtil, ReportService reportService) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.reportService = reportService;
    }

    @Override
    public void seedEmployees(EmployeeSeedDto[] employeeSeedDtos) {
        Arrays.stream(employeeSeedDtos)
                .filter(e -> this.employeeRepository.findByName(e.getName()) == null)
                .map(e -> this.modelMapper.map(e, Employee.class))
                .forEach(e -> {
                    if (this.validationUtil.isValid(e)) {
                        this.employeeRepository.saveAndFlush(e);
                    } else {
                        this.validationUtil.getViolations(e)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<EmployeesScoreViewDto> getReport() {
        Report report = reportService.getReport();

        List<EmployeesScoreViewDto> employeesScoreViewDtos = this.employeeRepository.findAll()
                .stream()
                .filter(e -> e.getSalesPeriod() <= report.getPeriodLimit())
                .map(e -> {
                    EmployeesScoreViewDto employeesScoreViewDto = this.modelMapper.map(e, EmployeesScoreViewDto.class);
                    double score = calculateScore(report, e);
                    employeesScoreViewDto.setScore(score);
                    return employeesScoreViewDto;
                })
                .collect(Collectors.toList());
        double minBoundOfScore = calculateMinBoundOfScore(report, employeesScoreViewDtos);

        return employeesScoreViewDtos
                .stream()
                .filter(e -> e.getScore() > minBoundOfScore)
                .collect(Collectors.toList());

    }

    private double calculateMinBoundOfScore(Report report, List<EmployeesScoreViewDto> employeesScoreViewDtos){
        double thresholdInPercent = 1.0*report.getTopPerformersThreshold() / 100;

        double minimumScore = 0;
        for (EmployeesScoreViewDto employeesScoreViewDto : employeesScoreViewDtos) {
            minimumScore+=employeesScoreViewDto.getScore();
        }

        double floorRange = minimumScore * thresholdInPercent;
        return minimumScore - floorRange;
    }

    private double calculateScore(Report report, Employee e) {
        double score = 1.0* e.getTotalSales() / e.getSalesPeriod();
        if (report.isUseExprienceMultiplier()){
            score *= e.getExperienceMultiplier();
        }
        return score;
    }
}
