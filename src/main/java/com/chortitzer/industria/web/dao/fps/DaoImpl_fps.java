/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.fps;

import com.chortitzer.web.fps.domain.FpsAvgValueModel;
import com.chortitzer.web.fps.domain.FpsHorarioReactorModel;
import com.chortitzer.web.fps.domain.TblFpsLotes;
import com.chortitzer.web.fps.domain.TblFpsTambores;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("session")
public class DaoImpl_fps implements Dao_fps {

    //@PersistenceContext(type = PersistenceContextType.EXTENDED)
    @PersistenceContext(unitName = "PU_fps", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> klass) {
        return em.createQuery("Select t from " + klass.getSimpleName() + " t")
                .getResultList();
    }

    public <T> T save(T t) {
        T newRecord = null;
        newRecord = em.merge(t);
        return newRecord;
    }

    public <T> void delete(T t) {
        em.remove(em.merge(t));
        em.flush();
    }

    public <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'";
        System.out.print(sQuery);
        return em.createQuery(sQuery).getResultList();
    }

    public List<FpsAvgValueModel> getAvgFpsParamByDateRange(int Param, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //String sQuery = "Select new com.chortitzer.industria.web.domain.fps.FpsAvgValueModel(max(t.fechahora), avg(t.p" + String.valueOf(Param) + ") as valor) from TblIndFpsLog t where t.fechahora between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate)
        String sQuery = "SELECT MAX(t.fechahora) AS fechahora, AVG(t.p" + String.valueOf(Param) + ") AS valor from tbl_ind_fps_log t "
                + "WHERE t.fechahora BETWEEN '" + sdf.format(startDate) + "' AND '" + sdf.format(endDate)
                + "' GROUP BY EXTRACT(DAY FROM t.fechahora), EXTRACT(HOUR FROM t.fechahora), EXTRACT(MINUTE FROM t.fechahora)"
                + " ORDER BY MAX(t.fechahora)";
        return (List<FpsAvgValueModel>) em.createNativeQuery(sQuery, FpsAvgValueModel.class).getResultList();
    }

    public List<FpsAvgValueModel> getAvgFpsParamByDate(int Param, Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sQuery = "Select new com.chortitzer.industria.web.domain.fps.FpsAvgValueModel(max(t.fechahora), avg(t.p" + String.valueOf(Param) + ") as valor) from TblIndFpsLog t where DATE(t.fechahora) = '" + sdf.format(fecha)
                + "' group by DATE(t.fechahora), HOUR(t.fechahora), MINUTE(t.fechahora)"
                + " ORDER BY t.fechahora";
        System.out.print(sQuery);
        return (List<FpsAvgValueModel>) em.createQuery(sQuery).getResultList();
    }

    public List<TblFpsTambores> getByLote(TblFpsLotes lote) {
        return (List<TblFpsTambores>) em.createQuery("select t from TblFpsTambores t where t.idLote = " + lote.getId().toString()).getResultList();
    }

    public FpsHorarioReactorModel getHorario(Date fecha, int reactor) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String sQuery = "select (select min(fechahora) from tbl_ind_fps_log where fechahora >= '" + sdf.format(fecha) + "' and p11 < 120) as paro, min(fechahora) as arranque "
        //        + "from tbl_ind_fps_log where fechahora >= '" + sdf.format(fecha) + "' and p11 > 120 and fechahora > (select min(fechahora) from tbl_ind_fps_log where fechahora >= '" + sdf.format(fecha) + "' and p11 < 120)";
        String sQuery = "select max(fechahora) as arranque, (select min(fechahora) from tbl_ind_fps_log where fechahora >= '" + sdf.format(fecha) + "' and p" + String.valueOf(10 + reactor) + " < 100) as paro "
                + "from tbl_ind_fps_log where p" + String.valueOf(10 + reactor) + " < 100 and fechahora < (select min(fechahora) from tbl_ind_fps_log where fechahora >= '" + sdf.format(fecha) + "' and p" + String.valueOf(10 + reactor) + " < 100)";
        return (FpsHorarioReactorModel) em.createNativeQuery(sQuery, FpsHorarioReactorModel.class).getSingleResult();
    }
}
