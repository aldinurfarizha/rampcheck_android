package com.riyan.rampcheck.Model;


public class ModelSopir {
    String id_sopir,nama_sopir,tgl_lahir,telepon,alamat,umur,nomor, id_bus;

    public ModelSopir(String id_sopir, String nama_sopir, String tgl_lahir, String telepon, String alamat, String umur, String nomor, String id_bus) {
        this.id_sopir = id_sopir;
        this.nama_sopir = nama_sopir;
        this.tgl_lahir = tgl_lahir;
        this.telepon = telepon;
        this.alamat = alamat;
        this.umur = umur;
        this.nomor = nomor;
        this.id_bus = id_bus;
    }

    public String getId_sopir() {
        return id_sopir;
    }

    public void setId_sopir(String id_sopir) {
        this.id_sopir = id_sopir;
    }

    public String getNama_sopir() {
        return nama_sopir;
    }

    public void setNama_sopir(String nama_sopir) {
        this.nama_sopir = nama_sopir;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getId_bus() {
        return id_bus;
    }

    public void setId_bus(String id_bus) {
        this.id_bus = id_bus;
    }
}
