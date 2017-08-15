/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.dao;

import com.mapris.modelo.entitie.Inscripcion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author APRENDIZ
 */
@Local
public interface InscripcionFacadeLocal {

    void create(Inscripcion inscripcion);

    void edit(Inscripcion inscripcion);

    void remove(Inscripcion inscripcion);

    Inscripcion find(Object id);

    List<Inscripcion> findAll();

    List<Inscripcion> findRange(int[] range);

    int count();
    
}
