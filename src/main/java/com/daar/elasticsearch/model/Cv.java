package com.daar.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cv {

    private final String id;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String created;

    //empty constructor to avoid JacksonMappingException problems when we try to deserialize JSon to Cv https://www.baeldung.com/jackson-exception
    public Cv(){
        super();
        this.id= UUID.randomUUID().toString();
        this.created = LocalDateTime.now().toString();
    }

    public String getId() {
        return this.id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
}

