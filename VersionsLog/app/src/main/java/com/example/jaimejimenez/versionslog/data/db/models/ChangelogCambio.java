package com.example.jaimejimenez.versionslog.data.db.models;

/**
 * Created by jaimejimenez on 15/03/18.
 */

public class ChangelogCambio {
    private int id;
    private int idVersion;
    private String cambio;
    private int idAplicacion;


    public ChangelogCambio() {
        id = 0;
        idVersion = 0;
        cambio = null;
        idAplicacion = 0;
    }


    public ChangelogCambio(int id, int idVersion, String cambio, int idAplicacion) {
        this.id = id;
        this.idVersion = idVersion;
        this.cambio = cambio;
        this.idAplicacion = idAplicacion;
    }


    public ChangelogCambio(int idVersion, String cambio, int idAplicacion) {
        this.idVersion = idVersion;
        this.cambio = cambio;
        this.idAplicacion = idAplicacion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public int getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
    }
}
