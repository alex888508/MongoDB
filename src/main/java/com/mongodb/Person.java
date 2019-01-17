package com.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "Person")
public class Person {
    @Id
    public String name;

    @Field
    public String Email;
    public String password;
    public String old;

    public Person () {};

    public Person(String name)
    {
        this.name = name;
        this.Email = Email;
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
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
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
}

