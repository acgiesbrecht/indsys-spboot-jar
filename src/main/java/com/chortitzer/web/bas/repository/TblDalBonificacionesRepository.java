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
import com.chortitzer.web.bas.domain.TblDalBonificaciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDalBonificacionesRepository extends JpaRepository<TblDalBonificaciones, Integer> {

    /*@Query("SELECT f.tblDalLotes.idProductor,"
            + "                    f.tipo, "
            + "                    COUNT(f.peso), "
            + "                    SUM(f.peso), "
            + "                    bv.valor, "
            + "                    SUM(f.peso) * bv.valor "
            + "                FROM TblDalFardosFibra f, TblDalBonificacionValores bv"
            + "                WHERE bv.tblDalBonificaciones = ?1"
            + "                    AND f.tblDalLotes.idProductor.id <> 361 AND f.tblDalLotes.idProductor.id <> 200"
            + "                    AND YEAR(f.fechahora) = YEAR(bv.tblDalBonificaciones.fechahora)"
            + "                ")
    public List<DalBonificacionModel> getBonificacionList(TblDalBonificaciones bonificacion);*/
    @Query(value = "SELECT tblempresa.nombre AS productor,"
            + "                    tbl_dal_fibra_tipos.descripcion AS tipo,"
            + "                    COUNT(tbl_dal_fardos_fibra.peso) AS cantidad,"
            + "                    SUM(tbl_dal_fardos_fibra.peso) AS peso,"
            + "                    tbl_dal_bonificacion_valores.valor AS bonificacion_kg,"
            + "                    SUM(tbl_dal_fardos_fibra.peso) * tbl_dal_bonificacion_valores.valor AS bonificacion_total"
            + "                FROM tbl_dal_fardos_fibra, tbl_dal_lotes, tblempresa, tbl_dal_fibra_tipos, tbl_dal_bonificacion_valores, tbl_dal_bonificaciones"
            + "                WHERE tbl_dal_fardos_fibra.id_lote = tbl_dal_lotes.id"
            + "                    AND tblempresa.id = tbl_dal_lotes.id_productor"
            + "                    AND tbl_dal_fibra_tipos.id = tbl_dal_fardos_fibra.tipo"
            + "                    AND tbl_dal_bonificacion_valores.id_fibra_tipo = tbl_dal_fardos_fibra.tipo"
            + "                    AND tbl_dal_bonificacion_valores.id_bonificacion = tbl_dal_bonificaciones.id"
            + "                    AND tbl_dal_bonificaciones.id = ?1"
            + "                    AND tblempresa.id <> 361 AND tblempresa.id <> 200"
            + "                    AND EXTRACT(YEAR FROM tbl_dal_fardos_fibra.fechahora) = EXTRACT(YEAR FROM tbl_dal_bonificaciones.fechahora)"
            + "                GROUP BY tblempresa.nombre, tbl_dal_fibra_tipos.descripcion, tbl_dal_bonificacion_valores.valor, tbl_dal_fibra_tipos.id, tbl_dal_bonificaciones.fechahora"
            + "                ORDER BY tblempresa.nombre, tbl_dal_fibra_tipos.id", nativeQuery = true)
    public List<Object[]> getBonificacionList(Integer bonificacionId);

}
