/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_dal_lotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDalLotes.findAll", query = "SELECT t FROM TblDalLotes t"),
    @NamedQuery(name = "TblDalLotes.findById", query = "SELECT t FROM TblDalLotes t WHERE t.id = :id"),
    @NamedQuery(name = "TblDalLotes.findByRefLoteProductor", query = "SELECT t FROM TblDalLotes t WHERE t.refLoteProductor = :refLoteProductor"),
    @NamedQuery(name = "TblDalLotes.findByFechahoraApertura", query = "SELECT t FROM TblDalLotes t WHERE t.fechahoraApertura = :fechahoraApertura"),
    @NamedQuery(name = "TblDalLotes.findByPesoSemilla", query = "SELECT t FROM TblDalLotes t WHERE t.pesoSemilla = :pesoSemilla"),
    @NamedQuery(name = "TblDalLotes.findByPesoFibrilla", query = "SELECT t FROM TblDalLotes t WHERE t.pesoFibrilla = :pesoFibrilla"),
    @NamedQuery(name = "TblDalLotes.findByAreaOrigenHa", query = "SELECT t FROM TblDalLotes t WHERE t.areaOrigenHa = :areaOrigenHa"),
    @NamedQuery(name = "TblDalLotes.findByNroLoteAnual", query = "SELECT t FROM TblDalLotes t WHERE t.nroLoteAnual = :nroLoteAnual")})
public class TblDalLotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ref_lote_productor")
    private String refLoteProductor;
    @Column(name = "fechahora_apertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraApertura;
    @Column(name = "peso_semilla")
    private Integer pesoSemilla;
    @Column(name = "peso_fibrilla")
    private Integer pesoFibrilla;
    @Column(name = "area_origen_ha")
    private Integer areaOrigenHa;
    @Column(name = "nro_lote_anual")
    private Integer nroLoteAnual;
    @JoinColumn(name = "id_productor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tblempresa idProductor;

    public TblDalLotes() {
    }

    public TblDalLotes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefLoteProductor() {
        return refLoteProductor;
    }

    public void setRefLoteProductor(String refLoteProductor) {
        this.refLoteProductor = refLoteProductor;
    }

    public Date getFechahoraApertura() {
        return fechahoraApertura;
    }

    public void setFechahoraApertura(Date fechahoraApertura) {
        this.fechahoraApertura = fechahoraApertura;
    }

    public Integer getPesoSemilla() {
        return pesoSemilla;
    }

    public void setPesoSemilla(Integer pesoSemilla) {
        this.pesoSemilla = pesoSemilla;
    }

    public Integer getPesoFibrilla() {
        return pesoFibrilla;
    }

    public void setPesoFibrilla(Integer pesoFibrilla) {
        this.pesoFibrilla = pesoFibrilla;
    }

    public Integer getAreaOrigenHa() {
        return areaOrigenHa;
    }

    public void setAreaOrigenHa(Integer areaOrigenHa) {
        this.areaOrigenHa = areaOrigenHa;
    }

    public Integer getNroLoteAnual() {
        return nroLoteAnual;
    }

    public void setNroLoteAnual(Integer nroLoteAnual) {
        this.nroLoteAnual = nroLoteAnual;
    }

    public Tblempresa getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(Tblempresa idProductor) {
        this.idProductor = idProductor;
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
        if (!(object instanceof TblDalLotes)) {
            return false;
        }
        TblDalLotes other = (TblDalLotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
