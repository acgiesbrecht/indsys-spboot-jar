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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tblproductos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductos.findAll", query = "SELECT t FROM Tblproductos t"),
    @NamedQuery(name = "Tblproductos.findById", query = "SELECT t FROM Tblproductos t WHERE t.id = :id"),
    @NamedQuery(name = "Tblproductos.findByDescripcion", query = "SELECT t FROM Tblproductos t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tblproductos.findByMateriaprima", query = "SELECT t FROM Tblproductos t WHERE t.materiaprima = :materiaprima")})
public class Tblproductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "materiaprima")
    private Integer materiaprima;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoid")
    private List<Tblpesadas> tblpesadasList;

    public Tblproductos() {
    }

    public Tblproductos(Integer id) {
        this.id = id;
    }

    public Tblproductos(Integer id, String descripcion) {
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

    public Integer getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Integer materiaprima) {
        this.materiaprima = materiaprima;
    }

    @XmlTransient
    public List<Tblpesadas> getTblpesadasList() {
        return tblpesadasList;
    }

    public void setTblpesadasList(List<Tblpesadas> tblpesadasList) {
        this.tblpesadasList = tblpesadasList;
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
        if (!(object instanceof Tblproductos)) {
            return false;
        }
        Tblproductos other = (Tblproductos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.bas.domain.Tblproductos[ id=" + id + " ]";
    }

}
