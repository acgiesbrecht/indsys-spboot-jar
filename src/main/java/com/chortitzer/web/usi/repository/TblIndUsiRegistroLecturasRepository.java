/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.usi.repository;

/**
 *
 * @author Industria
 */
import com.chortitzer.web.usi.domain.NisSinLecturaModel;
import com.chortitzer.web.usi.domain.TblIndUsiRegistroLecturas;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblIndUsiRegistroLecturasRepository extends JpaRepository<TblIndUsiRegistroLecturas, Date> {

    /*@Query(value = "SELECT DISTINCT u.\"NRSERIE\", u.\"NIS\", u.\"USUARIO\" "
            + " FROM nis_usuario u, tbl_ind_usi_registro_lecturas l "
            + " WHERE u.\"NRSERIE\" = l.nroserie "
            + " AND"
            + " u.\"NRSERIE\" NOT IN"
            + " (SELECT u2.\"NRSERIE\" "
            + " FROM  nis_usuario u2, tbl_ind_usi_registro_lecturas l2 "
            + " WHERE u2.\"NRSERIE\" = l2.nroserie"
            + " AND"
            + " EXTRACT(MONTH FROM l2.fechahora) = '?1'"
            + " AND EXTRACT(YEAR FROM l2.fechahora) = '?2')"
            + " ORDER BY u.\"NIS\"", nativeQuery = true)*/
    @Query(value = "SELECT DISTINCT new com.chortitzer.web.usi.domain.NisSinLecturaModel(u.nrserie, u.nis, u.usuario) "
            + " FROM NisUsuario u, TblIndUsiRegistroLecturas l "
            + " WHERE u.nrserie = l.nroserie "
            + " AND "
            + " u.nrserie NOT IN"
            + " (SELECT u2.nrserie "
            + " FROM  NisUsuario u2, TblIndUsiRegistroLecturas l2 "
            + " WHERE u2.nrserie = l2.nroserie"
            + " AND "
            + " MONTH(l2.fechahora) = ?1"
            + " AND YEAR(l2.fechahora) = ?2)"
            + " ORDER BY u.nis")
    public List<NisSinLecturaModel> getNisSinLectura(Integer mes, Integer ano);

    /*@Query("SELECT DISTINCT new com.chortitzer.web.usi.domain.ConsumoNisModel(u.nis, l.fechahora, u.nrserie, u.usuario, "
            + "l.kwh, "
            + "l.kwh - (SELECT x.kwh "
            + "FROM TblIndUsiRegistroLecturas x "
            + "WHERE l.fechahora - x.fechahora > '10 days' AND "
            + "x.fechahora < l.fechahora "
            + "AND x.nroserie = l.nroserie "
            + "ORDER BY fechahora DESC "
            + ") AS consumo) "
            + "FROM NisUsuario u, TblIndUsiRegistroLecturas l "
            + "WHERE "
            + " u.nrserie = l.nroserie "
            + " AND "
            + "MONTH(l.fechahora) = ?1"
            + " AND "
            + "YEAR(l.fechahora) = ?2"
            + " ORDER BY u.nis")*/
    @Query(value = "SELECT DISTINCT ON (u.\"NIS\") u.\"NIS\", "
            + "l.fechahora, "
            + "u.\"NRSERIE\", "
            + "u.\"USUARIO\", "
            + "l.kwh, "
            + "l.kvar, "
            + "l.kwh - (SELECT x.kwh "
            + "FROM tbl_ind_usi_registro_lecturas x "
            + "WHERE l.fechahora - x.fechahora > '10 days' AND "
            + "x.fechahora < l.fechahora "
            + "AND x.nroserie = l.nroserie "
            + "ORDER BY fechahora DESC "
            + "LIMIT 1) AS consumo "
            + "FROM nis_usuario u, tbl_ind_usi_registro_lecturas l "
            + "WHERE "
            + " u.\"NRSERIE\" = l.nroserie "
            + " AND "
            + "EXTRACT(MONTH FROM l.fechahora) = ?1 AND "
            + "EXTRACT(YEAR FROM l.fechahora) = ?2 ORDER BY u.\"NIS\"", nativeQuery = true)
    public List<Object[]> getConsumoNisCompleto(Integer mes, Integer ano);
    
    @Query(value = "SELECT DISTINCT ON (u.\"NIS\") u.\"NIS\", "
            + "l.fechahora, "
            + "u.\"NRSERIE\", "
            + "u.\"USUARIO\", "
            + "l.kwh, "
            + "l.kvar, "
            + "l.kwh - (SELECT x.kwh "
            + "FROM tbl_ind_usi_registro_lecturas x "
            + "WHERE l.fechahora - x.fechahora > '10 days' AND "
            + "x.fechahora < l.fechahora "
            + "AND x.nroserie = l.nroserie "
            + "ORDER BY fechahora DESC "
            + "LIMIT 1) AS consumo "
            + "FROM nis_usuario u, tbl_ind_usi_registro_lecturas l "
            + "WHERE "
            + " u.\"NRSERIE\" = l.nroserie "
            + " AND "
            + " to_number(coalesce(nullif(u.\"CATEG\", ''), '0'),'99') >= 50 "
            + " AND to_number(coalesce(nullif(u.\"CATEG\", ''), '0'),'99') <= 79 "
            + " AND "
            + "EXTRACT(MONTH FROM l.fechahora) = ?1 AND "
            + "EXTRACT(YEAR FROM l.fechahora) = ?2 ORDER BY u.\"NIS\"", nativeQuery = true)
    public List<Object[]> getConsumoNisComInd(Integer mes, Integer ano);

}
