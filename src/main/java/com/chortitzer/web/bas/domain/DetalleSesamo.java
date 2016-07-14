/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "detalle_sesamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSesamo.findAll", query = "SELECT d FROM DetalleSesamo d"),
    @NamedQuery(name = "DetalleSesamo.findById", query = "SELECT d FROM DetalleSesamo d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleSesamo.findByChapa", query = "SELECT d FROM DetalleSesamo d WHERE d.chapa = :chapa"),
    @NamedQuery(name = "DetalleSesamo.findByProductor", query = "SELECT d FROM DetalleSesamo d WHERE d.productor = :productor"),
    @NamedQuery(name = "DetalleSesamo.findByIdProductor", query = "SELECT d FROM DetalleSesamo d WHERE d.idProductor = :idProductor"),
    @NamedQuery(name = "DetalleSesamo.findByProducto", query = "SELECT d FROM DetalleSesamo d WHERE d.producto = :producto"),
    @NamedQuery(name = "DetalleSesamo.findByIdProducto", query = "SELECT d FROM DetalleSesamo d WHERE d.idProducto = :idProducto"),
    @NamedQuery(name = "DetalleSesamo.findByBruto", query = "SELECT d FROM DetalleSesamo d WHERE d.bruto = :bruto"),
    @NamedQuery(name = "DetalleSesamo.findByTara", query = "SELECT d FROM DetalleSesamo d WHERE d.tara = :tara"),
    @NamedQuery(name = "DetalleSesamo.findByFechahora", query = "SELECT d FROM DetalleSesamo d WHERE d.fechahora = :fechahora"),
    @NamedQuery(name = "DetalleSesamo.findByPrecioGsPorKg", query = "SELECT d FROM DetalleSesamo d WHERE d.precioGsPorKg = :precioGsPorKg"),
    @NamedQuery(name = "DetalleSesamo.findByIdLote", query = "SELECT d FROM DetalleSesamo d WHERE d.idLote = :idLote"),
    @NamedQuery(name = "DetalleSesamo.findByAutorizado", query = "SELECT d FROM DetalleSesamo d WHERE d.autorizado = :autorizado"),
    @NamedQuery(name = "DetalleSesamo.findByNeto", query = "SELECT d FROM DetalleSesamo d WHERE d.neto = :neto"),
    @NamedQuery(name = "DetalleSesamo.findByHumedad", query = "SELECT d FROM DetalleSesamo d WHERE d.humedad = :humedad"),
    @NamedQuery(name = "DetalleSesamo.findByImpurezas", query = "SELECT d FROM DetalleSesamo d WHERE d.impurezas = :impurezas"),
    @NamedQuery(name = "DetalleSesamo.findByAcidez", query = "SELECT d FROM DetalleSesamo d WHERE d.acidez = :acidez"),
    @NamedQuery(name = "DetalleSesamo.findByPesoHumedad", query = "SELECT d FROM DetalleSesamo d WHERE d.pesoHumedad = :pesoHumedad"),
    @NamedQuery(name = "DetalleSesamo.findByPesoImpurezas", query = "SELECT d FROM DetalleSesamo d WHERE d.pesoImpurezas = :pesoImpurezas"),
    @NamedQuery(name = "DetalleSesamo.findByNetoLiquido", query = "SELECT d FROM DetalleSesamo d WHERE d.netoLiquido = :netoLiquido"),
    @NamedQuery(name = "DetalleSesamo.findByMonto", query = "SELECT d FROM DetalleSesamo d WHERE d.monto = :monto")})
public class DetalleSesamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Size(max = 6)
    @Column(name = "chapa")
    private String chapa;
    @Size(max = 100)
    @Column(name = "productor")
    private String productor;
    @Column(name = "id_productor")
    private Integer idProductor;
    @Size(max = 45)
    @Column(name = "producto")
    private String producto;
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(name = "bruto")
    private Integer bruto;
    @Column(name = "tara")
    private Integer tara;
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Column(name = "precio_gs_por_kg")
    private Integer precioGsPorKg;
    @Column(name = "id_lote")
    private Integer idLote;
    @Column(name = "autorizado")
    private Integer autorizado;
    @Column(name = "neto")
    private Integer neto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "humedad")
    private Double humedad;
    @Column(name = "impurezas")
    private Double impurezas;
    @Column(name = "acidez")
    private Double acidez;
    @Column(name = "peso_humedad")
    private Integer pesoHumedad;
    @Column(name = "peso_impurezas")
    private Integer pesoImpurezas;
    @Column(name = "neto_liquido")
    private Integer netoLiquido;
    @Column(name = "monto")
    private Integer monto;

    public DetalleSesamo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public Integer getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(Integer idProductor) {
        this.idProductor = idProductor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getBruto() {
        return bruto;
    }

    public void setBruto(Integer bruto) {
        this.bruto = bruto;
    }

    public Integer getTara() {
        return tara;
    }

    public void setTara(Integer tara) {
        this.tara = tara;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public Integer getPrecioGsPorKg() {
        return precioGsPorKg;
    }

    public void setPrecioGsPorKg(Integer precioGsPorKg) {
        this.precioGsPorKg = precioGsPorKg;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getNeto() {
        return neto;
    }

    public void setNeto(Integer neto) {
        this.neto = neto;
    }

    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }

    public Double getImpurezas() {
        return impurezas;
    }

    public void setImpurezas(Double impurezas) {
        this.impurezas = impurezas;
    }

    public Double getAcidez() {
        return acidez;
    }

    public void setAcidez(Double acidez) {
        this.acidez = acidez;
    }

    public Integer getPesoHumedad() {
        return pesoHumedad;
    }

    public void setPesoHumedad(Integer pesoHumedad) {
        this.pesoHumedad = pesoHumedad;
    }

    public Integer getPesoImpurezas() {
        return pesoImpurezas;
    }

    public void setPesoImpurezas(Integer pesoImpurezas) {
        this.pesoImpurezas = pesoImpurezas;
    }

    public Integer getNetoLiquido() {
        return netoLiquido;
    }

    public void setNetoLiquido(Integer netoLiquido) {
        this.netoLiquido = netoLiquido;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

}
