/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_dal_bonificacion_valores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDalBonificacionValores.findAll", query = "SELECT t FROM TblDalBonificacionValores t"),
    @NamedQuery(name = "TblDalBonificacionValores.findByIdBonificacion", query = "SELECT t FROM TblDalBonificacionValores t WHERE t.tblDalBonificacionValoresPK.idBonificacion = :idBonificacion"),
    @NamedQuery(name = "TblDalBonificacionValores.findByIdFibraTipo", query = "SELECT t FROM TblDalBonificacionValores t WHERE t.tblDalBonificacionValoresPK.idFibraTipo = :idFibraTipo"),
    @NamedQuery(name = "TblDalBonificacionValores.findByValor", query = "SELECT t FROM TblDalBonificacionValores t WHERE t.valor = :valor")})
public class TblDalBonificacionValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblDalBonificacionValoresPK tblDalBonificacionValoresPK;
    @Column(name = "valor")
    private Integer valor;
    @JoinColumn(name = "id_bonificacion", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblDalBonificaciones tblDalBonificaciones;
    @JoinColumn(name = "id_fibra_tipo", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblDalFibraTipos tblDalFibraTipos;

    public TblDalBonificacionValores() {
    }

    public TblDalBonificacionValores(TblDalBonificacionValoresPK tblDalBonificacionValoresPK) {
        this.tblDalBonificacionValoresPK = tblDalBonificacionValoresPK;
    }

    public TblDalBonificacionValores(int idBonificacion, int idFibraTipo) {
        this.tblDalBonificacionValoresPK = new TblDalBonificacionValoresPK(idBonificacion, idFibraTipo);
    }

    public TblDalBonificacionValoresPK getTblDalBonificacionValoresPK() {
        return tblDalBonificacionValoresPK;
    }

    public void setTblDalBonificacionValoresPK(TblDalBonificacionValoresPK tblDalBonificacionValoresPK) {
        this.tblDalBonificacionValoresPK = tblDalBonificacionValoresPK;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public TblDalBonificaciones getTblDalBonificaciones() {
        return tblDalBonificaciones;
    }

    public void setTblDalBonificaciones(TblDalBonificaciones tblDalBonificaciones) {
        this.tblDalBonificaciones = tblDalBonificaciones;
    }

    public TblDalFibraTipos getTblDalFibraTipos() {
        return tblDalFibraTipos;
    }

    public void setTblDalFibraTipos(TblDalFibraTipos tblDalFibraTipos) {
        this.tblDalFibraTipos = tblDalFibraTipos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblDalBonificacionValoresPK != null ? tblDalBonificacionValoresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDalBonificacionValores)) {
            return false;
        }
        TblDalBonificacionValores other = (TblDalBonificacionValores) object;
        if ((this.tblDalBonificacionValoresPK == null && other.tblDalBonificacionValoresPK != null) || (this.tblDalBonificacionValoresPK != null && !this.tblDalBonificacionValoresPK.equals(other.tblDalBonificacionValoresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.web.bas.domain.TblDalBonificacionValores[ tblDalBonificacionValoresPK=" + tblDalBonificacionValoresPK + " ]";
    }

}
