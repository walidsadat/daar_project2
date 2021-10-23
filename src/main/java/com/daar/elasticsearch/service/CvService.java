package com.daar.elasticsearch.service;

import com.daar.elasticsearch.indices.Index;
import com.daar.elasticsearch.model.Cv;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CvService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);

    private final RestHighLevelClient client;

    @Autowired
    public CvService(RestHighLevelClient client) {
        this.client = client;
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
