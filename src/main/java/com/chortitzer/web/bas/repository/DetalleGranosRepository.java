/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.bas.repository;

/**
 *
 * @author Industria
 */
import com.chortitzer.web.bas.domain.DetalleGranos;
import com.chortitzer.web.bas.domain.Tblproductos;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleGranosRepository extends JpaRepository<DetalleGranos, Integer> {

    @Query("SELECT t FROM Tblproductos t WHERE ("
            + " t.descripcion LIKE '%Maiz %' OR "
            + " t.descripcion LIKE '%Sorgo %' OR "
            + " t.descripcion LIKE '%Soja %' OR "
            + " t.descripcion LIKE '%Trigo %'"
            + ") AND t.materiaprima = 1")
    public List<Tblproductos> getAllGranosProductos();

    @Query("Select t from DetalleGranos t where t.fechahora between ?1 and ?2"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleGranos> getViewByDateRange(Date startDate, Date endDate);

    @Query("Select t from DetalleGranos t where t.fechahora between ?1 and ?2"
            + " and t.idProducto = ?3"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleGranos> getViewByDateRangeAndProducto(Date startDate, Date endDate, Integer idProducto);

    @Query("Select t from DetalleGranos t where t.fechahora between ?1 and ?2"
            + " and t.idProductor = ?3"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleGranos> getViewByDateRangeAndEmpresa(Date startDate, Date endDate, Integer idProductor);

    @Query("Select t from DetalleGranos t where t.fechahora between ?1 and ?2"
            + " and t.idProductor = ?3"
            + " and t.idProducto = ?4"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleGranos> getViewByDateRangeAndEmpresaAndProducto(Date startDate, Date endDate, Integer idProductor, Integer idProducto);

}
