/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.reportes.controller;

import com.mapris.modelo.dao.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ruben
 */
@Named(value = "reportesUsuarioController")
@SessionScoped
public class ReportesUsuarioController implements Serializable {

    

@EJB
private UsuarioFacadeLocal ufl;
@PersistenceContext
private EntityManager em;

Integer resultado;

     
    
    /**
     * Creates a new instance of ReportesUsuarioController
     */
    public ReportesUsuarioController() {
    }
    
    @PostConstruct
    public void init(){
       recargarResultados();
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        em.refresh(resultado);
        this.resultado = resultado;
    }
    
    public void recargarResultados(){
     em.getEntityManagerFactory().getCache().evictAll();
     this.resultado = ufl.count();
    }
    
    public Integer getNumeroUsuarios(){
        
       return this.resultado;
    
    }
    
}
