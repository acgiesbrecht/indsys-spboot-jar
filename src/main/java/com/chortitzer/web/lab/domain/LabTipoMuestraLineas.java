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
@Table(name = "lab_tipo_muestra_lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabTipoMuestraLineas.findAll", query = "SELECT l FROM LabTipoMuestraLineas l"),
    @NamedQuery(name = "LabTipoMuestraLineas.findById", query = "SELECT l FROM LabTipoMuestraLineas l WHERE l.labTipoMuestraLineasPK.id = :id"),
    @NamedQuery(name = "LabTipoMuestraLineas.findByDescripcion", query = "SELECT l FROM LabTipoMuestraLineas l WHERE l.labTipoMuestraLineasPK.descripcion = :descripcion")})
public class LabTipoMuestraLineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LabTipoMuestraLineasPK labTipoMuestraLineasPK;

    public LabTipoMuestraLineas() {
    }

    public LabTipoMuestraLineas(LabTipoMuestraLineasPK labTipoMuestraLineasPK) {
        this.labTipoMuestraLineasPK = labTipoMuestraLineasPK;
    }

    public LabTipoMuestraLineas(int id, String descripcion) {
        this.labTipoMuestraLineasPK = new LabTipoMuestraLineasPK(id, descripcion);
    }

    public LabTipoMuestraLineasPK getLabTipoMuestraLineasPK() {
        return labTipoMuestraLineasPK;
    }

    public void setLabTipoMuestraLineasPK(LabTipoMuestraLineasPK labTipoMuestraLineasPK) {
        this.labTipoMuestraLineasPK = labTipoMuestraLineasPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labTipoMuestraLineasPK != null ? labTipoMuestraLineasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabTipoMuestraLineas)) {
            return false;
        }
        LabTipoMuestraLineas other = (LabTipoMuestraLineas) object;
        if ((this.labTipoMuestraLineasPK == null && other.labTipoMuestraLineasPK != null) || (this.labTipoMuestraLineasPK != null && !this.labTipoMuestraLineasPK.equals(other.labTipoMuestraLineasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.lab.LabTipoMuestraLineas[ labTipoMuestraLineasPK=" + labTipoMuestraLineasPK + " ]";
    }
    
}
