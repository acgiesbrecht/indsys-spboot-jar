/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.usi.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 *
 * @author Industria
 */
@Entity
public class NisSinLecturaModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 16)
    @Column(name = "NIS")
    private String nis;
    @Size(max = 30)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "NRSERIE")
    private Double nrserie;

    public NisSinLecturaModel() {

    }

    public NisSinLecturaModel(Double nrserie, String nis, String usuario) {
        this.nrserie = nrserie;
        this.nis = nis;
        this.usuario = usuario;
    }

    /**
     * @return the nis
     */
    public String getNis() {
        return nis;
    }

    /**
     * @param nis the nis to set
     */
    public void setNis(String nis) {
        this.nis = nis;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nrserie
     */
    public Double getNrserie() {
        return nrserie;
    }

    /**
     * @param nrserie the nrserie to set
     */
    public void setNrserie(Double nrserie) {
        this.nrserie = nrserie;
    }

}
