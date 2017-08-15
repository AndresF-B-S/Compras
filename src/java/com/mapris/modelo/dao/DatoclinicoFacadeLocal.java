/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.dao;

import com.mapris.modelo.entitie.Datoclinico;
import com.mapris.modelo.entitie.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruben
 */
@Local
public interface DatoclinicoFacadeLocal {

    void create(Datoclinico datoclinico);

    void edit(Datoclinico datoclinico);

    void remove(Datoclinico datoclinico);

    Datoclinico find(Object id);

    List<Datoclinico> findAll();

    List<Datoclinico> findRange(int[] range);

    int count();
    
    List<Datoclinico> buscarDato(Cliente idCliente);
    
}
