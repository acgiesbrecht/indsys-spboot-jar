/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.usi.controller;

import com.chortitzer.web.usi.domain.NisSinLecturaModel;
import com.chortitzer.web.usi.repository.TblIndUsiRegistroLecturasRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Industria
 */
@Controller
@Scope("view")
public class NisSinLecturaBean {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TblIndUsiRegistroLecturasRepository tblIndUsiRegistroLecturasRepository;
    Calendar calendar = Calendar.getInstance();
    List<NisSinLecturaModel> nisSinLecturaList;
    private List<NisSinLecturaModel> nisSinLecturaFilteredList;
    private List<Integer> mesList = new ArrayList<>();
    private List<Integer> anoList = new ArrayList<>();
    private Integer selectedMes = calendar.get(Calendar.MONTH) + 1;
    private Integer selectedAno = calendar.get(Calendar.YEAR);
    private int listCount;

    public NisSinLecturaBean() {
        try {
            int mesActual = calendar.get(Calendar.MONTH) + 1;

            for (int i = 1; i > -11; i--) {
                mesList.add(mesActual);
                mesActual--;
                if (mesActual == 0) {
                    mesActual = 12;
                }
            }

            int anoActual = calendar.get(Calendar.YEAR);
            for (int i = anoActual; i > anoActual - 3; i--) {
                anoList.add(i);
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    @PostConstruct
    private void init() {
        getNisSinLecturaList();
    }

    public List<NisSinLecturaModel> getNisSinLecturaList() {
        try {
            nisSinLecturaList = tblIndUsiRegistroLecturasRepository.getNisSinLectura(selectedMes, selectedAno);
            System.out.println(nisSinLecturaList.size());
            return nisSinLecturaList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    public String formatNis(String s) {
        try {
            return s.substring(0, 1) + "-" + s.substring(1, 4) + "-" + s.substring(4, 7);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return "error";
        }
    }

    /**
     * @return the listCount
     */
    public int getListCount() {
        return listCount;
    }

    /**
     * @param listCount the listCount to set
     */
    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    /**
     * @return the nisSinLecturaFilteredList
     */
    public List<NisSinLecturaModel> getNisSinLecturaFilteredList() {
        return nisSinLecturaFilteredList;
    }

    /**
     * @param nisSinLecturaFilteredList the nisSinLecturaFilteredList to set
     */
    public void setNisSinLecturaFilteredList(List<NisSinLecturaModel> nisSinLecturaFilteredList) {
        this.nisSinLecturaFilteredList = nisSinLecturaFilteredList;
    }

    /**
     * @return the mesList
     */
    public List<Integer> getMesList() {
        return mesList;
    }

    /**
     * @param mesList the mesList to set
     */
    public void setMesList(List<Integer> mesList) {
        this.mesList = mesList;
    }

    /**
     * @return the anoList
     */
    public List<Integer> getAnoList() {
        return anoList;
    }

    /**
     * @param anoList the anoList to set
     */
    public void setAnoList(List<Integer> anoList) {
        this.anoList = anoList;
    }

    /**
     * @return the selectedMes
     */
    public Integer getSelectedMes() {
        return selectedMes;
    }

    /**
     * @param selectedMes the selectedMes to set
     */
    public void setSelectedMes(Integer selectedMes) {
        this.selectedMes = selectedMes;
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

}
