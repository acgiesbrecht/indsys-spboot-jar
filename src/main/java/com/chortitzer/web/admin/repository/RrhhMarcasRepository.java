/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.admin.repository;

/**
 *
 * @author Industria
 */
import com.chortitzer.web.admin.domain.RrhhEmpleados;
import com.chortitzer.web.admin.domain.RrhhMarcas;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RrhhMarcasRepository extends JpaRepository<RrhhMarcas, Date> {

    @Query("select t from RrhhMarcas t where t.fechahora between ?1 and ?2")
    public List<RrhhMarcas> getByDateRange(Date startDate, Date endDate);

    @Query("select t from RrhhMarcas t where t.nroEmpleado = ?3 and t.fechahora between ?1 and ?2 order by t.fechahora")
    public List<RrhhMarcas> getByDateRangeAndEmpleado(Date startDate, Date endDate, RrhhEmpleados empleado);

    @Query(value = "select nro_empleado, inicio, fin, intervalo"
            + " from (select nro_empleado, "
            + "     lag(fechahora) over (partition by nro_empleado order by fechahora) as inicio, "
            + "     fechahora as fin, "
            + "     EXTRACT(epoch from (fechahora - lag(fechahora) over (partition by nro_empleado order by fechahora))) as intervalo,"
            + "     entrada_salida"
            + " from rrhh_marcas    "
            + " order by fechahora) t"
            + " where entrada_salida = 2 and intervalo < 24*3600", nativeQuery = true)
    public List<Object[]> getTiempos();

    public List<RrhhMarcas> findAllByOrderByFechahoraAsc();

}
