package com.riyan.rampcheck.Model;

public class Credential {
    private String id_user,username,nama;

    public Credential(String id_user, String username, String nama) {
        this.id_user = id_user;
        this.username = username;
        this.nama = nama;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}