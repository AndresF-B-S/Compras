/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.dao;

import com.mapris.modelo.entitie.Personalmedico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author APRENDIZ
 */
@Local
public interface PersonalmedicoFacadeLocal {

    void create(Personalmedico personalmedico);

    void edit(Personalmedico personalmedico);

    void remove(Personalmedico personalmedico);

    Personalmedico find(Object id);

    List<Personalmedico> findAll();

    List<Personalmedico> findRange(int[] range);

    int count();
    
}
