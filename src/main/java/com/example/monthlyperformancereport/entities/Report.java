package com.example.monthlyperformancereport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report extends BaseEntity{
    private int topPerformersThreshold;
    private boolean useExprienceMultiplier;
    private int periodLimit;

    public Report() {
    }

    @Column(name = "top_performers_threshold")
    public int getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public void setTopPerformersThreshold(int topPerformersThreshold) {
        this.topPerformersThreshold = topPerformersThreshold;
    }

    @Column(name = "is_used_experience")
    public boolean isUseExprienceMultiplier() {
        return useExprienceMultiplier;
    }

    public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
        this.useExprienceMultiplier = useExprienceMultiplier;
    }

    @Column(name = "period_limit")
    public int getPeriodLimit() {
        return periodLimit;
    }

    public void setPeriodLimit(int periodLimit) {
        this.periodLimit = periodLimit;
    }

    @Override
    public String toString() {
        return "Report{" +
                "topPerformersThreshold='" + topPerformersThreshold + '\'' +
                ", useExprienceMultiplier=" + useExprienceMultiplier +
                ", periodLimit=" + periodLimit +
                '}';
    }
}
