package com.mongodb.Person;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Person")
public class Person {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String old;

    public Person(String name, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.old = old;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getOld() {
        return this.old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", old='" + old + '\'' +
                '}';
    }
}

