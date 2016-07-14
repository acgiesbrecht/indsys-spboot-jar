/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Industria
 */
@Entity
public class DalResumenFibraModel implements Serializable {
    @Id
    private String tipo;
    private String micronaire;
    private Integer cantidad;
    private Integer peso;
    private Integer porcentaje;

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the micronaire
     */
    public String getMicronaire() {
        return micronaire;
    }

    /**
     * @param micronaire the micronaire to set
     */
    public void setMicronaire(String micronaire) {
        this.micronaire = micronaire;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the peso
     */
    public Integer getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    /**
     * @return the porcentaje
     */
    public Integer getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }
}
