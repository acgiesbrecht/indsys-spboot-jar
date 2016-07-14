/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.fps.repository;

/**
 *
 * @author Industria
 */
import com.chortitzer.web.fps.domain.TblIndFpsLog;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblIndFpsLogRepository extends JpaRepository<TblIndFpsLog, Date> {

    @Query(value = "select max(fechahora) as arranque, (select min(fechahora) from tbl_ind_fps_log where fechahora >= ?1 and p1 < 100) as paro "
            + "from tbl_ind_fps_log where p1 < 100 and fechahora < (select min(fechahora) from tbl_ind_fps_log where fechahora >= ?1 and p1 < 100)", nativeQuery = true)
    public Object[] getHorarioP1(Date fecha);

}
