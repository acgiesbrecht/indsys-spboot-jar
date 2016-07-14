/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.fps.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Industria
 */
@Entity
public class FpsAvgValueModel  implements Serializable{
    
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    private double valor;

    private static final long serialVersionUID = 1L;
    
    public FpsAvgValueModel() {
    }

    public FpsAvgValueModel(Date fechahora, double valor) {
        this.fechahora = fechahora;
        this.valor = valor;
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
     * @return the value
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param value the value to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}
