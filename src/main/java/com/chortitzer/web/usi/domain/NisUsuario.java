/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.usi.domain;

import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "nis_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NisUsuario.findAll", query = "SELECT n FROM NisUsuario n"),
    @NamedQuery(name = "NisUsuario.findByFid", query = "SELECT n FROM NisUsuario n WHERE n.fid = :fid"),
    @NamedQuery(name = "NisUsuario.findByUsuario", query = "SELECT n FROM NisUsuario n WHERE n.usuario = :usuario"),
    @NamedQuery(name = "NisUsuario.findByNis", query = "SELECT n FROM NisUsuario n WHERE n.nis = :nis"),
    @NamedQuery(name = "NisUsuario.findByLocalidad", query = "SELECT n FROM NisUsuario n WHERE n.localidad = :localidad"),
    @NamedQuery(name = "NisUsuario.findByDistrito", query = "SELECT n FROM NisUsuario n WHERE n.distrito = :distrito"),
    @NamedQuery(name = "NisUsuario.findByTarifa", query = "SELECT n FROM NisUsuario n WHERE n.tarifa = :tarifa"),
    @NamedQuery(name = "NisUsuario.findByCateg", query = "SELECT n FROM NisUsuario n WHERE n.categ = :categ"),
    @NamedQuery(name = "NisUsuario.findByZonapdNis", query = "SELECT n FROM NisUsuario n WHERE n.zonapdNis = :zonapdNis"),
    @NamedQuery(name = "NisUsuario.findByTipoAcom", query = "SELECT n FROM NisUsuario n WHERE n.tipoAcom = :tipoAcom"),
    @NamedQuery(name = "NisUsuario.findByNroMed", query = "SELECT n FROM NisUsuario n WHERE n.nroMed = :nroMed"),
    @NamedQuery(name = "NisUsuario.findByNroFases", query = "SELECT n FROM NisUsuario n WHERE n.nroFases = :nroFases"),
    @NamedQuery(name = "NisUsuario.findByNumande", query = "SELECT n FROM NisUsuario n WHERE n.numande = :numande"),
    @NamedQuery(name = "NisUsuario.findByObs", query = "SELECT n FROM NisUsuario n WHERE n.obs = :obs"),
    @NamedQuery(name = "NisUsuario.findByFase", query = "SELECT n FROM NisUsuario n WHERE n.fase = :fase"),
    @NamedQuery(name = "NisUsuario.findByXCoord", query = "SELECT n FROM NisUsuario n WHERE n.xCoord = :xCoord"),
    @NamedQuery(name = "NisUsuario.findByYCoord", query = "SELECT n FROM NisUsuario n WHERE n.yCoord = :yCoord"),
    @NamedQuery(name = "NisUsuario.findByNrserie", query = "SELECT n FROM NisUsuario n WHERE n.nrserie = :nrserie")})
public class NisUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fid")
    private Integer fid;
    @Lob
    @Column(name = "the_geom")
    private Point theGeom;
    @Size(max = 30)
    @Column(name = "`USUARIO`")
    private String usuario;
    @Size(max = 16)
    @Column(name = "`NIS`")
    private String nis;
    @Size(max = 25)
    @Column(name = "LOCALIDAD")
    private String localidad;
    @Size(max = 30)
    @Column(name = "DISTRITO")
    private String distrito;
    @Size(max = 5)
    @Column(name = "TARIFA")
    private String tarifa;
    @Size(max = 5)
    @Column(name = "CATEG")
    private String categ;
    @Size(max = 8)
    @Column(name = "ZONAPD_NIS")
    private String zonapdNis;
    @Size(max = 10)
    @Column(name = "TIPO_ACOM")
    private String tipoAcom;
    @Size(max = 16)
    @Column(name = "NRO_MED")
    private String nroMed;
    @Size(max = 4)
    @Column(name = "NRO_FASES")
    private String nroFases;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NUMANDE")
    private Double numande;
    @Size(max = 16)
    @Column(name = "OBS")
    private String obs;
    @Column(name = "FASE")
    private Double fase;
    @Column(name = "X_COORD")
    private Double xCoord;
    @Column(name = "Y_COORD")
    private Double yCoord;
    @Column(name = "`NRSERIE`")
    private Double nrserie;

    public NisUsuario() {
    }

    public NisUsuario(Integer fid) {
        this.fid = fid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Object getTheGeom() {
        return theGeom;
    }

    public void setTheGeom(Point theGeom) {
        this.theGeom = theGeom;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getZonapdNis() {
        return zonapdNis;
    }

    public void setZonapdNis(String zonapdNis) {
        this.zonapdNis = zonapdNis;
    }

    public String getTipoAcom() {
        return tipoAcom;
    }

    public void setTipoAcom(String tipoAcom) {
        this.tipoAcom = tipoAcom;
    }

    public String getNroMed() {
        return nroMed;
    }

    public void setNroMed(String nroMed) {
        this.nroMed = nroMed;
    }

    public String getNroFases() {
        return nroFases;
    }

    public void setNroFases(String nroFases) {
        this.nroFases = nroFases;
    }

    public Double getNumande() {
        return numande;
    }

    public void setNumande(Double numande) {
        this.numande = numande;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Double getFase() {
        return fase;
    }

    public void setFase(Double fase) {
        this.fase = fase;
    }

    public Double getXCoord() {
        return xCoord;
    }

    public void setXCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    public Double getYCoord() {
        return yCoord;
    }

    public void setYCoord(Double yCoord) {
        this.yCoord = yCoord;
    }

    public Double getNrserie() {
        return nrserie;
    }

    public void setNrserie(Double nrserie) {
        this.nrserie = nrserie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NisUsuario)) {
            return false;
        }
        NisUsuario other = (NisUsuario) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.usi.NisUsuario[ fid=" + fid + " ]";
    }

}
