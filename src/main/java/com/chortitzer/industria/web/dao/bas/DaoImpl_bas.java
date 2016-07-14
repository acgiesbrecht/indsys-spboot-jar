/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.bas;

import com.chortitzer.web.bas.domain.DalLiquidacionModel;
import com.chortitzer.web.bas.domain.DalResumenFibraModel;
import com.chortitzer.web.bas.domain.TblDalLotes;
import com.chortitzer.web.bas.domain.Tblempresa;
import com.chortitzer.web.bas.domain.Tblproductos;
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
public class DaoImpl_bas implements Dao_bas {

    @PersistenceContext(unitName = "PU_bas", type = PersistenceContextType.EXTENDED)
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
    public <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'"
                + " and t.bruto > -1 and t.tara > -1";

        return em.createQuery(sQuery).getResultList();
    }

    @Override
    public <T> List<T> getByDateRangeAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblproductos producto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'"
                + " and t.productoid.id = " + producto.getId().toString()
                + " and t.bruto > -1 and t.tara > -1";
        return em.createQuery(sQuery).getResultList();
    }

    @Override
    public <T> List<T> getByDateRangeAndEmpresa(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'"
                + " and t.empresaid.id = " + empresa.getId().toString()
                + " and t.bruto > -1 and t.tara > -1";
        return em.createQuery(sQuery).getResultList();
    }

    @Override
    public <T> List<T> getByDateRangeAndEmpresaAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'"
                + " and t.empresaid.id = " + empresa.getId().toString()
                + " and t.productoid.id = " + producto.getId().toString()
                + " and t.bruto > -1 and t.tara > -1";
        return em.createQuery(sQuery).getResultList();
    }

    @Override
    public List<DalResumenFibraModel> getResumenByLote(String lote) {
        String sQuery = "SELECT descripcion AS tipo, "
                + "                        mic AS micronaire, "
                + "                        count(*) AS cantidad, "
                + "                        sum(peso) as peso,"
                + "                        SUM(peso)/(SELECT SUM(peso) FROM tbl_dal_fardos_fibra WHERE id_lote=" + lote + ")*100 AS porcentaje"
                + "                FROM"
                + "                    (SELECT CASE WHEN tbl_dal_fardos_fibra.micronaire >= 5 THEN 'Mayor a 5.0'                                 "
                + "                                WHEN tbl_dal_fardos_fibra.micronaire >= 3.5 THEN '3.5 - 4.9'                                                                "
                + "                                ELSE 'Menor a 3.5'"
                + "                    END as mic, tbl_dal_fardos_fibra.tipo, tbl_dal_fardos_fibra.peso, tbl_dal_fibra_tipos.descripcion, tbl_dal_fardos_fibra.id"
                + "                FROM tbl_dal_fardos_fibra, tbl_dal_fibra_tipos"
                + "                WHERE  tbl_dal_fardos_fibra.tipo = tbl_dal_fibra_tipos.id AND id_lote=" + lote + ") AS fardos"
                + "                GROUP BY descripcion, mic"
                + "                ORDER BY descripcion, mic";
        return em.createNativeQuery(sQuery, DalResumenFibraModel.class).getResultList();
    }

    @Override
    public List<DalResumenFibraModel> getResumenByAno(int ano) {
        String sQuery = "SELECT descripcion AS tipo, "
                + "                        mic AS micronaire, "
                + "                        count(*) AS cantidad, "
                + "                        sum(peso) as peso,"
                + "                        SUM(peso)/(SELECT SUM(peso) FROM tbl_dal_fardos_fibra WHERE id_lote=" + String.valueOf(ano) + ")*100 AS porcentaje"
                + "                FROM"
                + "                    (SELECT CASE WHEN tbl_dal_fardos_fibra.micronaire >= 5 THEN 'Mayor a 5.0'                                 "
                + "                                WHEN tbl_dal_fardos_fibra.micronaire >= 3.5 THEN '3.5 - 4.9'                                                                "
                + "                                ELSE 'Menor a 3.5'"
                + "                    END as mic, tbl_dal_fardos_fibra.tipo, tbl_dal_fardos_fibra.peso, tbl_dal_fibra_tipos.descripcion, tbl_dal_fardos_fibra.id"
                + "                FROM tbl_dal_fardos_fibra, tbl_dal_fibra_tipos"
                + "                WHERE  tbl_dal_fardos_fibra.tipo = tbl_dal_fibra_tipos.id AND "
                + " EXTRACT(YEAR FROM tbl_dal_fardos_fibra.fechahora)=" + String.valueOf(ano) + ") AS fardos"
                + "                GROUP BY descripcion, mic"
                + "                ORDER BY descripcion, mic";
        return em.createNativeQuery(sQuery, DalResumenFibraModel.class).getResultList();
    }

    @Override
    public List<Integer> getDalAnosAll() {
        String sQuery = "SELECT CAST(EXTRACT(YEAR FROM fechahora_apertura) AS int) AS ano "
                + "FROM tbl_dal_lotes "
                + "GROUP BY ano ORDER BY ano DESC ";
        return (List<Integer>) em.createNativeQuery(sQuery).getResultList();
    }

    @Override
    public List<TblDalLotes> getLotesByEmpresaAndAno(int idEmpresa, int ano) {
        String sQuery = "SELECT * "
                + "FROM tbl_dal_lotes "
                + "WHERE id_productor = " + String.valueOf(idEmpresa)
                + " AND EXTRACT(YEAR FROM fechahora_apertura) = " + String.valueOf(ano);
        return em.createNativeQuery(sQuery, TblDalLotes.class).getResultList();
    }

    @Override
    public List<DalLiquidacionModel> getLiquidacionByAno(int ano) {
        String sQuery = "SELECT id, "
                + "                        fechahora, "
                + "                        (bruto-tara) AS peso, "
                + "                        ((bruto-tara)*0.2) AS pesoanticipo, "
                + "                        ((bruto-tara)*0.2*precio_gs_por_kg) AS pago "
                + "               FROM tblpesadas "
                + "               WHERE (productoid = 64 "
                + "                        OR productoid = 83) "
                + "                        AND EXTRACT(YEAR FROM fechahora) = " + String.valueOf(ano)
                + "                        AND bruto > -1 AND tara > -1";
        return em.createNativeQuery(sQuery, DalLiquidacionModel.class).getResultList();
    }

    @Override
    public List<DalLiquidacionModel> getLiquidacionByLote(String lote) {
        String sQuery = "SELECT id, "
                + "                        fechahora, "
                + "                        (bruto-tara) AS peso, "
                + "                        ((bruto-tara)*0.2) AS pesoanticipo, "
                + "                        ((bruto-tara)*0.2*precio_gs_por_kg) AS pago "
                + "               FROM tblpesadas "
                + "               WHERE (productoid = 64 "
                + "                        OR productoid = 83) "
                + "                        AND id_lote = " + lote
                + "                        AND bruto > -1 AND tara > -1";
        return em.createNativeQuery(sQuery, DalLiquidacionModel.class).getResultList();
    }

    @Override
    public List<Tblproductos> getAllSesammoProductos() {
        String sQuery = "SELECT * FROM tblproductos WHERE descripcion LIKE '%Sesamo %' AND materiaprima = 1";
        return em.createNativeQuery(sQuery, Tblproductos.class).getResultList();
    }

    @Override
    public List<Tblproductos> getAllGranosProductos() {
        String sQuery = "SELECT * FROM tblproductos WHERE ("
                + "descripcion LIKE '%Maiz %' OR "
                + "descripcion LIKE '%Sorgo %' OR "
                + "descripcion LIKE '%Soja %' OR "
                + "descripcion LIKE '%Trigo %'"
                + ") AND materiaprima = 1";
        return em.createNativeQuery(sQuery, Tblproductos.class).getResultList();
    }

    @Override
    public <T> List<T> getViewByDateRangeAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblproductos producto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'"
                + " and t.idProducto = " + producto.getId().toString()
                + " and t.bruto > -1 and t.tara > -1";
        return em.createQuery(sQuery).getResultList();
    }

    @Override
    public <T> List<T> getViewByDateRangeAndEmpresa(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'"
                + " and t.idProductor = " + empresa.getId().toString()
                + " and t.bruto > -1 and t.tara > -1";
        return em.createQuery(sQuery).getResultList();
    }

    @Override
    public <T> List<T> getViewByDateRangeAndEmpresaAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'"
                + " and t.idProductor = " + empresa.getId().toString()
                + " and t.idProducto = " + producto.getId().toString()
                + " and t.bruto > -1 and t.tara > -1";
        return em.createQuery(sQuery).getResultList();
    }

}
