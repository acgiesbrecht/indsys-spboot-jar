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
@Table(name = "tblempresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblempresa.findAll", query = "SELECT t FROM Tblempresa t"),
    @NamedQuery(name = "Tblempresa.findById", query = "SELECT t FROM Tblempresa t WHERE t.id = :id"),
    @NamedQuery(name = "Tblempresa.findByNombre", query = "SELECT t FROM Tblempresa t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tblempresa.findByDireccion", query = "SELECT t FROM Tblempresa t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Tblempresa.findByTelefono", query = "SELECT t FROM Tblempresa t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Tblempresa.findByCuentacorriente", query = "SELECT t FROM Tblempresa t WHERE t.cuentacorriente = :cuentacorriente"),
    @NamedQuery(name = "Tblempresa.findByRuc", query = "SELECT t FROM Tblempresa t WHERE t.ruc = :ruc")})
public class Tblempresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 45)
    @Column(name = "cuentacorriente")
    private String cuentacorriente;
    @Size(max = 45)
    @Column(name = "ruc")
    private String ruc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProductor")
    private List<TblDalLotes> tblDalLotesList;

    public Tblempresa() {
    }

    public Tblempresa(Integer id) {
        this.id = id;
    }

    public Tblempresa(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuentacorriente() {
        return cuentacorriente;
    }

    public void setCuentacorriente(String cuentacorriente) {
        this.cuentacorriente = cuentacorriente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @XmlTransient
    public List<TblDalLotes> getTblDalLotesList() {
        return tblDalLotesList;
    }

    public void setTblDalLotesList(List<TblDalLotes> tblDalLotesList) {
        this.tblDalLotesList = tblDalLotesList;
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
        if (!(object instanceof Tblempresa)) {
            return false;
        }
        Tblempresa other = (Tblempresa) object;
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
