/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.fps.controller;

import com.chortitzer.industria.web.dao.fps.Dao_fps;
import com.chortitzer.web.fps.domain.TblFpsLotes;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Industria
 */
@Controller
@Scope("view")
public class LotesBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    Dao_fps dao;

    private List<TblFpsLotes> tblFpsLotesList;
    private DataTable dt;

    Calendar calendar = Calendar.getInstance();

    private String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
    private String ano = String.valueOf(calendar.get(Calendar.YEAR));

    public LotesBean() {

    }

    @PostConstruct
    private void init() {
        tblFpsLotesList = dao.getAll(TblFpsLotes.class);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            //dt = (TblFpsLotesList)event.getSource();
            dao.save(tblFpsLotesList.get(event.getRowIndex()));

            if (tblFpsLotesList.get(event.getRowIndex()).getId() == null) {
                tblFpsLotesList = dao.getAll(TblFpsLotes.class);
            }
            System.out.println(tblFpsLotesList.get(event.getRowIndex()).getId());
        }
    }

    /**
     * @return the tblFpsLotesList
     */
    public List<TblFpsLotes> getTblFpsLotesList() {
        return tblFpsLotesList;
    }

    /**
     * @param tblFpsLotesList the tblFpsLotesList to set
     */
    public void setTblFpsLotesList(List<TblFpsLotes> tblFpsLotesList) {
        this.tblFpsLotesList = tblFpsLotesList;
    }

    public void addLote() {
        TblFpsLotes newLote = new TblFpsLotes();
        newLote.setFecha(new Date());
        newLote.setIdFabrica(tblFpsLotesList.get(tblFpsLotesList.size() - 1).getIdFabrica() + 1);
        dao.save(newLote);
        tblFpsLotesList = dao.getAll(TblFpsLotes.class);
        //tblFpsLotesList.add(newLote);
    }

    public void deleteLote(TblFpsLotes lote) {
        dao.delete(lote);
        tblFpsLotesList = dao.getAll(TblFpsLotes.class);
    }

    public void save() {
        dao.save(tblFpsLotesList);

    }

}
