/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.industria.web.dao.lab;

/**
 *
 * @author Industria
 */
import java.util.List;
 
public interface Dao_lab {
 
    <T> List<T> getAll(Class<T> klass);
 
   <T> T save(T t);
 
  <T> void delete(T t);
}
