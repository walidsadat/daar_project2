package com.daar.elasticsearch.search;

import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

public class SearchCvRequest {
    private List<String> fields;
    private String searchTerm;
    private String sortBy;
    private SortOrder sortOrder;

    public List<String> getFields(){
        return fields;
    }

    public void setFields(List<String> fields){
        this.fields = fields;
    }

    public String getSearchTerm(){
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
