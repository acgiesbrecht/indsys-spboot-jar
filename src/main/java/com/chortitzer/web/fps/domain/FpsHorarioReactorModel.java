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
public class FpsHorarioReactorModel implements Serializable {

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date arranque;
    @Temporal(TemporalType.TIMESTAMP)
    private Date paro;

    private static final long serialVersionUID = 1L;

    public FpsHorarioReactorModel() {
    }

    public FpsHorarioReactorModel(Date arranque, Date paro) {
        this.arranque = arranque;
        this.paro = paro;
    }

    /**
     * @return the arranque
     */
    public Date getArranque() {
        return arranque;
    }

    /**
     * @param arranque the arranque to set
     */
    public void setArranque(Date arranque) {
        this.arranque = arranque;
    }

    /**
     * @return the paro
     */
    public Date getParo() {
        return paro;
    }

    /**
     * @param paro the paro to set
     */
    public void setParo(Date paro) {
        this.paro = paro;
    }

}
