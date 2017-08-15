/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.aplazamiento.controllers;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.entitie.Aplazamiento;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verAplazamientoClienteController")
@RequestScoped
public class VerAplazamientoClienteController{
    @Inject
    private SessionController sc;
    
    private Aplazamiento aplazamiento;
   

    public VerAplazamientoClienteController() {
            
    }
    
    @PostConstruct
    public void init(){
        
    }

    public Aplazamiento getAplazamiento() {
        return aplazamiento;
    }

    public void setAplazamiento(Aplazamiento aplazamiento) {
        aplazamiento = sc.getUsuario().getCliente().getAplazamiento();
        this.aplazamiento = aplazamiento;
    }

    
    
    
    
    
}
