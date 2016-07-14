/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.usi;

import com.chortitzer.web.usi.domain.ConsumoNisModel;
import com.chortitzer.web.usi.domain.NisSinLecturaModel;
import com.chortitzer.web.usi.domain.NisUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("session")
public class DaoImpl_usi implements Dao_usi {

    //@PersistenceContext(type = PersistenceContextType.EXTENDED)
    @PersistenceContext(unitName = "PU_usi", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> klass) {
        return em.createQuery("Select t from " + klass.getSimpleName() + " t")
                .getResultList();
    }

    @Override
    public <T> T save(T t) {
        T newRecord = null;
        newRecord = em.merge(t);
        return newRecord;
    }

    @Override
    public <T> void delete(T t) {
        em.remove(em.merge(t));
        em.flush();
    }

    @Override
    public <T> T findByPk(Class<T> type, Object o) {
        return em.find(type, o);
    }

    @Override
    public long countByNrSerie(int nrserie) {
        return (Long) em.createQuery("SELECT COUNT(u) FROM NisUsuario u WHERE u.nrserie = " + String.valueOf(nrserie)).getSingleResult();
    }

    /*public List<ConsumoNisModel> getConsumoNisByMonth(String mes, String ano) {
        String sQuery = "SELECT u.nis, l.fechahora, u.nroserie, u.nombre, l.kwh"
                + ", l.kwh - (SELECT x.kwh "
                + "     FROM tbl_ind_usi_registro_lecturas x"
                + "     WHERE x.fechahora < l.fechahora"
                + "     AND x.nroserie = l.nroserie"
                + "     ORDER BY x.fechahora DESC LIMIT 1) as consumo "
                + " FROM tbl_ind_usi_usuarios u, tbl_ind_usi_registro_lecturas l"
                + " WHERE"
                + " u.nroserie = l.nroserie"
                + " AND "
                + " MONTH(l.fechahora) = " + mes
                + " AND "
                + " YEAR(l.fechahora) = " + ano
                + " ORDER BY u.nis";
        System.out.print(sQuery);
        //List<ConsumoNisModel> list = (List<ConsumoNisModel>) em.createNativeQuery(sQuery).getResultList();
        //TypedQuery<ConsumoNisModel> query = em.createNativeQuery(sQuery, ConsumoNisModel.class);

        //return (List<ConsumoNisModel>) em.createNativeQuery(sQuery, ConsumoNisModel.class).getResultList();
        return (List<ConsumoNisModel>) em.createNativeQuery(sQuery).getResultList();

    }*/
    @Override
    public List<ConsumoNisModel> getConsumoNis(String mes, String ano) {

        String sQuery = "SELECT DISTINCT ON (u.\"NIS\") u.\"NIS\", "
                + "l.fechahora, "
                + "u.\"NRSERIE\", "
                + "u.\"USUARIO\", "
                + "l.kwh, "
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
                + "EXTRACT(MONTH FROM l.fechahora) = '" + mes
                + "' AND "
                + "EXTRACT(YEAR FROM l.fechahora) = '" + ano
                + "' ORDER BY u.\"NIS\"";

        /*String sQuery = "SELECT c.nis, c.fechahora, c.nrserie, c.kwh, c.consumo "
                + "FROM consumo_nis c "
                + "WHERE EXTRACT(MONTH FROM c.fechahora) = '" + mes
                    + "' AND "
                    + "EXTRACT(YEAR FROM c.fechahora) = '" + ano
                + "'ORDER BY c.nis";*/
        System.out.print(sQuery);
        //List<ConsumoNisModel> list = (List<ConsumoNisModel>) em.createNativeQuery(sQuery).getResultList();
        //TypedQuery<ConsumoNisModel> query = em.createNativeQuery(sQuery, ConsumoNisModel.class);

        //return (List<ConsumoNisModel>) em.createNativeQuery(sQuery, ConsumoNisModel.class).getResultList();
        return (List<ConsumoNisModel>) em.createNativeQuery(sQuery, ConsumoNisModel.class).getResultList();

    }

    @Override
    public NisUsuario findByNis(String nis) {
        String sQuery = "Select u from NisUsuario u whre u.nis = " + nis;
        return (NisUsuario) em.createQuery(sQuery).getSingleResult();
    }

    /*@Override
    public List<ConsumoNisModel> getByDate(String mes, String ano){
        return em.createQuery("Select c from ConsumoNis c where  EXTRACT(MONTH FROM c.fechahora) = " + mes + " and EXTRACT(YEAR FROM c.fechahora) = " + ano + " order by c.nis")
        .getResultList();
    }*/
    @Override
    public List<NisSinLecturaModel> getNisSinLectura(String mes, String ano) {
        String query = "SELECT DISTINCT u.\"NRSERIE\", u.\"NIS\", u.\"USUARIO\" "
                + " FROM nis_usuario u, tbl_ind_usi_registro_lecturas l "
                + " WHERE u.\"NRSERIE\" = l.nroserie "
                + " AND"
                + " u.\"NRSERIE\" NOT IN"
                + " (SELECT u2.\"NRSERIE\" "
                + " FROM  nis_usuario u2, tbl_ind_usi_registro_lecturas l2 "
                + " WHERE u2.\"NRSERIE\" = l2.nroserie"
                + " AND"
                + " EXTRACT(MONTH FROM l2.fechahora) = '" + mes + "'"
                + " AND EXTRACT(YEAR FROM l2.fechahora) = '" + ano + "')"
                + " ORDER BY u.\"NIS\"";
        return (List<NisSinLecturaModel>) em.createNativeQuery(query, NisSinLecturaModel.class).getResultList();
    }

}
