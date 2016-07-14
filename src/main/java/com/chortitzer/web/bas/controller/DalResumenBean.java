/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.controller;

import com.chortitzer.web.bas.domain.DalResumenFibraModel;
import com.chortitzer.web.bas.domain.TblDalLotes;
import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.repository.TblDalFardosFibraRepository;
import com.chortitzer.web.bas.repository.TblDalLotesRepository;
import com.chortitzer.web.bas.repository.TblempresaRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class DalResumenBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TblDalLotesRepository tblDalLotesRepository;

    @Autowired
    TblDalFardosFibraRepository tblDalFardosFibraRepository;

    @Autowired
    TblempresaRepository tblempresaRepository;

    private List<DalResumenFibraModel> resumenList;

    private List<Tblempresa> empresaList;

    private Tblempresa selectedEmpresa;
    private TblDalLotes selectedLote;
    private int selectedAno;
    private List<TblDalLotes> lotesList;
    private List<Integer> anosList;
    private String hasLotes;

    @PostConstruct
    private void init() {
        try {
            getAnosList();
            setSelectedAno(anosList.get(0));
            getEmpresaList();
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
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
     * @return the selectedLote
     */
    public TblDalLotes getSelectedLote() {
        return selectedLote;
    }

    /**
     * @param selectedLote the selectedLote to set
     */
    public void setSelectedLote(TblDalLotes selectedLote) {
        this.selectedLote = selectedLote;
    }

    /**
     * @return the lotesList
     */
    public List<TblDalLotes> getLotesList() {
        try {
            if (selectedEmpresa != null) {
                lotesList = tblDalLotesRepository.getLotesByEmpresaAndAno(selectedEmpresa, getSelectedAno());
                /*if (lotesList.size() > 0) {
                    setSelectedLote(lotesList.get(0));
                } else {
                    setSelectedLote(null);
                }*/

            }

            return lotesList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param lotesList the lotesList to set
     */
    public void setLotesList(List<TblDalLotes> lotesList) {
        this.lotesList = lotesList;
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
    public int getSelectedAno() {
        return selectedAno;
    }

    /**
     * @param selectedAno the selectedAno to set
     */
    public void setSelectedAno(int selectedAno) {
        this.selectedAno = selectedAno;
    }

    /**
     * @return the resumenList
     */
    public List<DalResumenFibraModel> getResumenList() {
        try {
            resumenList = null;
            if (selectedEmpresa == null) {
                resumenList = new ArrayList<>();
                List<Object[]> obj = tblDalFardosFibraRepository.getResumenByAno(selectedAno);

                obj.stream().forEach((record) -> {
                    DalResumenFibraModel b = new DalResumenFibraModel();
                    b.setTipo((String) record[0]);
                    b.setMicronaire((String) record[1]);
                    b.setCantidad((Integer) record[2]);
                    b.setPeso((Integer) record[3]);
                    b.setPorcentaje((Integer) record[3]);
                    resumenList.add(b);
                });

                return resumenList;
            } else if (selectedLote != null) {
                resumenList = new ArrayList<>();
                List<Object[]> obj = tblDalFardosFibraRepository.getResumenByLote(selectedLote.getId());

                obj.stream().forEach((record) -> {
                    DalResumenFibraModel b = new DalResumenFibraModel();
                    b.setTipo((String) record[0]);
                    b.setMicronaire((String) record[1]);
                    b.setCantidad((Integer) record[2]);
                    b.setPeso((Integer) record[3]);
                    b.setPorcentaje((Integer) record[3]);
                    resumenList.add(b);
                });

                return resumenList;
            } else if (selectedLote == null) {
                resumenList = new ArrayList<>();
                List<Object[]> obj = tblDalFardosFibraRepository.getResumenByAnoAndEmpresa(selectedAno, selectedEmpresa.getId());

                obj.stream().forEach((record) -> {
                    DalResumenFibraModel b = new DalResumenFibraModel();
                    b.setTipo((String) record[0]);
                    b.setMicronaire((String) record[1]);
                    b.setCantidad((Integer) record[2]);
                    b.setPeso((Integer) record[3]);
                    b.setPorcentaje((Integer) record[3]);
                    resumenList.add(b);
                });

                return resumenList;
            }
            return resumenList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param resumenList the resumenList to set
     */
    public void setResumenList(List<DalResumenFibraModel> resumenList) {
        this.resumenList = resumenList;
    }

}
