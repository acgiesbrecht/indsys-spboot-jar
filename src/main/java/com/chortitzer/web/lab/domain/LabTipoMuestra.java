/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.lab.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "lab_tipo_muestra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabTipoMuestra.findAll", query = "SELECT l FROM LabTipoMuestra l"),
    @NamedQuery(name = "LabTipoMuestra.findById", query = "SELECT l FROM LabTipoMuestra l WHERE l.labTipoMuestraPK.id = :id"),
    @NamedQuery(name = "LabTipoMuestra.findByDescripcion", query = "SELECT l FROM LabTipoMuestra l WHERE l.labTipoMuestraPK.descripcion = :descripcion")})
public class LabTipoMuestra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LabTipoMuestraPK labTipoMuestraPK;

    public LabTipoMuestra() {
    }

    public LabTipoMuestra(LabTipoMuestraPK labTipoMuestraPK) {
        this.labTipoMuestraPK = labTipoMuestraPK;
    }

    public LabTipoMuestra(int id, String descripcion) {
        this.labTipoMuestraPK = new LabTipoMuestraPK(id, descripcion);
    }

    public LabTipoMuestraPK getLabTipoMuestraPK() {
        return labTipoMuestraPK;
    }

    public void setLabTipoMuestraPK(LabTipoMuestraPK labTipoMuestraPK) {
        this.labTipoMuestraPK = labTipoMuestraPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labTipoMuestraPK != null ? labTipoMuestraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabTipoMuestra)) {
            return false;
        }
        LabTipoMuestra other = (LabTipoMuestra) object;
        if ((this.labTipoMuestraPK == null && other.labTipoMuestraPK != null) || (this.labTipoMuestraPK != null && !this.labTipoMuestraPK.equals(other.labTipoMuestraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.lab.LabTipoMuestra[ labTipoMuestraPK=" + labTipoMuestraPK + " ]";
    }
    
}
