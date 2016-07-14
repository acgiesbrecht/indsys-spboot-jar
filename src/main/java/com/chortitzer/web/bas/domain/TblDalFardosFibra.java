/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_dal_fardos_fibra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDalFardosFibra.findAll", query = "SELECT t FROM TblDalFardosFibra t"),
    @NamedQuery(name = "TblDalFardosFibra.findById", query = "SELECT t FROM TblDalFardosFibra t WHERE t.tblDalFardosFibraPK.id = :id"),
    @NamedQuery(name = "TblDalFardosFibra.findByIdLote", query = "SELECT t FROM TblDalFardosFibra t WHERE t.tblDalFardosFibraPK.idLote = :idLote"),
    @NamedQuery(name = "TblDalFardosFibra.findByFechahora", query = "SELECT t FROM TblDalFardosFibra t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblDalFardosFibra.findByPeso", query = "SELECT t FROM TblDalFardosFibra t WHERE t.peso = :peso"),
    @NamedQuery(name = "TblDalFardosFibra.findByMicronaire", query = "SELECT t FROM TblDalFardosFibra t WHERE t.micronaire = :micronaire"),
    @NamedQuery(name = "TblDalFardosFibra.findByFermentado", query = "SELECT t FROM TblDalFardosFibra t WHERE t.fermentado = :fermentado"),
    @NamedQuery(name = "TblDalFardosFibra.findByFardoVerdadero", query = "SELECT t FROM TblDalFardosFibra t WHERE t.fardoVerdadero = :fardoVerdadero")})
public class TblDalFardosFibra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblDalFardosFibraPK tblDalFardosFibraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private int peso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "micronaire")
    private BigDecimal micronaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fermentado")
    private short fermentado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fardo_verdadero")
    private short fardoVerdadero;
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblDalFibraTipos tipo;
    @JoinColumn(name = "id_lote", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblDalLotes tblDalLotes;

    public TblDalFardosFibra() {
    }

    public TblDalFardosFibra(TblDalFardosFibraPK tblDalFardosFibraPK) {
        this.tblDalFardosFibraPK = tblDalFardosFibraPK;
    }

    public TblDalFardosFibra(TblDalFardosFibraPK tblDalFardosFibraPK, Date fechahora, int peso, BigDecimal micronaire, short fermentado, short fardoVerdadero) {
        this.tblDalFardosFibraPK = tblDalFardosFibraPK;
        this.fechahora = fechahora;
        this.peso = peso;
        this.micronaire = micronaire;
        this.fermentado = fermentado;
        this.fardoVerdadero = fardoVerdadero;
    }

    public TblDalFardosFibra(int id, int idLote) {
        this.tblDalFardosFibraPK = new TblDalFardosFibraPK(id, idLote);
    }

    public TblDalFardosFibraPK getTblDalFardosFibraPK() {
        return tblDalFardosFibraPK;
    }

    public void setTblDalFardosFibraPK(TblDalFardosFibraPK tblDalFardosFibraPK) {
        this.tblDalFardosFibraPK = tblDalFardosFibraPK;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public BigDecimal getMicronaire() {
        return micronaire;
    }

    public void setMicronaire(BigDecimal micronaire) {
        this.micronaire = micronaire;
    }

    public short getFermentado() {
        return fermentado;
    }

    public void setFermentado(short fermentado) {
        this.fermentado = fermentado;
    }

    public short getFardoVerdadero() {
        return fardoVerdadero;
    }

    public void setFardoVerdadero(short fardoVerdadero) {
        this.fardoVerdadero = fardoVerdadero;
    }

    public TblDalFibraTipos getTipo() {
        return tipo;
    }

    public void setTipo(TblDalFibraTipos tipo) {
        this.tipo = tipo;
    }

    public TblDalLotes getTblDalLotes() {
        return tblDalLotes;
    }

    public void setTblDalLotes(TblDalLotes tblDalLotes) {
        this.tblDalLotes = tblDalLotes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblDalFardosFibraPK != null ? tblDalFardosFibraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDalFardosFibra)) {
            return false;
        }
        TblDalFardosFibra other = (TblDalFardosFibra) object;
        if ((this.tblDalFardosFibraPK == null && other.tblDalFardosFibraPK != null) || (this.tblDalFardosFibraPK != null && !this.tblDalFardosFibraPK.equals(other.tblDalFardosFibraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.bas.domain.TblDalFardosFibra[ tblDalFardosFibraPK=" + tblDalFardosFibraPK + " ]";
    }

}
