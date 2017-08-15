
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.aplazamiento.controllers;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.entitie.Aplazamiento;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;


import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "listarProgramasController")
@ViewScoped
public class VerAplazamientoController implements Serializable{
//    cdi 
    
    
     private Aplazamiento aplazamiento;
   

    
    public VerAplazamientoController() {
    }

    @PostConstruct
    public void init() {
       
    }
    
    public Aplazamiento getAplazamiento() {
        return aplazamiento;
    }

    public void setAplazamiento(Aplazamiento aplazamiento) {
        this.aplazamiento = aplazamiento;
    }

 
    
    
    public String verAplazamiento(Aplazamiento a){
    this.aplazamiento = a;
   
    return "/app/administrador/aplazamientos/ver.xhtml?faces-redirect=true";
    }

  

    
    
    
  
    
    
   
}
