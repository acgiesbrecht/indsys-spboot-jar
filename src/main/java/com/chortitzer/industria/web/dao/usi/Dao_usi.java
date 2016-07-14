/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.usi;

/**
 *
 * @author Industria
 */
import com.chortitzer.web.usi.domain.ConsumoNisModel;
import com.chortitzer.web.usi.domain.NisSinLecturaModel;
import com.chortitzer.web.usi.domain.NisUsuario;
import java.util.List;

public interface Dao_usi {

    <T> List<T> getAll(Class<T> klass);

    <T> T save(T t);

    <T> void delete(T t);           
    
    List<ConsumoNisModel> getConsumoNis(String mes, String ano);
    
    List<NisSinLecturaModel> getNisSinLectura(String mes, String ano);
    
    <T> T findByPk(Class<T> type, Object o);
    
    long countByNrSerie(int nrserie);
    
    NisUsuario findByNis(String nis);
    
    //List<ConsumoNisModel> getByDate(String mes, String ano);
}
