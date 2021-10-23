package com.daar.elasticsearch.service;

import com.daar.elasticsearch.indices.Helper;
import com.daar.elasticsearch.indices.Index;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.daar.elasticsearch.indices.Helper.LOG;

@Service
public class IndexService {
   private final List<String> INDICES_TO_CREATE = List.of(Index.CV_INDEX);
   private final RestHighLevelClient client;


   @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndices(){
        for(final String indexName : INDICES_TO_CREATE){
            try {
                // first, let's check if the indice exists
                boolean indexExists = client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if(indexExists){
                    continue;
                }
                // at this point, surely the index is not created yet, let's do it
                final String mappings = Helper.loadAsString("static/mappings/" + indexName + ".json");
                final String settings = Helper.loadAsString("static/es-settings.json");

                if(settings == null || mappings == null){
                    LOG.error("Failed to create index with name '{}' ", indexName);
                    continue;
                }

                //creation of the index
                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);

                createIndexRequest.settings(settings, XContentType.JSON);
                createIndexRequest.mapping(mappings, XContentType.JSON);

                client.indices().create(createIndexRequest , RequestOptions.DEFAULT);

            } catch (final Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
