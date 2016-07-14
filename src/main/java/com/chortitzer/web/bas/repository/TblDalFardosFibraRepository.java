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
import com.chortitzer.web.bas.domain.TblDalFardosFibra;
import com.chortitzer.web.bas.domain.TblDalFardosFibraPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDalFardosFibraRepository extends JpaRepository<TblDalFardosFibra, TblDalFardosFibraPK> {

    @Query(value = "SELECT descripcion AS tipo, "
            + "                        mic AS micronaire, "
            + "                        count(*)\\:\\:integer AS cantidad, "
            + "                        sum(peso)\\:\\:integer as peso,"
            + "                        SUM(peso)\\:\\:decimal/(SELECT SUM(peso)\\:\\:decimal FROM tbl_dal_fardos_fibra WHERE id_lote= ?1)*100 AS porcentaje"
            + "                FROM"
            + "                    (SELECT CASE WHEN tbl_dal_fardos_fibra.micronaire >= 5 THEN 'Mayor a 5.0'                                 "
            + "                                WHEN tbl_dal_fardos_fibra.micronaire >= 3.5 THEN '3.5 - 4.9'                                                                "
            + "                                ELSE 'Menor a 3.5'"
            + "                    END as mic, tbl_dal_fardos_fibra.tipo, tbl_dal_fardos_fibra.peso, tbl_dal_fibra_tipos.descripcion, tbl_dal_fardos_fibra.id"
            + "                FROM tbl_dal_fardos_fibra, tbl_dal_fibra_tipos"
            + "                WHERE  tbl_dal_fardos_fibra.tipo = tbl_dal_fibra_tipos.id AND id_lote= ?1) AS fardos"
            + "                GROUP BY descripcion, mic"
            + "                ORDER BY descripcion, mic", nativeQuery = true)
    public List<Object[]> getResumenByLote(Integer lote);//DalResumenFibraModel

    @Query(value = "SELECT descripcion AS tipo, "
            + "                        mic AS micronaire, "
            + "                        count(*)\\:\\:integer AS cantidad, "
            + "                        sum(peso)\\:\\:integer as peso,"
            + "                        SUM(peso)\\:\\:decimal/(SELECT SUM(peso)\\:\\:decimal FROM tbl_dal_fardos_fibra WHERE EXTRACT(YEAR FROM tbl_dal_fardos_fibra.fechahora)= ?1)*100 AS porcentaje"
            + "                FROM"
            + "                    (SELECT CASE WHEN tbl_dal_fardos_fibra.micronaire >= 5 THEN 'Mayor a 5.0'                                 "
            + "                                WHEN tbl_dal_fardos_fibra.micronaire >= 3.5 THEN '3.5 - 4.9'                                                                "
            + "                                ELSE 'Menor a 3.5'"
            + "                    END as mic, tbl_dal_fardos_fibra.tipo, tbl_dal_fardos_fibra.peso, tbl_dal_fibra_tipos.descripcion, tbl_dal_fardos_fibra.id"
            + "                FROM tbl_dal_fardos_fibra, tbl_dal_fibra_tipos"
            + "                WHERE  tbl_dal_fardos_fibra.tipo = tbl_dal_fibra_tipos.id AND "
            + " EXTRACT(YEAR FROM tbl_dal_fardos_fibra.fechahora)= ?1) AS fardos"
            + "                GROUP BY descripcion, mic"
            + "                ORDER BY descripcion, mic", nativeQuery = true)
    public List<Object[]> getResumenByAno(int ano); //DalResumenFibraModel

    @Query(value = "SELECT descripcion AS tipo, "
            + "                        mic AS micronaire, "
            + "                        count(*)\\:\\:integer AS cantidad, "
            + "                        sum(peso)\\:\\:integer as peso,"
            + "                        SUM(peso)\\:\\:decimal/(SELECT SUM(peso)\\:\\:decimal FROM tbl_dal_fardos_fibra WHERE EXTRACT(YEAR FROM tbl_dal_fardos_fibra.fechahora)= ?1)*100 AS porcentaje"
            + "                FROM"
            + "                    (SELECT CASE WHEN tbl_dal_fardos_fibra.micronaire >= 5 THEN 'Mayor a 5.0'                                 "
            + "                                WHEN tbl_dal_fardos_fibra.micronaire >= 3.5 THEN '3.5 - 4.9'                                                                "
            + "                                ELSE 'Menor a 3.5'"
            + "                    END as mic, tbl_dal_fardos_fibra.tipo, tbl_dal_fardos_fibra.peso, tbl_dal_fibra_tipos.descripcion, tbl_dal_fardos_fibra.id"
            + "                FROM tbl_dal_fardos_fibra, tbl_dal_fibra_tipos, tbl_dal_lotes"
            + "                WHERE  tbl_dal_fardos_fibra.tipo = tbl_dal_fibra_tipos.id AND "
            + " EXTRACT(YEAR FROM tbl_dal_fardos_fibra.fechahora)= ?1"
            + " AND tbl_dal_lotes.id_productor = ?2 AND tbl_dal_fardos_fibra.id_lote = tbl_dal_lotes.id) AS fardos"
            + "                GROUP BY descripcion, mic"
            + "                ORDER BY descripcion, mic", nativeQuery = true)
    public List<Object[]> getResumenByAnoAndEmpresa(int ano, int empresaId); //DalResumenFibraModel

}
