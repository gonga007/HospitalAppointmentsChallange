package com.challenge.challenge;

import java.util.List;
import java.util.stream.Collectors;

public class Page <T> {
    private Integer pageNumber;
    private Integer resultsPerPage;
    private Integer totalResults;
    private List<T> items;

    public Page(Integer pageNumber, Integer totalResults, List<T> items, Integer resultsPerPage) {
        this.pageNumber = pageNumber;
        this.resultsPerPage = resultsPerPage;
        this.totalResults = totalResults;
        this.items = items;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    public Integer getResultsPerPage() {
        return resultsPerPage;
    }
    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }
    public Integer getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
    public Page<Patient> getPage(List<Patient> patients, int pageNumber, Integer resultsPerPage) {
        int skipCount = (pageNumber - 1) * resultsPerPage;

        List<Patient> patientsPage = patients
                .stream()
                .skip(skipCount)
                .limit(resultsPerPage)
                .collect(Collectors.toList());

        Page<Patient> page = new Page<>(pageNumber, patients.size(), patientsPage, resultsPerPage);

        return page;
    }
}
