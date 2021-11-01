package com.daar.elasticsearch.service;

import com.daar.elasticsearch.indices.Helper;
import com.daar.elasticsearch.indices.Index;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class IndexService {
   private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);

   private static final List<String> INDICES_TO_CREATE = List.of(Index.CV_INDEX);

   private final RestHighLevelClient client;


   @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndices(){
       recreateIndices(false);
    }

    public void recreateIndices(final boolean deleteExisting){
        final String settings = Helper.loadAsString("static/es-settings.json");

        if(settings == null){
            LOG.error("Failed to load index settings");
            return;
        }

        for(final String indexName : INDICES_TO_CREATE){
            try {
                // first, let's check if the index exist
                boolean indexExists = client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if(indexExists){
                    if(!deleteExisting)
                        continue;

                    client.indices().delete(
                            new DeleteIndexRequest(indexName),
                            RequestOptions.DEFAULT
                    );
                }
                // at this point, surely the index is not created yet, let's do it
                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);

                final String mappings = loadMappings(indexName);

                if(mappings != null)
                     createIndexRequest.mapping(mappings, XContentType.JSON);

                client.indices().create(createIndexRequest , RequestOptions.DEFAULT);
            } catch (final Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private String loadMappings(String indexName){
       final String mappings = Helper.loadAsString("static/mappings/" + indexName + ".json");
       if(mappings == null){
           LOG.error("Failed to load mappings for index : '{}'", indexName);
           return null;
       }
       return mappings;
    }
}
