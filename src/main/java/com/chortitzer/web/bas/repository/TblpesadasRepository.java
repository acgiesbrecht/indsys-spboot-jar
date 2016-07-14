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
import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.domain.Tblpesadas;
import com.chortitzer.web.bas.domain.Tblproductos;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblpesadasRepository extends JpaRepository<Tblpesadas, Integer> {

    @Query("select t from Tblpesadas t where t.fechahora between ?1 and ?2")
    public List<Tblpesadas> getByDateRange(Date startDate, Date endDate);

    @Query("select t from Tblpesadas t where t.productoid = ?3 and t.fechahora between ?1 and ?2")
    public List<Tblpesadas> getByDateRangeAndProducto(Date startDate, Date endDate, Tblproductos producto);

    @Query("SELECT t.id, t.fechahora, "
            + "    (t.bruto-t.tara) AS peso, "
            + "    ((t.bruto-t.tara)*0.2) AS pesoanticipo, "
            + "    ((t.bruto-t.tara)*0.2*t.precioGsPorKg) AS pago "
            + " FROM Tblpesadas t "
            + " WHERE (t.productoid.id = 64 "
            + "  OR t.productoid.id = 83) "
            + "   AND YEAR(t.fechahora) = ?1"
            + "   AND t.bruto > -1 AND t.tara > -1")
    public List<Object[]> getLiquidacionByAno(Integer ano);

    @Query("SELECT t.id, t.fechahora, "
            + "                        (t.bruto-t.tara) AS peso, "
            + "                        ((t.bruto-t.tara)*0.2) AS pesoanticipo, "
            + "                        ((t.bruto-t.tara)*0.2*precioGsPorKg) AS pago "
            + "               FROM Tblpesadas t "
            + "               WHERE (t.productoid.id = 64 "
            + "                        OR t.productoid.id = 83) "
            + "                        AND t.idLote = ?1"
            + "                        AND t.bruto > -1 AND t.tara > -1")
    public List<Object[]> getLiquidacionByLote(Integer lote);

    @Query("SELECT t.id, t.fechahora, "
            + "    (t.bruto-t.tara) AS peso, "
            + "    ((t.bruto-t.tara)*0.2) AS pesoanticipo, "
            + "    ((t.bruto-t.tara)*0.2*t.precioGsPorKg) AS pago "
            + " FROM Tblpesadas t "
            + " WHERE (t.productoid.id = 64 "
            + "  OR t.productoid.id = 83) "
            + "   AND t.empresaid = ?2"
            + "   AND YEAR(t.fechahora) = ?1"
            + "   AND t.bruto > -1 AND t.tara > -1")
    public List<Object[]> getLiquidacionByAnoAndEmpresa(Integer ano, Tblempresa empresaId);

}
