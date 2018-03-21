/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.al.changelogdtos;

/**
 *
 * @author jaimejimenez
 */
public class ChangelogVersionDTO {
    private int id;
    private String version;
    private int idAplicacion;

    
    public ChangelogVersionDTO(int id, String version, int idAplicacion) {
        this.id = id;
        this.version = version;
        this.idAplicacion = idAplicacion;
    }

    
    public ChangelogVersionDTO(String version, int idAplicacion) {
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
}
