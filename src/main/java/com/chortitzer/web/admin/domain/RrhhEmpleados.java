/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "rrhh_empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhEmpleados.findAll", query = "SELECT r FROM RrhhEmpleados r"),
    @NamedQuery(name = "RrhhEmpleados.findById", query = "SELECT r FROM RrhhEmpleados r WHERE r.id = :id"),
    @NamedQuery(name = "RrhhEmpleados.findByNombre", query = "SELECT r FROM RrhhEmpleados r WHERE r.nombre = :nombre")})
public class RrhhEmpleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "nroEmpleado")
    private List<RrhhMarcas> rrhhMarcasList;

    public RrhhEmpleados() {
    }

    public RrhhEmpleados(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<RrhhMarcas> getRrhhMarcasList() {
        return rrhhMarcasList;
    }

    public void setRrhhMarcasList(List<RrhhMarcas> rrhhMarcasList) {
        this.rrhhMarcasList = rrhhMarcasList;
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
        if (!(object instanceof RrhhEmpleados)) {
            return false;
        }
        RrhhEmpleados other = (RrhhEmpleados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.admin.domain.RrhhEmpleados[ id=" + id + " ]";
    }

}
