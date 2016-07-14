/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.lab.repository;

/**
 *
 * @author Industria
 */
import com.chortitzer.web.lab.domain.LabClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabClientesRepository extends JpaRepository<LabClientes, Integer> {

}
