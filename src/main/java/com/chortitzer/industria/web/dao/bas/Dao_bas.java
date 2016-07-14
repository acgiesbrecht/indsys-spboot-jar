/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.bas;

/**
 *
 * @author Industria
 */
import com.chortitzer.web.bas.domain.DalLiquidacionModel;
import com.chortitzer.web.bas.domain.DalResumenFibraModel;
import com.chortitzer.web.bas.domain.TblDalLotes;
import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.domain.Tblproductos;
import java.util.Date;
import java.util.List;

public interface Dao_bas {

    <T> List<T> getAll(Class<T> klass);

    <T> T save(T t);

    <T> void delete(T t);

    <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate);

    <T> List<T> getByDateRangeAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblproductos producto);

    <T> List<T> getByDateRangeAndEmpresa(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa);

    <T> List<T> getByDateRangeAndEmpresaAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto);

    <T> T findByPk(Class<T> type, Object o);

    List<DalResumenFibraModel> getResumenByLote(String lote);

    List<DalResumenFibraModel> getResumenByAno(int ano);

    List<DalLiquidacionModel> getLiquidacionByLote(String lote);

    List<DalLiquidacionModel> getLiquidacionByAno(int ano);

    List<Integer> getDalAnosAll();

    List<TblDalLotes> getLotesByEmpresaAndAno(int idEmpresa, int ano);

    List<Tblproductos> getAllSesammoProductos();

    List<Tblproductos> getAllGranosProductos();

    <T> List<T> getViewByDateRangeAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblproductos producto);

    <T> List<T> getViewByDateRangeAndEmpresa(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa);

    <T> List<T> getViewByDateRangeAndEmpresaAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto);
}
