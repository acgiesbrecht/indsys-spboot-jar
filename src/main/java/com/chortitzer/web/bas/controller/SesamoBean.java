/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.controller;

import com.chortitzer.web.bas.domain.DetalleSesamo;
import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.domain.Tblproductos;
import com.chortitzer.web.bas.repository.DetalleSesamoRepository;
import com.chortitzer.web.bas.repository.TblempresaRepository;
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
public class SesamoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DetalleSesamoRepository detalleSesamoRepository;

    @Autowired
    TblempresaRepository tblempresaRepository;

    private List<DetalleSesamo> sesamoList;
    private List<DetalleSesamo> sesamoFilteredList;
    private List<Tblempresa> empresaList;
    private List<Tblproductos> productosSesamoList;
    private Date fechaDesde;
    private Date fechaHasta;
    private Tblempresa selectedEmpresa;
    private Tblproductos selectedProducto;
    private int selectedRango = 1;
    private long totalBruto;
    private long totalTara;
    private long totalNeto;
    private double averageAcidez;

    Calendar calendar = Calendar.getInstance();

    @PostConstruct
    private void init() {
        setSelectedRango(1);
        getEmpresaList();
        getProductosSesamoList();
        getSesamoList();
    }

    /**
     * @return the pesadasList
     */
    public List<DetalleSesamo> getSesamoList() {
        try {
            if (selectedEmpresa == null && getSelectedProducto() == null) {
                sesamoList = detalleSesamoRepository.getViewByDateRange(fechaDesde, fechaHasta);
            } else if (selectedEmpresa == null && getSelectedProducto() != null) {
                sesamoList = detalleSesamoRepository.getViewByDateRangeAndProducto(fechaDesde, fechaHasta, selectedProducto);
            } else if (selectedEmpresa != null && getSelectedProducto() == null) {
                sesamoList = detalleSesamoRepository.getViewByDateRangeAndEmpresa(fechaDesde, fechaHasta, selectedEmpresa);
            } else if (selectedEmpresa != null && getSelectedProducto() != null) {
                sesamoList = detalleSesamoRepository.getViewByDateRangeAndEmpresaAndProducto(fechaDesde, fechaHasta, selectedEmpresa, selectedProducto);
            }
            getTotalBruto();
            getTotalTara();
            getTotalNeto();
            return sesamoList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param sesamoList the pesadasList to set
     */
    public void setSesamoList(List<DetalleSesamo> sesamoList) {
        this.sesamoList = sesamoList;
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
     * @return the totalBruto
     */
    public long getTotalBruto() {
        totalBruto = 0;
        /*for (Tblpesadas d : pesadasList) {
         totalBruto += d.getBruto();
         }*/
        totalBruto = sesamoList.stream().mapToLong(x -> x.getBruto()).sum();
        return totalBruto;
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
        totalTara = 0;
        for (DetalleSesamo d : sesamoList) {
            totalTara += d.getTara();
        }
        return totalTara;
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
        totalNeto = 0;
        for (DetalleSesamo d : sesamoList) {
            totalNeto += d.getNeto();
        }
        return totalNeto;
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
     * @return the sesamoFilteredList
     */
    public List<DetalleSesamo> getSesamoFilteredList() {
        return sesamoFilteredList;
    }

    /**
     * @param sesamoFilteredList the pesadasFilteredList to set
     */
    public void setSesamoFilteredList(List<DetalleSesamo> sesamoFilteredList) {
        this.sesamoFilteredList = sesamoFilteredList;
    }

    /**
     * @return the productosSesamoList
     */
    public List<Tblproductos> getProductosSesamoList() {
        try {
            productosSesamoList = detalleSesamoRepository.getAllSesammoProductos();
            return productosSesamoList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param productosSesamoList the productosSesamoList to set
     */
    public void setProductosSesamoList(List<Tblproductos> productosSesamoList) {
        this.productosSesamoList = productosSesamoList;
    }

    /**
     * @return the averageAcidez
     */
    public double getAverageAcidez() {
        try {
            if (sesamoList.size() > 0) {
                averageAcidez = sesamoList.stream().mapToDouble(x -> x.getAcidez()).average().getAsDouble();
                return averageAcidez;
            } else {
                return 0.0;
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return 0.0;
        }
    }

    /**
     * @param averageAcidez the averageAcidez to set
     */
    public void setAverageAcidez(double averageAcidez) {
        this.averageAcidez = averageAcidez;
    }

}
