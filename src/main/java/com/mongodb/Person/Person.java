package com.mongodb.Person;

import netscape.security.Privilege;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Set;

@Entity
@Document(collection = "Person")
public class Person implements UserDetails {
    @Id
    private Long id;

    @Field
    public String name;
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

    private Set<Privilege> privileges;

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

