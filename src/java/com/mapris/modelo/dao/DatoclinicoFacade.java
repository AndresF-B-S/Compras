/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.dao;

import com.mapris.modelo.entitie.Cliente;
import com.mapris.modelo.entitie.Datoclinico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ruben
 */
@Stateless
public class DatoclinicoFacade extends AbstractFacade<Datoclinico> implements DatoclinicoFacadeLocal {

    @PersistenceContext(unitName = "maprisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatoclinicoFacade() {
        super(Datoclinico.class);
    }

    @Override
    public List<Datoclinico> buscarDato(Cliente idCliente) {
         try {
            getEntityManager().getEntityManagerFactory().getCache().evictAll();
            TypedQuery<Datoclinico> q = getEntityManager().createNamedQuery("Datoclinico.findByFkUsuario", Datoclinico.class);
            q.setParameter("idUsuarios", idCliente);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("El usuario no pudo consultar su dato clinico");
            e.printStackTrace();
            return null;
        }
    }
    
}
