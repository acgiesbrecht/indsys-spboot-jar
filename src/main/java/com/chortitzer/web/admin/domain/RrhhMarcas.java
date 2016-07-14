/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "rrhh_marcas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhMarcas.findAll", query = "SELECT r FROM RrhhMarcas r"),
    @NamedQuery(name = "RrhhMarcas.findByFechahora", query = "SELECT r FROM RrhhMarcas r WHERE r.fechahora = :fechahora"),
    @NamedQuery(name = "RrhhMarcas.findByEntradaSalida", query = "SELECT r FROM RrhhMarcas r WHERE r.entradaSalida = :entradaSalida")})
public class RrhhMarcas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entrada_salida")
    private int entradaSalida;
    @JoinColumn(name = "nro_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RrhhEmpleados nroEmpleado;

    public RrhhMarcas() {
    }

    public RrhhMarcas(Date fechahora) {
        this.fechahora = fechahora;
    }

    public RrhhMarcas(Date fechahora, int entradaSalida) {
        this.fechahora = fechahora;
        this.entradaSalida = entradaSalida;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public int getEntradaSalida() {
        return entradaSalida;
    }

    public void setEntradaSalida(int entradaSalida) {
        this.entradaSalida = entradaSalida;
    }

    public RrhhEmpleados getNroEmpleado() {
        return nroEmpleado;
    }

    public void setNroEmpleado(RrhhEmpleados nroEmpleado) {
        this.nroEmpleado = nroEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechahora != null ? fechahora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhMarcas)) {
            return false;
        }
        RrhhMarcas other = (RrhhMarcas) object;
        if ((this.fechahora == null && other.fechahora != null) || (this.fechahora != null && !this.fechahora.equals(other.fechahora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.admin.domain.RrhhMarcas[ fechahora=" + fechahora + " ]";
    }

}
