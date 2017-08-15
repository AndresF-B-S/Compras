/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.cursos.controllers;


import com.mapris.modelo.dao.CursoFacadeLocal;
import com.mapris.modelo.entitie.Curso;
import com.mapris.util.MessageUtil;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */

@Named(value="registrarCursoController")
@RequestScoped
public class RegistrarCursosController {
    @EJB
    private CursoFacadeLocal cfl;
    
    private Curso cursoNuevo;
    
 
    public RegistrarCursosController() {
    }
    
     
    @PostConstruct
    public void  init(){
        
        cursoNuevo = new Curso();
        
    }

    public Curso getCursoNuevo() {
        return cursoNuevo;
    }

    public void setCursoNuevo(Curso cursoNuevo) {
        this.cursoNuevo = cursoNuevo;
    }
    
    
    
    
    public void registrar(){
        if (cursoNuevo != null) {
            try {
            cursoNuevo.setEstado("En proceso");
            cfl.create(cursoNuevo);
                MessageUtil.enviarMensajeInformacion(null, "Registro con exito", "Se ha registrado correctamente el curso");
            } catch (Exception e) {
                MessageUtil.enviarMensajeErrorGlobal("No se ha podido registrar", "El curso no se ha podido registrar correctamente");
                e.printStackTrace();
            }
            
        }else{
                MessageUtil.enviarMensajeErrorGlobal("No se ha podido registrar el curso", "Intentalo de nuevo");
        
        }
    
    }
    
    

  
    
    
    
}
