package com.example.monthlyperformancereport.services;

import com.example.monthlyperformancereport.dtos.ReportSeedDto;
import com.example.monthlyperformancereport.entities.Report;

public interface ReportService {
    void save(ReportSeedDto reportSeedDto);

    Report getReport();
}
