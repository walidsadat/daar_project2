package com.daar.elasticsearch.model;

import java.io.InputStream;
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
    @Field(type = FieldType.Text, name = "birthDate")
    private String birthDate;
    @Field(type = FieldType.Nested, name = "experiences")
    private ArrayList<String> experiences;
    @Field(type = FieldType.Nested, name = "etudes")
    private ArrayList<String> etudes;
    @Field(type = FieldType.Nested, name = "competences")
    private ArrayList<String> competences;

    public Cv(String name, String birthDate, ArrayList<String> experiences, ArrayList<String> etudes,
              ArrayList<String> competences) {
        this.id = generate_id();
        this.name = name;
        this.birthDate = birthDate;
        this.experiences = experiences;
        this.etudes = etudes;
        this.competences = competences;
    }

    public Cv(InputStream input) {

        Scanner scanner;
        try {
            scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitted = line.split(":");
                if (splitted.length > 1)
                    fill(splitted);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return this.id;
    }

    private void fill(String[] splitted) {
        if (splitted[0].contains("name"))
            this.name = splitted[1];
        else if (splitted[0].contains("birth"))
            this.birthDate = splitted[1];
        else if (splitted[0].contains("experience")) {
            String[] xps = splitted[1].split(";");
            this.experiences.addAll(Arrays.asList(xps));
        } else if (splitted[0].contains("etude")) {
            String[] studies = splitted[1].split(";");
            this.experiences.addAll(Arrays.asList(studies));
        } else if (splitted[0].contains("competence")) {
            String[] cmpts = splitted[1].split(";");
            this.competences.addAll(Arrays.asList(cmpts));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<String> getExperiences() {
        return experiences;
    }

    public void setExperiences(ArrayList<String> experiences) {
        this.experiences = experiences;
    }

    public ArrayList<String> getEtudes() {
        return etudes;
    }

    public void setEtudes(ArrayList<String> etudes) {
        this.etudes = etudes;
    }

    public ArrayList<String> getCompetences() {
        return competences;
    }

    public void setCompetences(ArrayList<String> competences) {
        this.competences = competences;
    }

    public String toString() {
        String str = "{\n   Name : " + this.name;
        str += "\n   Birth Date : " + this.birthDate + "\n   Experiences : " + this.experiences.toString();
        str += "\n   Etudes : " + this.etudes.toString() + "\n   competences : " + this.competences.toString();
        str += "\n}";
        return str;
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

