package com.mongodb.Person;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "Phonebook")
public class Phonebook {
    @Id
    private String id;

    private String date;

    @Indexed(unique = true)
    @Field
    private String numer;

    private String name;
    private String info;

    public Phonebook () {}

    public Phonebook(String id, String date, String numer, String name, String info) {
        this.id = id;
        this.date = date;
        this.numer = numer;
        this.name = name;
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
