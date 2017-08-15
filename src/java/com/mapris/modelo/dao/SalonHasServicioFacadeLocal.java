/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.dao;

import com.mapris.modelo.entitie.SalonHasServicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author APRENDIZ
 */
@Local
public interface SalonHasServicioFacadeLocal {

    void create(SalonHasServicio salonHasServicio);

    void edit(SalonHasServicio salonHasServicio);

    void remove(SalonHasServicio salonHasServicio);

    SalonHasServicio find(Object id);

    List<SalonHasServicio> findAll();

    List<SalonHasServicio> findRange(int[] range);

    int count();
    
}
