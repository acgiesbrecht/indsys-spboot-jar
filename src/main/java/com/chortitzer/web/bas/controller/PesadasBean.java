/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.controller;

import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.domain.Tblpesadas;
import com.chortitzer.web.bas.domain.Tblproductos;
import com.chortitzer.web.bas.repository.TblempresaRepository;
import com.chortitzer.web.bas.repository.TblpesadasRepository;
import com.chortitzer.web.bas.repository.TblproductosRepository;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class PesadasBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TblpesadasRepository tblpesadasRepository;

    @Autowired
    TblproductosRepository tblproductosRepository;

    @Autowired
    TblempresaRepository tblempresaRepository;

    private List<Tblpesadas> pesadasList;
    private List<Tblpesadas> pesadasFilteredList;
    private List<Tblempresa> empresaList;
    private List<Tblproductos> productosList;
    private Date fechaDesde;
    private Date fechaHasta;
    private Tblempresa selectedEmpresa;
    private Tblproductos selectedProducto;
    private int selectedRango = 1;
    private long totalBruto;
    private long totalTara;
    private long totalNeto;

    Calendar calendar = Calendar.getInstance();

    @PostConstruct
    private void init() {
        try {
            setSelectedRango(1);
            getEmpresaList();
            getProductosList();
            getPesadasList();
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    /**
     * @return the pesadasList
     */
    public List<Tblpesadas> getPesadasList() {
        try {
            if (selectedEmpresa == null && getSelectedProducto() == null) {
                pesadasList = tblpesadasRepository.getByDateRange(fechaDesde, fechaHasta);
            } else if (selectedEmpresa == null && getSelectedProducto() != null) {
                pesadasList = tblpesadasRepository.getByDateRangeAndProducto(fechaDesde, fechaHasta, getSelectedProducto());
            } else if (selectedEmpresa != null && getSelectedProducto() == null) {
                // pesadasList = tblpesadasRepository.getByDateRangeAndEmpresa(fechaDesde, fechaHasta, selectedEmpresa);
            } else if (selectedEmpresa != null && getSelectedProducto() != null) {
                // pesadasList = tblpesadasRepository.getByDateRangeAndEmpresaAndProducto(fechaDesde, fechaHasta, selectedEmpresa, getSelectedProducto());
            }
            getTotalBruto();
            getTotalTara();
            getTotalNeto();
            return pesadasList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param pesadasList the pesadasList to set
     */
    public void setPesadasList(List<Tblpesadas> pesadasList) {
        this.pesadasList = pesadasList;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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
     * @return the selectedProducto
     */
    public Tblproductos getSelectedProducto() {
        return selectedProducto;
    }

    /**
     * @param selectedProducto the selectedProducto to set
     */
    public void setSelectedProducto(Tblproductos selectedProducto) {
        this.selectedProducto = selectedProducto;
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
     * @return the productosList
     */
    public List<Tblproductos> getProductosList() {
        try {
            productosList = tblproductosRepository.findAll();
            return productosList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param productosList the productosList to set
     */
    public void setProductosList(List<Tblproductos> productosList) {
        this.productosList = productosList;
    }

    /**
     * @return the totalBruto
     */
    public long getTotalBruto() {
        try {
            totalBruto = 0;
            /*for (Tblpesadas d : pesadasList) {
         totalBruto += d.getBruto();
         }*/
            totalBruto = pesadasList.stream().mapToLong(x -> x.getBruto()).sum();
            return totalBruto;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return -1;
        }
    }

    /**
     * @param totalBruto the totalBruto to set
     */
    public void setTotalBruto(long totalBruto) {
        this.totalBruto = totalBruto;
    }

    /**
     * @return the totalTara
     */
    public long getTotalTara() {
        try {
            totalTara = 0;
            for (Tblpesadas d : pesadasList) {
                totalTara += d.getTara();
            }
            return totalTara;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return -1;
        }
    }

    /**
     * @param totalTara the totalTara to set
     */
    public void setTotalTara(long totalTara) {
        this.totalTara = totalTara;
    }

    /**
     * @return the totalNeto
     */
    public long getTotalNeto() {
        try {
            totalNeto = getTotalBruto() - totalTara;
            return totalNeto;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return -1;
        }
    }

    /**
     * @param totalNeto the totalNeto to set
     */
    public void setTotalNeto(long totalNeto) {
        this.totalNeto = totalNeto;
    }

    /**
     * @return the selectedRango
     */
    public int getSelectedRango() {
        return selectedRango;
    }

    /**
     * @param selectedRango the selectedRango to set
     */
    public void setSelectedRango(int selectedRango) {
        try {
            this.selectedRango = selectedRango;
            if (selectedRango != 0) {
                setFechaHasta(new Date());
                switch (selectedRango) {
                    case 1:
                        setFechaDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
                        break;
                    case 2:
                        fechaDesde = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
                        setFechaDesde(DateUtils.addDays(fechaDesde, -1));
                        fechaHasta = DateUtils.addDays(fechaDesde, 1);
                        setFechaHasta(DateUtils.addMilliseconds(fechaHasta, -1));
                        break;
                    case 3:
                        calendar = DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH);
                        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                        setFechaDesde(calendar.getTime());
                        break;
                    case 4:
                        setFechaDesde(DateUtils.truncate(new Date(), Calendar.MONTH));
                        break;
                    case 5:
                        setFechaDesde(DateUtils.truncate(new Date(), Calendar.YEAR));
                        break;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    /**
     * @return the pesadasFilteredList
     */
    public List<Tblpesadas> getPesadasFilteredList() {
        return pesadasFilteredList;
    }

    /**
     * @param pesadasFilteredList the pesadasFilteredList to set
     */
    public void setPesadasFilteredList(List<Tblpesadas> pesadasFilteredList) {
        this.pesadasFilteredList = pesadasFilteredList;
    }

}
