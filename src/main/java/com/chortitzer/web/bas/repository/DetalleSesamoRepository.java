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
import com.chortitzer.web.bas.domain.DetalleSesamo;
import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.domain.Tblproductos;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleSesamoRepository extends JpaRepository<DetalleSesamo, Integer> {

    @Query("SELECT t FROM Tblproductos t WHERE t.descripcion LIKE '%Sesamo %' AND t.materiaprima = 1")
    public List<Tblproductos> getAllSesammoProductos();

    @Query("Select t from DetalleSesamo t where t.fechahora between ?1 and ?2"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleSesamo> getViewByDateRange(Date startDate, Date endDate);

    @Query("Select t from DetalleSesamo t where t.fechahora between ?1 and ?2"
            + " and t.idProducto = ?3"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleSesamo> getViewByDateRangeAndProducto(Date startDate, Date endDate, Tblproductos producto);

    @Query("Select t from DetalleSesamo t where t.fechahora between ?1 and ?2"
            + " and t.idProductor = ?3"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleSesamo> getViewByDateRangeAndEmpresa(Date startDate, Date endDate, Tblempresa empresa);

    @Query("Select t from DetalleSesamo t where t.fechahora between ?1 and ?2"
            + " and t.idProductor = ?3"
            + " and t.idProducto = ?4"
            + " and t.bruto > -1 and t.tara > -1")
    public List<DetalleSesamo> getViewByDateRangeAndEmpresaAndProducto(Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto);

}
