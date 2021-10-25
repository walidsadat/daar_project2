package com.daar.elasticsearch.service;

import com.daar.elasticsearch.indices.Index;
import com.daar.elasticsearch.model.Cv;
import com.daar.elasticsearch.search.SearchCvRequest;
import com.daar.elasticsearch.search.engine.SearchEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class CvService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);

    private final RestHighLevelClient client;

    @Autowired
    public CvService(RestHighLevelClient client) {
        this.client = client;
    }

    public List<Cv> getAllCv(){
        return search(new SearchCvRequest());
    }

    public List<Cv> search(final SearchCvRequest request){
        final SearchRequest searchRequest = SearchEngine.buildSearchRequest(
                Index.CV_INDEX,
                request
        );

        return searchInternal(searchRequest);
    }

    private List<Cv> searchInternal (SearchRequest searchRequest){
        if(searchRequest == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Cv> cvList = new ArrayList<>(searchHits.length);

            for(SearchHit hit : searchHits){
                cvList.add(
                        MAPPER.readValue(hit.getSourceAsString(), Cv.class)
                );
            }

            return cvList;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }



    public Boolean index(final Cv cv) {
        try {
            final String cvAsString = MAPPER.writeValueAsString(cv);

            final IndexRequest request = new IndexRequest(Index.CV_INDEX);
            request.id(cv.getId());
            request.source(cvAsString, XContentType.JSON);

            final IndexResponse reponse = client.index(request, RequestOptions.DEFAULT);

            return reponse != null && reponse.status().equals(RestStatus.OK);


        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    public Cv getById(final String id) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(Index.CV_INDEX, id),
                    RequestOptions.DEFAULT
            );
            if (documentFields == null || documentFields.isSourceEmpty())
                return null;

            return MAPPER.readValue(documentFields.getSourceAsString(), Cv.class);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
