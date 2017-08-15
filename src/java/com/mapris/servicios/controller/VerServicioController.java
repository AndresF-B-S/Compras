/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.servicios.controller;

import com.mapris.modelo.entitie.Servicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verServicioController")
@SessionScoped
public class VerServicioController implements Serializable{
    
    private Servicio servicio;

    public VerServicioController() {
    }
    
    @PostConstruct
    public void init(){
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    public String verServicio(Servicio i){
    this.servicio = i;
    return "/app/administrador/servicios/ver.xhtml?faces-redirect=true";
    }
    
    
}
