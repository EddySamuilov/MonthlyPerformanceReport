package com.example.monthlyperformancereport.services.impl;

import com.example.monthlyperformancereport.dtos.ReportSeedDto;
import com.example.monthlyperformancereport.entities.Report;
import com.example.monthlyperformancereport.repositories.ReportRepository;
import com.example.monthlyperformancereport.services.ReportService;
import com.example.monthlyperformancereport.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.Random;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void save(ReportSeedDto reportSeedDto) {

        Report report = this.modelMapper.map(reportSeedDto, Report.class);

        if (!this.validationUtil.isValid(report)){
            this.validationUtil.getViolations(report)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }

        this.reportRepository.saveAndFlush(report);
    }

    @Override
    public Report getReport() {
        Random random = new Random();
        long randomReportId = random.nextInt((int) (this.reportRepository.count()) ) + 1;

        return this.reportRepository.getOne(randomReportId);
    }
}
