package com.example.monthlyperformancereport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{

    private String name;
    private int totalSales;
    private int salesPeriod;
    private double experienceMultiplier;

    public Employee() {
    }

    @NotNull
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "total_sales")
    @DecimalMin(value = "0", message = "Sales count can't be less than zero!")
    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    @Column(name = "sales_period")
    @DecimalMin(value = "0", message = "Period can't be a negative number!")
    public int getSalesPeriod() {
        return salesPeriod;
    }

    public void setSalesPeriod(int salesPeriod) {
        this.salesPeriod = salesPeriod;
    }

    @Column(name = "experience_multiplier")
    @DecimalMin(value = "0", message = "Multiplier can't be a negative number!")
    public double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public void setExperienceMultiplier(double experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", totalSales=" + totalSales +
                ", salesPeriod=" + salesPeriod +
                ", experienceMultiplier=" + experienceMultiplier +
                '}';
    }
}
