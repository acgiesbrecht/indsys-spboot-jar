/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.controller;

import com.chortitzer.web.admin.domain.RrhhEmpleados;
import com.chortitzer.web.admin.repository.RrhhEmpleadosRepository;
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
@FacesConverter("com.chortitzer.web.admin.controller.EmpleadosConverter")
public class EmpleadosConverter implements Converter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RrhhEmpleadosRepository rrhhEmpleadosRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0 && StringUtils.isNumeric(value)) {
            return rrhhEmpleadosRepository.findOne(Integer.parseInt(value));
        } else {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof RrhhEmpleados ? ((RrhhEmpleados) value).getId().toString() : "";
    }
}
