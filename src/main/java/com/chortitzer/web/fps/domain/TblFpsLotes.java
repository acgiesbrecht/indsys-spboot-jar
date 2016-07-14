/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.fps.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "tbl_fps_lotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblFpsLotes.findAll", query = "SELECT t FROM TblFpsLotes t"),
    @NamedQuery(name = "TblFpsLotes.findById", query = "SELECT t FROM TblFpsLotes t WHERE t.id = :id"),
    @NamedQuery(name = "TblFpsLotes.findByIdFabrica", query = "SELECT t FROM TblFpsLotes t WHERE t.idFabrica = :idFabrica"),
    @NamedQuery(name = "TblFpsLotes.findByFecha", query = "SELECT t FROM TblFpsLotes t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TblFpsLotes.findByObs", query = "SELECT t FROM TblFpsLotes t WHERE t.obs = :obs")})
public class TblFpsLotes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fabrica")
    private int idFabrica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 200)
    @Column(name = "obs")
    private String obs;

    public TblFpsLotes() {
    }

    public TblFpsLotes(Integer id) {
        this.id = id;
    }

    public TblFpsLotes(Integer id, int idFabrica, Date fecha) {
        this.id = id;
        this.idFabrica = idFabrica;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdFabrica() {
        return idFabrica;
    }

    public void setIdFabrica(int idFabrica) {
        this.idFabrica = idFabrica;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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
        if (!(object instanceof TblFpsLotes)) {
            return false;
        }
        TblFpsLotes other = (TblFpsLotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.fps.TblFpsLotes[ id=" + id + " ]";
    }
    
}
