/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.fps.controller;

import com.chortitzer.industria.web.dao.fps.Dao_fps;
import com.chortitzer.web.fps.domain.FpsAvgValueModel;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.time.DateUtils;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class VaporBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    Dao_fps dao;

    private LineChartModel dataModel;

    private Date fechaDesde;
    private Date fechaHasta;
    private int selectedRango = 1;

    Calendar calendar = Calendar.getInstance();

    @PostConstruct
    private void init() {
        setSelectedRango(1);

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
     * @return the selectedRango
     */
    public int getSelectedRango() {
        return selectedRango;
    }

    /**
     * @param selectedRango the selectedRango to set
     */
    public void setSelectedRango(int selectedRango) {
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
        createDataModel();
    }

    private void createDataModel() {
        dataModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Temp");

        List<FpsAvgValueModel> dataList = dao.getAvgFpsParamByDateRange(25, fechaDesde, fechaHasta);
        int i = 1;
        for (FpsAvgValueModel d : dataList) {
            //series1.set(d.getFechahora().getTime(),  Integer.valueOf((int) Math.round(d.getValor())) );
            series1.set(String.valueOf(i), Integer.valueOf((int) Math.round(d.getValor())));
            i++;
            //System.out.println(d.getFechahora().getTime() + ", " + Integer.valueOf((int) Math.round(d.getValor())).toString());
            System.out.println(String.valueOf(i) + ", " + Integer.valueOf((int) Math.round(d.getValor())).toString());

        }

        dataModel.addSeries(series1);

        //dataModel.setTitle("Zoom for Details");
        //dataModel.setZoom(true);
        /*dataModel.getAxis(AxisType.Y).setLabel("Values");
         DateAxis axis = new DateAxis("Dates");
         axis.setTickAngle(-50);
         //axis.setMax("2014-02-01");
         axis.setTickFormat("%d/%#b/%y - %H:%M");

         dataModel.getAxes().put(AxisType.X, axis);*/
    }

    /**
     * @return the dataModel
     */
    public LineChartModel getDataModel() {
        return dataModel;
    }

    /**
     * @param dataModel the dataModel to set
     */
    public void setDataModel(LineChartModel dataModel) {
        this.dataModel = dataModel;
    }

}
