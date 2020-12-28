package com.bohra.covid19tracker.data.covid;

import java.util.List;

public class CovidResponse {
    private List<CasesTimeSeries> cases_time_series;
    private List<StateWise> statewise;
    private List<Tested> tested;

    public CovidResponse(List<CasesTimeSeries> cases_time_series, List<StateWise> statewise, List<Tested> tested) {
        this.cases_time_series = cases_time_series;
        this.statewise = statewise;
        this.tested = tested;
    }

    public List<CasesTimeSeries> getCases_time_series() {
        return cases_time_series;
    }

    public void setCases_time_series(List<CasesTimeSeries> cases_time_series) {
        this.cases_time_series = cases_time_series;
    }

    public List<StateWise> getStatewise() {
        return statewise;
    }

    public void setStatewise(List<StateWise> statewise) {
        this.statewise = statewise;
    }

    public List<Tested> getTested() {
        return tested;
    }

    public void setTested(List<Tested> tested) {
        this.tested = tested;
    }
}
