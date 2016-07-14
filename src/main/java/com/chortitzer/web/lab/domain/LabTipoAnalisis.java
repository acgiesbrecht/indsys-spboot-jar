/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.lab.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "lab_tipo_analisis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabTipoAnalisis.findAll", query = "SELECT l FROM LabTipoAnalisis l"),
    @NamedQuery(name = "LabTipoAnalisis.findById", query = "SELECT l FROM LabTipoAnalisis l WHERE l.id = :id"),
    @NamedQuery(name = "LabTipoAnalisis.findByDescripcion", query = "SELECT l FROM LabTipoAnalisis l WHERE l.descripcion = :descripcion"),
    @NamedQuery(name = "LabTipoAnalisis.findByUnidadDeMedida", query = "SELECT l FROM LabTipoAnalisis l WHERE l.unidadDeMedida = :unidadDeMedida"),
    @NamedQuery(name = "LabTipoAnalisis.findByCosto", query = "SELECT l FROM LabTipoAnalisis l WHERE l.costo = :costo"),
    @NamedQuery(name = "LabTipoAnalisis.findByCantidadDecimales", query = "SELECT l FROM LabTipoAnalisis l WHERE l.cantidadDecimales = :cantidadDecimales")})
public class LabTipoAnalisis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "UNIDAD_DE_MEDIDA")
    private String unidadDeMedida;
    @Basic(optional = false)
    @Column(name = "COSTO")
    private int costo;
    @Basic(optional = false)
    @Column(name = "CANTIDAD_DECIMALES")
    private int cantidadDecimales;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "labTipoAnalisis")
    private Collection<LabResultados> labResultadosCollection;

    public LabTipoAnalisis() {
    }

    public LabTipoAnalisis(Integer id) {
        this.id = id;
    }

    public LabTipoAnalisis(Integer id, String descripcion, String unidadDeMedida, int costo, int cantidadDecimales) {
        this.id = id;
        this.descripcion = descripcion;
        this.unidadDeMedida = unidadDeMedida;
        this.costo = costo;
        this.cantidadDecimales = cantidadDecimales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getCantidadDecimales() {
        return cantidadDecimales;
    }

    public void setCantidadDecimales(int cantidadDecimales) {
        this.cantidadDecimales = cantidadDecimales;
    }

    @XmlTransient
    public Collection<LabResultados> getLabResultadosCollection() {
        return labResultadosCollection;
    }

    public void setLabResultadosCollection(Collection<LabResultados> labResultadosCollection) {
        this.labResultadosCollection = labResultadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabTipoAnalisis)) {
            return false;
        }
        LabTipoAnalisis other = (LabTipoAnalisis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.lab.LabTipoAnalisis[ id=" + id + " ]";
    }
    
}
