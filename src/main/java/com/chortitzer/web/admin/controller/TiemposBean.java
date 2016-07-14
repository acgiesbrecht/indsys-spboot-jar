/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.controller;

import com.chortitzer.web.admin.domain.RrhhEmpleados;
import com.chortitzer.web.admin.domain.RrhhMarcas;
import com.chortitzer.web.admin.domain.TiemposModel;
import com.chortitzer.web.admin.repository.RrhhEmpleadosRepository;
import com.chortitzer.web.admin.repository.RrhhMarcasRepository;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.time.DateUtils;
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
public class TiemposBean implements Serializable {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    Calendar calendar = Calendar.getInstance();
    @Autowired
    RrhhMarcasRepository rrhhMarcasRepo;

    @Autowired
    RrhhEmpleadosRepository rrhhEmpleadosRepo;

    private List<TiemposModel> tiemposList;
    private List<TiemposModel> tiemposFilteredList;

    private List<RrhhEmpleados> empleadosList;
    private List<RrhhEmpleados> empleadosFilteredList;
    private RrhhEmpleados selectedEmpleado;
    private Date fechaDesde;
    private Date fechaHasta;
    private int selectedRango;
    private String periodoPasado;

    public TiemposBean() {

    }

    @PostConstruct
    private void init() {
        try {
            processMarcasFile();
            setSelectedRango(1);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));

        }
    }

    /**
     * @return the tiemposList
     */
    public List<TiemposModel> getTiemposList() {
        try {
            List<RrhhMarcas> lm = rrhhMarcasRepo.getByDateRangeAndEmpleado(fechaDesde, fechaHasta, selectedEmpleado);
            tiemposList = new ArrayList<>();
            Date hoy = new Date();
            if (lm.size() > 0) {
                TiemposModel tiempo = new TiemposModel();
                int count = 0;
                for (RrhhMarcas m : lm) {
                    tiempo.setEmpleado(selectedEmpleado);
                    if ((m.getEntradaSalida() == 1 && count > 0) || (m.getEntradaSalida() == 2 && count > 1)) {
                        tiemposList.add(tiempo);
                        tiempo = new TiemposModel();
                        count = 0;
                    }
                    if (tiempo.getHoraEntrada() == null && m.getEntradaSalida() == 1) {
                        tiempo.setHoraEntrada(m.getFechahora());
                        count++;
                        //Mostrar Entrada si es de mas de 24 anterior al time actual
                        if (m.equals(lm.get(lm.size() - 1))) {

                            long i = hoy.getTime();
                            long f = m.getFechahora().getTime();
                            if ((i - f) / 1000 > 3600 * 24) {
                                tiemposList.add(tiempo);
                                tiempo = new TiemposModel();
                                count = 0;
                            }
                        }
                    }
                    if (tiempo.getHoraSalida() == null && m.getEntradaSalida() == 2) {
                        tiempo.setHoraSalida(m.getFechahora());
                        count++;
                        if (m.equals(lm.get(0))) {
                            //Buscar si hay entrada en las 24 horas anteriores si el primer registro es salida
                            Date diaAnterior = DateUtils.addHours(m.getFechahora(), -24);

                            List<RrhhMarcas> lmt = rrhhMarcasRepo.getByDateRangeAndEmpleado(diaAnterior, DateUtils.addSeconds(m.getFechahora(), -1), selectedEmpleado);
                            if (lmt.size() > 0) {
                                if (lmt.get(lmt.size() - 1).getEntradaSalida() == 1) {
                                    tiempo.setHoraEntrada(lmt.get(lmt.size() - 1).getFechahora());
                                    count++;
                                }
                            }
                        }
                    }
                    if (tiempo.getHoraEntrada() != null && tiempo.getHoraSalida() != null) {
                        tiempo.setTiempo((int) (long) (tiempo.getHoraSalida().getTime() - tiempo.getHoraEntrada().getTime()) / 1000);
                    }
                }

            }


            /*List<Object[]> lo = rrhhMarcasRepo.getTiempos();

        lo.stream().forEach((o) -> {
            TiemposModel tm = new TiemposModel();
            tm.setEmpleado(o[0].toString());
            tm.setHoraEntrada((Date) o[1]);
            tm.setHoraSalida((Date) o[2]);
            tm.setTiempo(((Double) o[3]).intValue());
            tiemposList.add(tm);
        });*/
            return tiemposList;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param tiemposList the tiemposList to set
     */
    public void setTiemposList(List<TiemposModel> tiemposList) {
        this.tiemposList = tiemposList;
    }

    /**
     * @return the tiemposFilteredList
     */
    public List<TiemposModel> getTiemposFilteredList() {
        return tiemposFilteredList;
    }

    /**
     * @param tiemposFilteredList the tiemposFilteredList to set
     */
    public void setTiemposFilteredList(List<TiemposModel> tiemposFilteredList) {
        this.tiemposFilteredList = tiemposFilteredList;
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
        try {
            this.selectedRango = selectedRango;
            if (selectedRango != 0) {
                setFechaHasta(new Date());
                switch (selectedRango) {
                    case 1:
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        cal.add(Calendar.MONTH, -1);
                        cal.set(Calendar.DAY_OF_MONTH, 16);
                        cal.set(Calendar.HOUR, 0);
                        cal.set(Calendar.MINUTE, 0);
                        setFechaDesde(cal.getTime());
                        cal.add(Calendar.MONTH, 1);
                        cal.add(Calendar.MINUTE, -1);
                        setFechaHasta(cal.getTime());
                        break;
                    case 2:
                        setFechaDesde(DateUtils.truncate(new Date(), Calendar.MONTH));
                        break;
                    case 3:
                        setFechaDesde(DateUtils.truncate(new Date(), Calendar.YEAR));
                        break;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    public String convertInterval(Integer interval) {
        try {
            if (interval != null) {
                long minute = (interval / 60) % 60;
                long hour = (interval / (60 * 60)) % 24;
                return String.format("%02d:%02d", hour, minute);
            } else {
                return "N/A";
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @return the empleadosList
     */
    public List<RrhhEmpleados> getEmpleadosList() {
        this.empleadosList = rrhhEmpleadosRepo.findAll();
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

    public void processMarcasFile() {
        try {
            FacesContext.getCurrentInstance().addMessage("form:msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cargando..."));
            String fileName = "C:\\Users\\adriang\\Documents\\Synel\\SYBridge\\Data\\Collect.dat";
            if (Files.exists(Paths.get(fileName))) {

                Path file = Paths.get(fileName);
                Integer countNoRegistrados = 0;
                Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());
                while (scanner.hasNextLine()) {
                    String aLine = scanner.nextLine();
                    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHmm");
                    Date d = formatter.parse(aLine.substring(10, 16) + aLine.substring(29, 33));
                    //System.out.println(d.toString());
                    //System.out.println(aLine.substring(18, 29));
                    //System.out.println(aLine.substring(16, 17));

                    RrhhMarcas rrhhMarcas = new RrhhMarcas();
                    rrhhMarcas.setFechahora(d);
                    rrhhMarcas.setNroEmpleado(rrhhEmpleadosRepo.findOne(Integer.parseInt(aLine.substring(18, 29))));
                    rrhhMarcas.setEntradaSalida(Integer.parseInt(aLine.substring(17, 18)));

                    //if (em.find(RrhhTiempos.class, rrhhTiemposPK) == null) {
                    if (rrhhMarcas.getNroEmpleado() == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Existen registros para empleados no identificacdos en la base de datos! - Nro.: " + aLine.substring(18, 29)));
                        countNoRegistrados += 1;
                        System.out.println("Empleado sin registro: " + aLine.substring(18, 29));
                    } else {
                        rrhhMarcasRepo.save(rrhhMarcas);
                    }

                }
                limpiarMarcasDobles();
                if (countNoRegistrados == 0) {
                    Files.deleteIfExists(file);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    void limpiarMarcasDobles() {
        try {
            List<RrhhMarcas> lm = rrhhMarcasRepo.findAllByOrderByFechahoraAsc();

            for (int i = 1; i < lm.size(); i++) {
                if (lm.get(i).getNroEmpleado() == lm.get(i - 1).getNroEmpleado()) {
                    if (lm.get(i).getFechahora().getTime() - lm.get(i - 1).getFechahora().getTime() <= 1000 * 60 * 2) {
                        System.out.println(lm.get(i).getNroEmpleado().getNombre() + " - " + lm.get(i).getFechahora().toString() + " " + lm.get(i - 1).getFechahora() + " - " + String.valueOf(lm.get(i).getFechahora().getTime() - lm.get(i - 1).getFechahora().getTime()));
                        rrhhMarcasRepo.delete(lm.get(i - 1));
                        //lm = rrhhMarcasRepo.findAllByOrderByFechahoraAsc();
                        lm.remove(i - 1);
                        i -= 3;
                    }
                }
            }
            getTiemposList();
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }

    }

    /**
     * @return the selectedEmpleado
     */
    public RrhhEmpleados getSelectedEmpleado() {
        return selectedEmpleado;
    }

    /**
     * @param selectedEmpleado the selectedEmpleado to set
     */
    public void setSelectedEmpleado(RrhhEmpleados selectedEmpleado) {
        this.selectedEmpleado = selectedEmpleado;
    }

    /**
     * @return the periodoPasado
     */
    public String getPeriodoPasado() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, 16);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            Date desde = DateUtils.truncate(cal.getTime(), Calendar.HOUR_OF_DAY);
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.MINUTE, -1);
            Date hasta = DateUtils.truncate(cal.getTime(), Calendar.HOUR_OF_DAY);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM");
            periodoPasado = sdf.format(desde) + " - " + sdf.format(hasta);
            return periodoPasado;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            return null;
        }
    }

    /**
     * @param periodoPasado the periodoPasado to set
     */
    public void setPeriodoPasado(String periodoPasado) {
        this.periodoPasado = periodoPasado;
    }

}
