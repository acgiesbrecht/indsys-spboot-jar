/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author adriang
 */
@Embeddable
public class TblDalBonificacionValoresPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_bonificacion")
    private int idBonificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fibra_tipo")
    private int idFibraTipo;

    public TblDalBonificacionValoresPK() {
    }

    public TblDalBonificacionValoresPK(int idBonificacion, int idFibraTipo) {
        this.idBonificacion = idBonificacion;
        this.idFibraTipo = idFibraTipo;
    }

    public int getIdBonificacion() {
        return idBonificacion;
    }

    public void setIdBonificacion(int idBonificacion) {
        this.idBonificacion = idBonificacion;
    }

    public int getIdFibraTipo() {
        return idFibraTipo;
    }

    public void setIdFibraTipo(int idFibraTipo) {
        this.idFibraTipo = idFibraTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idBonificacion;
        hash += (int) idFibraTipo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDalBonificacionValoresPK)) {
            return false;
        }
        TblDalBonificacionValoresPK other = (TblDalBonificacionValoresPK) object;
        if (this.idBonificacion != other.idBonificacion) {
            return false;
        }
        if (this.idFibraTipo != other.idFibraTipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.bas.domain.TblDalBonificacionValoresPK[ idBonificacion=" + idBonificacion + ", idFibraTipo=" + idFibraTipo + " ]";
    }

}
