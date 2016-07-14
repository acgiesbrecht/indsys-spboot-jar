/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Industria
 */
@Entity
public class DalLiquidacionModel implements Serializable {

    @Id
    private Integer id;
    private Date fechahora;
    private Integer peso;
    private Double pesoanticipo;
    private Double pago;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the fechahora
     */
    public Date getFechahora() {
        return fechahora;
    }

    /**
     * @param fechahora the fechahora to set
     */
    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
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
     * @return the pesoanticipo
     */
    public Double getPesoanticipo() {
        return pesoanticipo;
    }

    /**
     * @param pesoanticipo the pesoanticipo to set
     */
    public void setPesoanticipo(Double pesoanticipo) {
        this.pesoanticipo = pesoanticipo;
    }

    /**
     * @return the pago
     */
    public Double getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(Double pago) {
        this.pago = pago;
    }

}
