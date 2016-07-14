/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.controller;

import com.chortitzer.web.bas.domain.Precios;
import com.chortitzer.web.bas.repository.PreciosRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class PreciosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PreciosRepository preciosRepository;

    private List<Precios> preciosList;
    private List<Precios> preciosFilteredList;

    @PostConstruct
    private void init() {
        getPreciosList();
    }

    /**
     * @return the preciosList
     */
    public List<Precios> getPreciosList() {
        try {
            preciosList = preciosRepository.findAll();
            return preciosList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param preciosList the preciosList to set
     */
    public void setPreciosList(List<Precios> preciosList) {
        this.preciosList = preciosList;
    }

    /**
     * @return the preciosFilteredList
     */
    public List<Precios> getPreciosFilteredList() {
        return preciosFilteredList;
    }

    /**
     * @param preciosFilteredList the preciosFilteredList to set
     */
    public void setPreciosFilteredList(List<Precios> preciosFilteredList) {
        this.preciosFilteredList = preciosFilteredList;
    }

}
