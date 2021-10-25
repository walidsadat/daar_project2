package com.daar.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Cv {
    private String id;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

    //empty constructor to avoid JacksonMappingException problems when we try to deserialize JSon to Cv https://www.baeldung.com/jackson-exception
    public Cv(){
        super();
    }

    public Cv(String id, String content){
        this.id=id;
        this.content = content;
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

