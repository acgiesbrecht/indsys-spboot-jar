/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.usi.controller;

import com.chortitzer.web.common.CommonBean;
import com.chortitzer.web.usi.domain.ConsumoNisModel;
import com.chortitzer.web.usi.repository.TblIndUsiRegistroLecturasRepository;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class ConsumoNisBean {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TblIndUsiRegistroLecturasRepository tblIndUsiRegistroLecturasRepository;
    Calendar calendar = Calendar.getInstance();
    List<ConsumoNisModel> consumoNisList;
    private List<ConsumoNisModel> consumoNisFilteredList;
    private List<Integer> mesList = new ArrayList<>();
    private List<Integer> anoList = new ArrayList<>();
    private Integer selectedMes = calendar.get(Calendar.MONTH) + 1;
    private Integer selectedAno = calendar.get(Calendar.YEAR);

    private String mesLetras;

    public ConsumoNisBean() {
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
        getConsumoNisList();
    }

    public List<ConsumoNisModel> getConsumoNisList() {
        try {
            List<Object[]> list = tblIndUsiRegistroLecturasRepository.getConsumoNis(selectedMes, selectedAno);
            consumoNisList = new ArrayList<>();
            list.stream().forEach((o) -> {
                ConsumoNisModel cnm = new ConsumoNisModel();
                cnm.setNis(o[0].toString());
                final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    cnm.setFechahora(format.parse(o[1].toString()));
                } catch (Exception ex) {
                    LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
                }
                cnm.setNrserie((BigInteger) o[2]);
                cnm.setUsuario(o[3].toString());
                cnm.setKwh((int) o[4]);
                cnm.setKvar((int) o[5]);
                cnm.setConsumo(o[6] != null ? (int) o[6] : 0);                
                consumoNisList.add(cnm);
            });
            return consumoNisList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    public String formatNis(String s) {
        if (s.length() >= 6) {
            return s.substring(0, 1) + "-" + s.substring(1, 4) + "-" + s.substring(4, 7);
        } else {
            return "ERROR";
        }
    }

    /**
     * @return the consumoNisFilteredList
     */
    public List<ConsumoNisModel> getConsumoNisFilteredList() {
        return consumoNisFilteredList;
    }

    /**
     * @param consumoNisFilteredList the consumoNisFilteredList to set
     */
    public void setConsumoNisFilteredList(List<ConsumoNisModel> consumoNisFilteredList) {
        this.consumoNisFilteredList = consumoNisFilteredList;
    }

    /**
     * @return the mesLetras
     */
    public String getMesLetras() {
        try {
            CommonBean commonBean = new CommonBean();
            return commonBean.mesInttoString(this.getSelectedMes());
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return "error";
        }
    }

    /**
     * @param mesLetras the mesLetras to set
     */
    public void setMesLetras(String mesLetras) {
        this.mesLetras = mesLetras;
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
