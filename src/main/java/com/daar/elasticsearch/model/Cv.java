package com.daar.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class Cv {
    private UUID idGen;

    private static Integer cpt = 0;
    private final String id;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

    //empty constructor to avoid JacksonMappingException problems when we try to deserialize JSon to Cv https://www.baeldung.com/jackson-exception
    public Cv(){
        super();
        idGen = UUID.randomUUID();
        cpt++;
        this.id=idGen.toString();
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
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
}

