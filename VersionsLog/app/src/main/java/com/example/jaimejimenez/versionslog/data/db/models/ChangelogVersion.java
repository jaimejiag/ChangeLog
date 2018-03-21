package com.example.jaimejimenez.versionslog.data.db.models;

/**
 * Created by jaimejimenez on 15/03/18.
 */

public class ChangelogVersion {
    private int id;
    private String version;
    private int idAplicacion;


    public ChangelogVersion(int id, String version, int idAplicacion) {
        this.id = id;
        this.version = version;
        this.idAplicacion = idAplicacion;
    }


    public ChangelogVersion(String version, int idAplicacion) {
        this.version = version;
        this.idAplicacion = idAplicacion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
    }


    @Override
    public String toString() {
        return version;
    }
}
