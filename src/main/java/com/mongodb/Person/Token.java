package com.mongodb.Person;

import java.lang.reflect.Array;

public class Token {

    private Integer code = 20000;
    private String roles = "editor";
    private String token = "admin";

    public Token() {
        this.code = code;
        this.data = roles;
        this.name = name;
        this.avatar = avatar;
        this.token = token;
    }

    private String data;
    private String name = "editor";
    private String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
