/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.lab.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "lab_resultados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabResultados.findAll", query = "SELECT l FROM LabResultados l"),
    @NamedQuery(name = "LabResultados.findByIdMuestra", query = "SELECT l FROM LabResultados l WHERE l.labResultadosPK.idMuestra = :idMuestra"),
    @NamedQuery(name = "LabResultados.findByIdTipoAnalisis", query = "SELECT l FROM LabResultados l WHERE l.labResultadosPK.idTipoAnalisis = :idTipoAnalisis"),
    @NamedQuery(name = "LabResultados.findByValor", query = "SELECT l FROM LabResultados l WHERE l.valor = :valor"),
    @NamedQuery(name = "LabResultados.findByCosto", query = "SELECT l FROM LabResultados l WHERE l.costo = :costo")})
public class LabResultados implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LabResultadosPK labResultadosPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "costo")
    private int costo;
    @JoinColumn(name = "id_muestra", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LabMuestras labMuestras;
    @JoinColumn(name = "id_tipo_analisis", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LabTipoAnalisis labTipoAnalisis;

    public LabResultados() {
    }

    public LabResultados(LabResultadosPK labResultadosPK) {
        this.labResultadosPK = labResultadosPK;
    }

    public LabResultados(LabResultadosPK labResultadosPK, BigDecimal valor, int costo) {
        this.labResultadosPK = labResultadosPK;
        this.valor = valor;
        this.costo = costo;
    }

    public LabResultados(int idMuestra, int idTipoAnalisis) {
        this.labResultadosPK = new LabResultadosPK(idMuestra, idTipoAnalisis);
    }

    public LabResultadosPK getLabResultadosPK() {
        return labResultadosPK;
    }

    public void setLabResultadosPK(LabResultadosPK labResultadosPK) {
        this.labResultadosPK = labResultadosPK;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public LabMuestras getLabMuestras() {
        return labMuestras;
    }

    public void setLabMuestras(LabMuestras labMuestras) {
        this.labMuestras = labMuestras;
    }

    public LabTipoAnalisis getLabTipoAnalisis() {
        return labTipoAnalisis;
    }

    public void setLabTipoAnalisis(LabTipoAnalisis labTipoAnalisis) {
        this.labTipoAnalisis = labTipoAnalisis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labResultadosPK != null ? labResultadosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabResultados)) {
            return false;
        }
        LabResultados other = (LabResultados) object;
        if ((this.labResultadosPK == null && other.labResultadosPK != null) || (this.labResultadosPK != null && !this.labResultadosPK.equals(other.labResultadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.lab.LabResultados[ labResultadosPK=" + labResultadosPK + " ]";
    }
    
}
