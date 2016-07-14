/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author Industria
 */
@SqlResultSetMapping(name = "DalBonificacionModel",
        classes = {
            @ConstructorResult(
                    targetClass = DalBonificacionModel.class,
                    columns = {
                        @ColumnResult(name = "productor", type = String.class),
                        @ColumnResult(name = "tipo", type = String.class),
                        @ColumnResult(name = "cantidad", type = BigInteger.class),
                        @ColumnResult(name = "peso", type = BigInteger.class),
                        @ColumnResult(name = "bonificacion_kg", type = Integer.class),
                        @ColumnResult(name = "bonificacion_total", type = BigInteger.class)
                    })
        })
@Entity
public class DalBonificacionModel implements Serializable {

    @Id
    @Column(name = "productor")
    private String productor;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "cantidad")
    private BigInteger cantidad;
    @Column(name = "peso")
    private BigInteger peso;
    @Column(name = "bonificacion_kg")
    private Integer bonificacion_kg;
    @Column(name = "bonificacion_total")
    private BigInteger bonificacion_total;

    public DalBonificacionModel() {

    }

    public DalBonificacionModel(String productor) {
        this.productor = productor;
    }

    /*public DalBonificacionModel(String productor, String tipo, BigInteger cantidad, BigInteger peso, Integer bonificacion_kg, BigInteger bonificacion_total) {

    }*/
    /**
     * @return the productor
     */
    public String getProductor() {
        return productor;
    }

    /**
     * @param productor the productor to set
     */
    public void setProductor(String productor) {
        this.productor = productor;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the cantidad
     */
    public BigInteger getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the peso
     */
    public BigInteger getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    /**
     * @return the bonificacion_kg
     */
    public Integer getBonificacion_kg() {
        return bonificacion_kg;
    }

    /**
     * @param bonificacion_kg the bonificacion_kg to set
     */
    public void setBonificacion_kg(Integer bonificacion_kg) {
        this.bonificacion_kg = bonificacion_kg;
    }

    /**
     * @return the bonificacion_total
     */
    public BigInteger getBonificacion_total() {
        return bonificacion_total;
    }

    /**
     * @param bonificacion_total the bonificacion_total to set
     */
    public void setBonificacion_total(BigInteger bonificacion_total) {
        this.bonificacion_total = bonificacion_total;
    }

}
