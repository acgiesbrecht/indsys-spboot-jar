/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "precios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Precios.findAll", query = "SELECT p FROM Precios p"),
    @NamedQuery(name = "Precios.findByDescripcion", query = "SELECT p FROM Precios p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Precios.findByValorGsPorKg", query = "SELECT p FROM Precios p WHERE p.valorGsPorKg = :valorGsPorKg"),
    @NamedQuery(name = "Precios.findByFecha", query = "SELECT p FROM Precios p WHERE p.fecha = :fecha")})
public class Precios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valor_gs_por_kg")
    private Integer valorGsPorKg;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Precios() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValorGsPorKg() {
        return valorGsPorKg;
    }

    public void setValorGsPorKg(Integer valorGsPorKg) {
        this.valorGsPorKg = valorGsPorKg;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
