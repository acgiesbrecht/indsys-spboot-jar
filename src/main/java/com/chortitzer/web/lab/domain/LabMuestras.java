/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.lab.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "lab_muestras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabMuestras.findAll", query = "SELECT l FROM LabMuestras l"),
    @NamedQuery(name = "LabMuestras.findById", query = "SELECT l FROM LabMuestras l WHERE l.id = :id"),
    @NamedQuery(name = "LabMuestras.findByIdCliente", query = "SELECT l FROM LabMuestras l WHERE l.idCliente = :idCliente"),
    @NamedQuery(name = "LabMuestras.findByIdDptoChortitzer", query = "SELECT l FROM LabMuestras l WHERE l.idDptoChortitzer = :idDptoChortitzer"),
    @NamedQuery(name = "LabMuestras.findByIdTipoMuestra", query = "SELECT l FROM LabMuestras l WHERE l.idTipoMuestra = :idTipoMuestra"),
    @NamedQuery(name = "LabMuestras.findByFechaEntrada", query = "SELECT l FROM LabMuestras l WHERE l.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "LabMuestras.findByObservaciones", query = "SELECT l FROM LabMuestras l WHERE l.observaciones = :observaciones"),
    @NamedQuery(name = "LabMuestras.findByFechaAnalisis", query = "SELECT l FROM LabMuestras l WHERE l.fechaAnalisis = :fechaAnalisis")})
public class LabMuestras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private int idCliente;
    @Column(name = "ID_DPTO_CHORTITZER")
    private Integer idDptoChortitzer;
    @Basic(optional = false)
    @Column(name = "ID_TIPO_MUESTRA")
    private int idTipoMuestra;
    @Basic(optional = false)
    @Column(name = "FECHA_ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "FECHA_ANALISIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnalisis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "labMuestras")
    private Collection<LabResultados> labResultadosCollection;

    public LabMuestras() {
    }

    public LabMuestras(Integer id) {
        this.id = id;
    }

    public LabMuestras(Integer id, int idCliente, int idTipoMuestra, Date fechaEntrada, Date fechaAnalisis) {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoMuestra = idTipoMuestra;
        this.fechaEntrada = fechaEntrada;
        this.fechaAnalisis = fechaAnalisis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdDptoChortitzer() {
        return idDptoChortitzer;
    }

    public void setIdDptoChortitzer(Integer idDptoChortitzer) {
        this.idDptoChortitzer = idDptoChortitzer;
    }

    public int getIdTipoMuestra() {
        return idTipoMuestra;
    }

    public void setIdTipoMuestra(int idTipoMuestra) {
        this.idTipoMuestra = idTipoMuestra;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(Date fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
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
        if (!(object instanceof LabMuestras)) {
            return false;
        }
        LabMuestras other = (LabMuestras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.lab.LabMuestras[ id=" + id + " ]";
    }
    
}
