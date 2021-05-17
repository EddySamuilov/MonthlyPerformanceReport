package com.example.monthlyperformancereport.init;

import com.example.monthlyperformancereport.dtos.EmployeesScoreViewDto;
import com.example.monthlyperformancereport.dtos.EmployeeSeedDto;
import com.example.monthlyperformancereport.dtos.ReportSeedDto;
import com.example.monthlyperformancereport.services.EmployeeService;
import com.example.monthlyperformancereport.services.ReportService;
import com.example.monthlyperformancereport.utils.FileUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.example.monthlyperformancereport.constants.GlobalConstants.*;

@Component
public class AppInitializer implements CommandLineRunner {
    private final Gson gson;
    private final FileUtil fileUtil;
    private final EmployeeService employeeService;
    private final ReportService reportService;

    @Autowired
    public AppInitializer(Gson gson, FileUtil fileUtil, EmployeeService employeeService, ReportService reportService) {
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.employeeService = employeeService;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedData(args[0]);
        this.saveDefinitionReport(args[1]);

        this.makeReport();

    }

    private void makeReport() throws IOException {
        List<EmployeesScoreViewDto> employeesScoreViewDto = this.employeeService.getReport();

        for (EmployeesScoreViewDto scoreViewDto : employeesScoreViewDto) {
            this.fileUtil.write(scoreViewDto.toString(), OUTPUT_REPORT_DEFINITION_PATH);
        }

    }

    private void saveDefinitionReport(String reportDefinitionFilePath) throws FileNotFoundException {
        ReportSeedDto reportSeedDto = this.gson.fromJson(new FileReader(reportDefinitionFilePath), ReportSeedDto.class);

        this.reportService.save(reportSeedDto);
    }

    private void seedData(String dataPath) throws FileNotFoundException {
        EmployeeSeedDto[] employeeSeedDtos = this.gson.fromJson(new FileReader(dataPath), EmployeeSeedDto[].class);

        this.employeeService.seedEmployees(employeeSeedDtos);
    }
}
