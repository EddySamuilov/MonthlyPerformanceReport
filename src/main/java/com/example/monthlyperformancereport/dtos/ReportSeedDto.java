package com.example.monthlyperformancereport.dtos;

import com.google.gson.annotations.Expose;

public class ReportSeedDto {
    @Expose
    private String topPerformersThreshold;
    @Expose
    private boolean useExprienceMultiplier;
    @Expose
    private int periodLimit;

    public ReportSeedDto() {
    }

    public String getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public void setTopPerformersThreshold(String topPerformersThreshold) {
        this.topPerformersThreshold = topPerformersThreshold;
    }

    public boolean isUseExprienceMultiplier() {
        return useExprienceMultiplier;
    }

    public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
        this.useExprienceMultiplier = useExprienceMultiplier;
    }

    public int getPeriodLimit() {
        return periodLimit;
    }

    public void setPeriodLimit(int periodLimit) {
        this.periodLimit = periodLimit;
    }
}
