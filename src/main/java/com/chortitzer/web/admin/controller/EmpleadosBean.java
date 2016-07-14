/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.controller;

import com.chortitzer.web.admin.domain.RrhhEmpleados;
import com.chortitzer.web.admin.repository.RrhhEmpleadosRepository;
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
public class EmpleadosBean implements Serializable {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;

    @Autowired
    private RrhhEmpleadosRepository rrhhEmpleadosRepository;

    private List<RrhhEmpleados> empleadosList;
    private List<RrhhEmpleados> empleadosFilteredList;

    public EmpleadosBean() {

    }

    @PostConstruct
    private void init() {
        try {
            setEmpleadosList(rrhhEmpleadosRepository.findAll());
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    /**
     * @return the empleadosList
     */
    public List<RrhhEmpleados> getEmpleadosList() {
        return empleadosList;
    }

    /**
     * @param empleadosList the empleadosList to set
     */
    public void setEmpleadosList(List<RrhhEmpleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    /**
     * @return the empleadosFilteredList
     */
    public List<RrhhEmpleados> getEmpleadosFilteredList() {
        return empleadosFilteredList;
    }

    /**
     * @param empleadosFilteredList the empleadosFilteredList to set
     */
    public void setEmpleadosFilteredList(List<RrhhEmpleados> empleadosFilteredList) {
        this.empleadosFilteredList = empleadosFilteredList;
    }

    @Transactional("adminTransactionManager")
    public void onEdit(RowEditEvent event) {
        try {
            RrhhEmpleados row = (RrhhEmpleados) event.getObject();
            rrhhEmpleadosRepository.save(row);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage("dtForm:msgs", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    public void add() {
        RrhhEmpleados e = new RrhhEmpleados();
        empleadosList.add(e);
    }

    @Transactional("adminTransactionManager")
    public void delete(RrhhEmpleados empleado) {
        rrhhEmpleadosRepository.delete(empleado);
        empleadosList = rrhhEmpleadosRepository.findAll();
    }

}
