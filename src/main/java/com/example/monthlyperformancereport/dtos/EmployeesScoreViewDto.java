package com.example.monthlyperformancereport.dtos;

import com.google.gson.annotations.Expose;

public class EmployeesScoreViewDto {
    @Expose
    private String name;
    @Expose
    private double score;

    public EmployeesScoreViewDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("%s, %.2f", this.name, this.score);
    }
}
