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
public class TblDalFardosFibraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_lote")
    private int idLote;

    public TblDalFardosFibraPK() {
    }

    public TblDalFardosFibraPK(int id, int idLote) {
        this.id = id;
        this.idLote = idLote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idLote;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDalFardosFibraPK)) {
            return false;
        }
        TblDalFardosFibraPK other = (TblDalFardosFibraPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idLote != other.idLote) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.bas.domain.TblDalFardosFibraPK[ id=" + id + ", idLote=" + idLote + " ]";
    }

}
