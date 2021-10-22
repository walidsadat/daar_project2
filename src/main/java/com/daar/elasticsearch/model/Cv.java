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
        this.id = generate_id();
        this.name=name;
        this.content = content;

    }
    public Cv(String id, String name, String content){
        this.id = id;
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

    private String generate_id() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            str.append(ID_GENERATOR.charAt((int) Math.floor(Math.random() * 36)));
        }
        while (ids.contains(str.toString()))
            str = new StringBuilder(generate_id());
        ids.add(str.toString());
        return str.toString();
    }

}

