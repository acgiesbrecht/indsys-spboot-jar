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
@Table(name = "detalle_granos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleGranos.findAll", query = "SELECT d FROM DetalleGranos d"),
    @NamedQuery(name = "DetalleGranos.findById", query = "SELECT d FROM DetalleGranos d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleGranos.findByChapa", query = "SELECT d FROM DetalleGranos d WHERE d.chapa = :chapa"),
    @NamedQuery(name = "DetalleGranos.findByProductor", query = "SELECT d FROM DetalleGranos d WHERE d.productor = :productor"),
    @NamedQuery(name = "DetalleGranos.findByIdProductor", query = "SELECT d FROM DetalleGranos d WHERE d.idProductor = :idProductor"),
    @NamedQuery(name = "DetalleGranos.findByProducto", query = "SELECT d FROM DetalleGranos d WHERE d.producto = :producto"),
    @NamedQuery(name = "DetalleGranos.findByIdProducto", query = "SELECT d FROM DetalleGranos d WHERE d.idProducto = :idProducto"),
    @NamedQuery(name = "DetalleGranos.findByBruto", query = "SELECT d FROM DetalleGranos d WHERE d.bruto = :bruto"),
    @NamedQuery(name = "DetalleGranos.findByTara", query = "SELECT d FROM DetalleGranos d WHERE d.tara = :tara"),
    @NamedQuery(name = "DetalleGranos.findByFechahora", query = "SELECT d FROM DetalleGranos d WHERE d.fechahora = :fechahora"),
    @NamedQuery(name = "DetalleGranos.findByPrecioGsPorKg", query = "SELECT d FROM DetalleGranos d WHERE d.precioGsPorKg = :precioGsPorKg"),
    @NamedQuery(name = "DetalleGranos.findByIdLote", query = "SELECT d FROM DetalleGranos d WHERE d.idLote = :idLote"),
    @NamedQuery(name = "DetalleGranos.findByAutorizado", query = "SELECT d FROM DetalleGranos d WHERE d.autorizado = :autorizado"),
    @NamedQuery(name = "DetalleGranos.findByNeto", query = "SELECT d FROM DetalleGranos d WHERE d.neto = :neto"),
    @NamedQuery(name = "DetalleGranos.findByHumedad", query = "SELECT d FROM DetalleGranos d WHERE d.humedad = :humedad"),
    @NamedQuery(name = "DetalleGranos.findByImpurezas", query = "SELECT d FROM DetalleGranos d WHERE d.impurezas = :impurezas"),
    @NamedQuery(name = "DetalleGranos.findByArdidos", query = "SELECT d FROM DetalleGranos d WHERE d.ardidos = :ardidos"),
    @NamedQuery(name = "DetalleGranos.findByQuebrados", query = "SELECT d FROM DetalleGranos d WHERE d.quebrados = :quebrados"),
    @NamedQuery(name = "DetalleGranos.findByPesoHumedad", query = "SELECT d FROM DetalleGranos d WHERE d.pesoHumedad = :pesoHumedad"),
    @NamedQuery(name = "DetalleGranos.findByPesoImpurezas", query = "SELECT d FROM DetalleGranos d WHERE d.pesoImpurezas = :pesoImpurezas"),
    @NamedQuery(name = "DetalleGranos.findByPesoArdidos", query = "SELECT d FROM DetalleGranos d WHERE d.pesoArdidos = :pesoArdidos"),
    @NamedQuery(name = "DetalleGranos.findByPesoQuebrados", query = "SELECT d FROM DetalleGranos d WHERE d.pesoQuebrados = :pesoQuebrados"),
    @NamedQuery(name = "DetalleGranos.findByNetoLiquido", query = "SELECT d FROM DetalleGranos d WHERE d.netoLiquido = :netoLiquido"),
    @NamedQuery(name = "DetalleGranos.findByMonto", query = "SELECT d FROM DetalleGranos d WHERE d.monto = :monto")})
public class DetalleGranos implements Serializable {

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
    @Column(name = "ardidos")
    private Double ardidos;
    @Column(name = "quebrados")
    private Double quebrados;
    @Column(name = "peso_humedad")
    private Integer pesoHumedad;
    @Column(name = "peso_impurezas")
    private Integer pesoImpurezas;
    @Column(name = "peso_ardidos")
    private Integer pesoArdidos;
    @Column(name = "peso_quebrados")
    private Integer pesoQuebrados;
    @Column(name = "neto_liquido")
    private Integer netoLiquido;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "id_remision")
    private String idRemision;

    public DetalleGranos() {
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

    public Double getArdidos() {
        return ardidos;
    }

    public void setArdidos(Double ardidos) {
        this.ardidos = ardidos;
    }

    public Double getQuebrados() {
        return quebrados;
    }

    public void setQuebrados(Double quebrados) {
        this.quebrados = quebrados;
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

    public Integer getPesoArdidos() {
        return pesoArdidos;
    }

    public void setPesoArdidos(Integer pesoArdidos) {
        this.pesoArdidos = pesoArdidos;
    }

    public Integer getPesoQuebrados() {
        return pesoQuebrados;
    }

    public void setPesoQuebrados(Integer pesoQuebrados) {
        this.pesoQuebrados = pesoQuebrados;
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

    /**
     * @return the idRemision
     */
    public String getIdRemision() {
        return idRemision;
    }

    /**
     * @param idRemision the idRemision to set
     */
    public void setIdRemision(String idRemision) {
        this.idRemision = idRemision;
    }

}
