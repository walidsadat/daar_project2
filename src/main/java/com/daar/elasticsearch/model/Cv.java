package com.daar.elasticsearch.model;

import java.util.*;

import com.daar.elasticsearch.indices.Index;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import org.springframework.data.elasticsearch.annotations.Setting;


@Document(indexName = Index.CV_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Cv {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Text, name = "content")
    private String content;

    public Cv(String name, String content){
        this.name=name;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    private final String ID_GENERATOR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static ArrayList<String> ids = new ArrayList<>();


}

