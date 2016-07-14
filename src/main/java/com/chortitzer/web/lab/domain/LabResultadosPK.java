/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.lab.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Industria
 */
@Embeddable
public class LabResultadosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_muestra")
    private int idMuestra;
    @Basic(optional = false)
    @Column(name = "id_tipo_analisis")
    private int idTipoAnalisis;

    public LabResultadosPK() {
    }

    public LabResultadosPK(int idMuestra, int idTipoAnalisis) {
        this.idMuestra = idMuestra;
        this.idTipoAnalisis = idTipoAnalisis;
    }

    public int getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(int idMuestra) {
        this.idMuestra = idMuestra;
    }

    public int getIdTipoAnalisis() {
        return idTipoAnalisis;
    }

    public void setIdTipoAnalisis(int idTipoAnalisis) {
        this.idTipoAnalisis = idTipoAnalisis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMuestra;
        hash += (int) idTipoAnalisis;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabResultadosPK)) {
            return false;
        }
        LabResultadosPK other = (LabResultadosPK) object;
        if (this.idMuestra != other.idMuestra) {
            return false;
        }
        if (this.idTipoAnalisis != other.idTipoAnalisis) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.lab.LabResultadosPK[ idMuestra=" + idMuestra + ", idTipoAnalisis=" + idTipoAnalisis + " ]";
    }
    
}
