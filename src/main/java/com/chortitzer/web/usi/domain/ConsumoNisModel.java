/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.usi.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Industria
 */
@Entity
public class ConsumoNisModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 16)
    @Column(name = "NIS")
    private String nis;
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NRSERIE")
    private BigInteger nrserie;
    @Size(max = 30)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "kwh")
    private Integer kwh;
    @Column(name = "consumo")
    private Integer consumo;

    public ConsumoNisModel() {
    }

    public ConsumoNisModel(String nis, Date fechahora, BigInteger nrserie, String usuario, Integer kwh, Integer consumo) {
        this.nis = nis;
        this.fechahora = fechahora;
        this.nrserie = nrserie;
        this.usuario = usuario;
        this.kwh = kwh;
        this.consumo = consumo;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public BigInteger getNrserie() {
        return nrserie;
    }

    public void setNrserie(BigInteger nrserie) {
        this.nrserie = nrserie;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getKwh() {
        return kwh;
    }

    public void setKwh(Integer kwh) {
        this.kwh = kwh;
    }

    public Integer getConsumo() {
        return consumo;
    }

    public void setConsumo(Integer consumo) {
        this.consumo = consumo;
    }

}
