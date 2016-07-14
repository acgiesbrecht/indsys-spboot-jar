/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_dal_fibra_tipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDalFibraTipos.findAll", query = "SELECT t FROM TblDalFibraTipos t"),
    @NamedQuery(name = "TblDalFibraTipos.findById", query = "SELECT t FROM TblDalFibraTipos t WHERE t.id = :id"),
    @NamedQuery(name = "TblDalFibraTipos.findByDescripcion", query = "SELECT t FROM TblDalFibraTipos t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TblDalFibraTipos.findByBonificacion2012", query = "SELECT t FROM TblDalFibraTipos t WHERE t.bonificacion2012 = :bonificacion2012"),
    @NamedQuery(name = "TblDalFibraTipos.findByBonificacion2013", query = "SELECT t FROM TblDalFibraTipos t WHERE t.bonificacion2013 = :bonificacion2013"),
    @NamedQuery(name = "TblDalFibraTipos.findByBonificacion2014", query = "SELECT t FROM TblDalFibraTipos t WHERE t.bonificacion2014 = :bonificacion2014"),
    @NamedQuery(name = "TblDalFibraTipos.findByBonificacion2015", query = "SELECT t FROM TblDalFibraTipos t WHERE t.bonificacion2015 = :bonificacion2015")})
public class TblDalFibraTipos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "bonificacion_2012")
    private Integer bonificacion2012;
    @Column(name = "bonificacion_2013")
    private Integer bonificacion2013;
    @Column(name = "bonificacion_2014")
    private Integer bonificacion2014;
    @Column(name = "bonificacion_2015")
    private Integer bonificacion2015;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblDalFibraTipos")
    private List<TblDalBonificacionValores> tblDalBonificacionValoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo")
    private List<TblDalFardosFibra> tblDalFardosFibraList;

    public TblDalFibraTipos() {
    }

    public TblDalFibraTipos(Integer id) {
        this.id = id;
    }

    public TblDalFibraTipos(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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

    public Integer getBonificacion2012() {
        return bonificacion2012;
    }

    public void setBonificacion2012(Integer bonificacion2012) {
        this.bonificacion2012 = bonificacion2012;
    }

    public Integer getBonificacion2013() {
        return bonificacion2013;
    }

    public void setBonificacion2013(Integer bonificacion2013) {
        this.bonificacion2013 = bonificacion2013;
    }

    public Integer getBonificacion2014() {
        return bonificacion2014;
    }

    public void setBonificacion2014(Integer bonificacion2014) {
        this.bonificacion2014 = bonificacion2014;
    }

    public Integer getBonificacion2015() {
        return bonificacion2015;
    }

    public void setBonificacion2015(Integer bonificacion2015) {
        this.bonificacion2015 = bonificacion2015;
    }

    @XmlTransient
    public List<TblDalBonificacionValores> getTblDalBonificacionValoresList() {
        return tblDalBonificacionValoresList;
    }

    public void setTblDalBonificacionValoresList(List<TblDalBonificacionValores> tblDalBonificacionValoresList) {
        this.tblDalBonificacionValoresList = tblDalBonificacionValoresList;
    }

    @XmlTransient
    public List<TblDalFardosFibra> getTblDalFardosFibraList() {
        return tblDalFardosFibraList;
    }

    public void setTblDalFardosFibraList(List<TblDalFardosFibra> tblDalFardosFibraList) {
        this.tblDalFardosFibraList = tblDalFardosFibraList;
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
        if (!(object instanceof TblDalFibraTipos)) {
            return false;
        }
        TblDalFibraTipos other = (TblDalFibraTipos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.bas.domain.TblDalFibraTipos[ id=" + id + " ]";
    }

}
