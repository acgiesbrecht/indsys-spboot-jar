/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.controller;

import com.chortitzer.web.admin.domain.RrhhMarcas;
import com.chortitzer.web.admin.repository.RrhhMarcasRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Industria
 */
@Controller
@Scope("view")
public class MarcasBean implements Serializable {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;

    @Autowired
    RrhhMarcasRepository rrhhMarcasRepository;

    private List<RrhhMarcas> marcasList;
    private List<RrhhMarcas> marcasFilteredList;

    public MarcasBean() {

    }

    @PostConstruct
    private void init() {
        setMarcasList(rrhhMarcasRepository.findAll());
    }

    /**
     * @return the marcasList
     */
    public List<RrhhMarcas> getMarcasList() {
        return marcasList;
    }

    /**
     * @param marcasList the marcasList to set
     */
    public void setMarcasList(List<RrhhMarcas> marcasList) {
        this.marcasList = marcasList;
    }

    /**
     * @return the marcasFilteredList
     */
    public List<RrhhMarcas> getMarcasFilteredList() {
        return marcasFilteredList;
    }

    /**
     * @param marcasFilteredList the marcasFilteredList to set
     */
    public void setMarcasFilteredList(List<RrhhMarcas> marcasFilteredList) {
        this.marcasFilteredList = marcasFilteredList;
    }

    @Transactional("adminTransactionManager")
    public void onEdit(RowEditEvent event) {
        try {
            RrhhMarcas row = (RrhhMarcas) event.getObject();
            rrhhMarcasRepository.save(row);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    public void add() {
        try {
            RrhhMarcas e = new RrhhMarcas();
            marcasList.add(e);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    @Transactional("adminTransactionManager")
    public void delete(RrhhMarcas marca) {
        try {
            rrhhMarcasRepository.delete(marca);
            marcasList = rrhhMarcasRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    public String getES(Integer value) {
        switch (value) {
            case 1:
                return "Entrada";
            case 2:
                return "Salida";
            default:
                return "ERROR";
        }
    }

}
