package com.example.psdnote.db.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Item {

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @Generated(hash = 1263279773)
    public Item(Long id, String title, String email, String password) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.password = password;
    }

    public Item(String title, String email, String password) {
        this.title = title;
        this.email = email;
        this.password = password;
    }

    @Generated(hash = 1470900980)
    public Item() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
