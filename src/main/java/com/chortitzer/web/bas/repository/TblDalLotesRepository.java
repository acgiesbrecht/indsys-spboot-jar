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
import com.chortitzer.web.bas.domain.TblDalLotes;
import com.chortitzer.web.bas.domain.Tblempresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDalLotesRepository extends JpaRepository<TblDalLotes, Integer> {

    @Query("SELECT l FROM TblDalLotes l WHERE l.idProductor = ?1"
            + " AND YEAR(l.fechahoraApertura) = ?2 order by l.id")
    public List<TblDalLotes> getLotesByEmpresaAndAno(Tblempresa idEmpresa, int ano);

    @Query(value = "SELECT CAST(EXTRACT(YEAR FROM fechahora_apertura) AS int) AS ano "
            + "FROM tbl_dal_lotes "
            + "GROUP BY ano ORDER BY ano DESC ", nativeQuery = true)
    public List<Integer> getDalAnosAll();
}
