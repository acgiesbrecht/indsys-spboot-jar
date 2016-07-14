/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.controller;

import com.chortitzer.web.admin.repository.RrhhEmpleadosRepository;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Industria
 */
@Component
@Scope("view")
@FacesConverter("com.chortitzer.web.admin.controller.EntradaSalidaConverter")
public class EntradaSalidaConverter implements Converter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RrhhEmpleadosRepository rrhhEmpleadosRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value != null && value.trim().length() > 0 && StringUtils.isNumeric(value)) {
                if (value.equals("Entrada")) {
                    return 1;
                } else if (value.equals("Salida")) {
                    return 2;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            switch ((int) value) {
                case 1:
                    return "Entrada";
                case 2:
                    return "Salida";
                default:
                    return "ERROR";
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return "ERROR";
        }
    }
}
