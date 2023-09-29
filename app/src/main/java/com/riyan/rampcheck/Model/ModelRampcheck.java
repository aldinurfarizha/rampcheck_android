package com.riyan.rampcheck.Model;

public class ModelRampcheck {
    String id_rampcheck, tanggal, bus, sopir, penguji, trayek, status, nomor;

    public ModelRampcheck(String id_rampcheck, String tanggal, String bus, String sopir, String penguji, String trayek, String status, String nomor) {
        this.id_rampcheck = id_rampcheck;
        this.tanggal = tanggal;
        this.bus = bus;
        this.sopir = sopir;
        this.penguji = penguji;
        this.trayek = trayek;
        this.status = status;
        this.nomor = nomor;
    }

    public String getId_rampcheck() {
        return id_rampcheck;
    }

    public void setId_rampcheck(String id_rampcheck) {
        this.id_rampcheck = id_rampcheck;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getSopir() {
        return sopir;
    }

    public void setSopir(String sopir) {
        this.sopir = sopir;
    }

    public String getPenguji() {
        return penguji;
    }

    public void setPenguji(String penguji) {
        this.penguji = penguji;
    }

    public String getTrayek() {
        return trayek;
    }

    public void setTrayek(String trayek) {
        this.trayek = trayek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }
}
