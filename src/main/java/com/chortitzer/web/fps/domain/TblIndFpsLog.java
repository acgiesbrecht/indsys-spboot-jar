/*
 * To change this license header, choose License Headers in project properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.web.fps.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "tbl_ind_fps_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIndFpsLog.findAll", query = "SELECT t FROM TblIndFpsLog t"),
    @NamedQuery(name = "TblIndFpsLog.findByFechahora", query = "SELECT t FROM TblIndFpsLog t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblIndFpsLog.findByp0", query = "SELECT t FROM TblIndFpsLog t WHERE t.p0 = :p0"),
    @NamedQuery(name = "TblIndFpsLog.findByp1", query = "SELECT t FROM TblIndFpsLog t WHERE t.p1 = :p1"),
    @NamedQuery(name = "TblIndFpsLog.findByp2", query = "SELECT t FROM TblIndFpsLog t WHERE t.p2 = :p2"),
    @NamedQuery(name = "TblIndFpsLog.findByp3", query = "SELECT t FROM TblIndFpsLog t WHERE t.p3 = :p3"),
    @NamedQuery(name = "TblIndFpsLog.findByp4", query = "SELECT t FROM TblIndFpsLog t WHERE t.p4 = :p4"),
    @NamedQuery(name = "TblIndFpsLog.findByp5", query = "SELECT t FROM TblIndFpsLog t WHERE t.p5 = :p5"),
    @NamedQuery(name = "TblIndFpsLog.findByp6", query = "SELECT t FROM TblIndFpsLog t WHERE t.p6 = :p6"),
    @NamedQuery(name = "TblIndFpsLog.findByp7", query = "SELECT t FROM TblIndFpsLog t WHERE t.p7 = :p7"),
    @NamedQuery(name = "TblIndFpsLog.findByp8", query = "SELECT t FROM TblIndFpsLog t WHERE t.p8 = :p8"),
    @NamedQuery(name = "TblIndFpsLog.findByp9", query = "SELECT t FROM TblIndFpsLog t WHERE t.p9 = :p9"),
    @NamedQuery(name = "TblIndFpsLog.findByp10", query = "SELECT t FROM TblIndFpsLog t WHERE t.p10 = :p10"),
    @NamedQuery(name = "TblIndFpsLog.findByp11", query = "SELECT t FROM TblIndFpsLog t WHERE t.p11 = :p11"),
    @NamedQuery(name = "TblIndFpsLog.findByp12", query = "SELECT t FROM TblIndFpsLog t WHERE t.p12 = :p12"),
    @NamedQuery(name = "TblIndFpsLog.findByp13", query = "SELECT t FROM TblIndFpsLog t WHERE t.p13 = :p13"),
    @NamedQuery(name = "TblIndFpsLog.findByp14", query = "SELECT t FROM TblIndFpsLog t WHERE t.p14 = :p14"),
    @NamedQuery(name = "TblIndFpsLog.findByp15", query = "SELECT t FROM TblIndFpsLog t WHERE t.p15 = :p15"),
    @NamedQuery(name = "TblIndFpsLog.findByp16", query = "SELECT t FROM TblIndFpsLog t WHERE t.p16 = :p16"),
    @NamedQuery(name = "TblIndFpsLog.findByp17", query = "SELECT t FROM TblIndFpsLog t WHERE t.p17 = :p17"),
    @NamedQuery(name = "TblIndFpsLog.findByp18", query = "SELECT t FROM TblIndFpsLog t WHERE t.p18 = :p18"),
    @NamedQuery(name = "TblIndFpsLog.findByp19", query = "SELECT t FROM TblIndFpsLog t WHERE t.p19 = :p19"),
    @NamedQuery(name = "TblIndFpsLog.findByp20", query = "SELECT t FROM TblIndFpsLog t WHERE t.p20 = :p20"),
    @NamedQuery(name = "TblIndFpsLog.findByp21", query = "SELECT t FROM TblIndFpsLog t WHERE t.p21 = :p21"),
    @NamedQuery(name = "TblIndFpsLog.findByp22", query = "SELECT t FROM TblIndFpsLog t WHERE t.p22 = :p22"),
    @NamedQuery(name = "TblIndFpsLog.findByp23", query = "SELECT t FROM TblIndFpsLog t WHERE t.p23 = :p23"),
    @NamedQuery(name = "TblIndFpsLog.findByp24", query = "SELECT t FROM TblIndFpsLog t WHERE t.p24 = :p24"),
    @NamedQuery(name = "TblIndFpsLog.findByp25", query = "SELECT t FROM TblIndFpsLog t WHERE t.p25 = :p25"),
    @NamedQuery(name = "TblIndFpsLog.findByp26", query = "SELECT t FROM TblIndFpsLog t WHERE t.p26 = :p26")})
public class TblIndFpsLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "p0")
    private BigDecimal p0;
    @Column(name = "p1")
    private BigDecimal p1;
    @Column(name = "p2")
    private BigDecimal p2;
    @Column(name = "p3")
    private BigDecimal p3;
    @Column(name = "p4")
    private BigDecimal p4;
    @Column(name = "p5")
    private BigDecimal p5;
    @Column(name = "p6")
    private BigDecimal p6;
    @Column(name = "p7")
    private BigDecimal p7;
    @Column(name = "p8")
    private BigDecimal p8;
    @Column(name = "p9")
    private BigDecimal p9;
    @Column(name = "p10")
    private BigDecimal p10;
    @Column(name = "p11")
    private BigDecimal p11;
    @Column(name = "p12")
    private BigDecimal p12;
    @Column(name = "p13")
    private BigDecimal p13;
    @Column(name = "p14")
    private BigDecimal p14;
    @Column(name = "p15")
    private BigDecimal p15;
    @Column(name = "p16")
    private BigDecimal p16;
    @Column(name = "p17")
    private BigDecimal p17;
    @Column(name = "p18")
    private BigDecimal p18;
    @Column(name = "p19")
    private BigDecimal p19;
    @Column(name = "p20")
    private BigDecimal p20;
    @Column(name = "p21")
    private BigDecimal p21;
    @Column(name = "p22")
    private BigDecimal p22;
    @Column(name = "p23")
    private BigDecimal p23;
    @Column(name = "p24")
    private BigDecimal p24;
    @Column(name = "p25")
    private BigDecimal p25;
    @Column(name = "p26")
    private BigDecimal p26;

    public TblIndFpsLog() {
    }

    public TblIndFpsLog(Date fechahora) {
        this.fechahora = fechahora;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public BigDecimal getp0() {
        return p0;
    }

    public void setp0(BigDecimal p0) {
        this.p0 = p0;
    }

    public BigDecimal getp1() {
        return p1;
    }

    public void setp1(BigDecimal p1) {
        this.p1 = p1;
    }

    public BigDecimal getp2() {
        return p2;
    }

    public void setp2(BigDecimal p2) {
        this.p2 = p2;
    }

    public BigDecimal getp3() {
        return p3;
    }

    public void setp3(BigDecimal p3) {
        this.p3 = p3;
    }

    public BigDecimal getp4() {
        return p4;
    }

    public void setp4(BigDecimal p4) {
        this.p4 = p4;
    }

    public BigDecimal getp5() {
        return p5;
    }

    public void setp5(BigDecimal p5) {
        this.p5 = p5;
    }

    public BigDecimal getp6() {
        return p6;
    }

    public void setp6(BigDecimal p6) {
        this.p6 = p6;
    }

    public BigDecimal getp7() {
        return p7;
    }

    public void setp7(BigDecimal p7) {
        this.p7 = p7;
    }

    public BigDecimal getp8() {
        return p8;
    }

    public void setp8(BigDecimal p8) {
        this.p8 = p8;
    }

    public BigDecimal getp9() {
        return p9;
    }

    public void setp9(BigDecimal p9) {
        this.p9 = p9;
    }

    public BigDecimal getp10() {
        return p10;
    }

    public void setp10(BigDecimal p10) {
        this.p10 = p10;
    }

    public BigDecimal getp11() {
        return p11;
    }

    public void setp11(BigDecimal p11) {
        this.p11 = p11;
    }

    public BigDecimal getp12() {
        return p12;
    }

    public void setp12(BigDecimal p12) {
        this.p12 = p12;
    }

    public BigDecimal getp13() {
        return p13;
    }

    public void setp13(BigDecimal p13) {
        this.p13 = p13;
    }

    public BigDecimal getp14() {
        return p14;
    }

    public void setp14(BigDecimal p14) {
        this.p14 = p14;
    }

    public BigDecimal getp15() {
        return p15;
    }

    public void setp15(BigDecimal p15) {
        this.p15 = p15;
    }

    public BigDecimal getp16() {
        return p16;
    }

    public void setp16(BigDecimal p16) {
        this.p16 = p16;
    }

    public BigDecimal getp17() {
        return p17;
    }

    public void setp17(BigDecimal p17) {
        this.p17 = p17;
    }

    public BigDecimal getp18() {
        return p18;
    }

    public void setp18(BigDecimal p18) {
        this.p18 = p18;
    }

    public BigDecimal getp19() {
        return p19;
    }

    public void setp19(BigDecimal p19) {
        this.p19 = p19;
    }

    public BigDecimal getp20() {
        return p20;
    }

    public void setp20(BigDecimal p20) {
        this.p20 = p20;
    }

    public BigDecimal getp21() {
        return p21;
    }

    public void setp21(BigDecimal p21) {
        this.p21 = p21;
    }

    public BigDecimal getp22() {
        return p22;
    }

    public void setp22(BigDecimal p22) {
        this.p22 = p22;
    }

    public BigDecimal getp23() {
        return p23;
    }

    public void setp23(BigDecimal p23) {
        this.p23 = p23;
    }

    public BigDecimal getp24() {
        return p24;
    }

    public void setp24(BigDecimal p24) {
        this.p24 = p24;
    }

    public BigDecimal getp25() {
        return p25;
    }

    public void setp25(BigDecimal p25) {
        this.p25 = p25;
    }

    public BigDecimal getp26() {
        return p26;
    }

    public void setp26(BigDecimal p26) {
        this.p26 = p26;
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
        if (!(object instanceof TblIndFpsLog)) {
            return false;
        }
        TblIndFpsLog other = (TblIndFpsLog) object;
        if ((this.fechahora == null && other.fechahora != null) || (this.fechahora != null && !this.fechahora.equals(other.fechahora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.fps.TblIndFpsLog[ fechahora=" + fechahora + " ]";
    }
    
}
