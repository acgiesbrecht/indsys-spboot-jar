/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
 * @author adriang
 */
@Entity
@Table(name = "tbl_dal_bonificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDalBonificaciones.findAll", query = "SELECT t FROM TblDalBonificaciones t"),
    @NamedQuery(name = "TblDalBonificaciones.findById", query = "SELECT t FROM TblDalBonificaciones t WHERE t.id = :id"),
    @NamedQuery(name = "TblDalBonificaciones.findByFechahora", query = "SELECT t FROM TblDalBonificaciones t WHERE t.fechahora = :fechahora")})
public class TblDalBonificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblDalBonificaciones")
    private List<TblDalBonificacionValores> tblDalBonificacionValoresList;

    public TblDalBonificaciones() {
    }

    public TblDalBonificaciones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    @XmlTransient
    public List<TblDalBonificacionValores> getTblDalBonificacionValoresList() {
        return tblDalBonificacionValoresList;
    }

    public void setTblDalBonificacionValoresList(List<TblDalBonificacionValores> tblDalBonificacionValoresList) {
        this.tblDalBonificacionValoresList = tblDalBonificacionValoresList;
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
        if (!(object instanceof TblDalBonificaciones)) {
            return false;
        }
        TblDalBonificaciones other = (TblDalBonificaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //return sdf.format(fechahora);
        return id.toString();
    }

}
