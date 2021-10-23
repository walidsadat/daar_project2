package com.daar.elasticsearch.model;

public class Cv {
    private String id;
    private String content;

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

}

