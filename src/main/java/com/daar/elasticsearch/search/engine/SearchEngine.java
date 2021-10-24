package com.daar.elasticsearch.search.engine;

import com.daar.elasticsearch.search.SearchCvRequest;

import java.util.List;

import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.util.CollectionUtils;

import org.elasticsearch.action.search.SearchRequest;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;

public class SearchEngine {

    private SearchEngine() {}

    public static SearchRequest buildSearchRequest(final String indexName, final SearchCvRequest request){
        final SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(getQueryBuilder(request));

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(builder);

        return searchRequest;
    }

    public static QueryBuilder getQueryBuilder(final SearchCvRequest request){
        if (request == null)
            return null;

        final List<String> fieldList = request.getFields();
        if(CollectionUtils.isEmpty(fieldList))
            return null;

        if(fieldList.size() > 1){
            //multiple search
            return multipleSearch(request, fieldList);
        }

        //only one field
        return simpleSearch(request, fieldList);
    }

    private static MatchQueryBuilder simpleSearch(SearchCvRequest request, List<String> fieldList) {
        return fieldList.stream()
                .findFirst()
                .map(field ->
                        QueryBuilders.matchQuery(field, request.getSearchTerm())
                                .operator(Operator.AND))
                .orElse(null);
    }

    private static MultiMatchQueryBuilder multipleSearch(SearchCvRequest request, List<String> fieldList) {
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(request.getSearchTerm())
                .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                .operator(Operator.AND);

        fieldList.forEach(queryBuilder::field);

        return queryBuilder;
    }
}
