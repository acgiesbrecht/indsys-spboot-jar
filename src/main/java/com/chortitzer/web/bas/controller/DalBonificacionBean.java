/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.controller;

import com.chortitzer.web.bas.domain.DalBonificacionModel;
import com.chortitzer.web.bas.domain.TblDalBonificaciones;
import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.repository.TblDalBonificacionesRepository;
import com.chortitzer.web.bas.repository.TblDalFardosFibraRepository;
import com.chortitzer.web.bas.repository.TblDalLotesRepository;
import com.chortitzer.web.bas.repository.TblempresaRepository;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class DalBonificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TblDalFardosFibraRepository tblDalFardosFibraRepository;
    @Autowired
    TblDalLotesRepository tblDalLotesRepository;
    @Autowired
    TblempresaRepository tblempresaRepository;
    @Autowired
    TblDalBonificacionesRepository tblDalBonificacionesRepository;

    private List<DalBonificacionModel> fardosList;
    private List<Tblempresa> empresaList;
    private List<TblDalBonificaciones> bonificacionList;
    private Tblempresa selectedEmpresa;
    private Integer selectedAno;
    private TblDalBonificaciones selectedBonificacion;
    private List<Integer> anosList;

    @PostConstruct
    private void init() {
        getBonificacionList();
        setSelectedBonificacion(bonificacionList.get(0));
        getEmpresaList();
    }

    /**
     * @return the empresaList
     */
    public List<Tblempresa> getEmpresaList() {
        try {
            empresaList = tblempresaRepository.findAll();
            return empresaList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param empresaList the empresaList to set
     */
    public void setEmpresaList(List<Tblempresa> empresaList) {
        this.empresaList = empresaList;
    }

    /**
     * @return the selectedEmpresa
     */
    public Tblempresa getSelectedEmpresa() {
        return selectedEmpresa;
    }

    /**
     * @param selectedEmpresa the selectedEmpresa to set
     */
    public void setSelectedEmpresa(Tblempresa selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    /**
     * @return the anosList
     */
    public List<Integer> getAnosList() {
        try {
            anosList = tblDalLotesRepository.getDalAnosAll();
            return anosList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param anosList the anosList to set
     */
    public void setAnosList(List<Integer> anosList) {
        this.anosList = anosList;
    }

    /**
     * @return the selectedAno
     */
    public Integer getSelectedAno() {
        return selectedAno;
    }

    /**
     * @param selectedAno the selectedAno to set
     */
    public void setSelectedAno(Integer selectedAno) {
        this.selectedAno = selectedAno;
    }

    /**
     * @return the fardosList
     */
    public List<DalBonificacionModel> getFardosList() {
        try {
            this.fardosList = new ArrayList<>();
            List<Object[]> obj = tblDalBonificacionesRepository.getBonificacionList(getSelectedBonificacion().getId());

            obj.stream().forEach((record) -> {
                DalBonificacionModel b = new DalBonificacionModel();
                b.setProductor((String) record[0]);
                b.setTipo((String) record[1]);
                b.setCantidad((BigInteger) record[2]);
                b.setPeso((BigInteger) record[3]);
                b.setBonificacion_kg((Integer) record[4]);
                b.setBonificacion_total((BigInteger) record[5]);
                fardosList.add(b);
            });

            return fardosList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param fardosList the fardosList to set
     */
    public void setFardosList(List<DalBonificacionModel> fardosList) {
        this.fardosList = fardosList;
    }

    /**
     * @return the selectedBonificacion
     */
    public TblDalBonificaciones getSelectedBonificacion() {
        return selectedBonificacion;
    }

    /**
     * @param selectedBonificacion the selectedBonificacion to set
     */
    public void setSelectedBonificacion(TblDalBonificaciones selectedBonificacion) {
        this.selectedBonificacion = selectedBonificacion;
    }

    /**
     * @return the bonificacionList
     */
    public List<TblDalBonificaciones> getBonificacionList() {
        bonificacionList = tblDalBonificacionesRepository.findAll(new Sort(Sort.Direction.DESC, "fechahora"));
        return bonificacionList;
    }

    /**
     * @param bonificacionList the bonificacionList to set
     */
    public void setBonificacionList(List<TblDalBonificaciones> bonificacionList) {
        this.bonificacionList = bonificacionList;
    }

    public String formatDate(Date fecha) {
        try {
            if (fecha != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(fecha);
            } else {
                return "TODOS";
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return "ERROR";
        }
    }

}
