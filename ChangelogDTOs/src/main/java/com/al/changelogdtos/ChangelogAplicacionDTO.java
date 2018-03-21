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
public class ChangelogAplicacionDTO {
    private int id;
    private String aplicacion;
    private String cliente;

    
    public ChangelogAplicacionDTO(int id, String aplicacion, String cliente) {
        this.id = id;
        this.aplicacion = aplicacion;
        this.cliente = cliente;
    }

    
    public ChangelogAplicacionDTO(String aplicacion, String cliente) {
        this.aplicacion = aplicacion;
        this.cliente = cliente;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
