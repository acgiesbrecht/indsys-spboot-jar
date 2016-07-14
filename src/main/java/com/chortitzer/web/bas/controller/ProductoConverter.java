/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.controller;

import com.chortitzer.web.bas.domain.Tblproductos;
import com.chortitzer.web.bas.repository.TblproductosRepository;
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
@FacesConverter("com.chortitzer.web.bas.bean.ProductoConverter")
public class ProductoConverter implements Converter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TblproductosRepository tblproductoRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        try {
            if (value != null && value.trim().length() > 0 && StringUtils.isNumeric(value)) {
                return tblproductoRepository.findOne(Integer.parseInt(value));
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
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        try {
            return value instanceof Tblproductos ? ((Tblproductos) value).getId().toString() : "";
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }
}
