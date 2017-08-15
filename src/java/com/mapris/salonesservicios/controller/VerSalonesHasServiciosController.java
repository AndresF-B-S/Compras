/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.salonesservicios.controller;

import com.mapris.modelo.entitie.Salon;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verSalonesHasServiciosController")
@SessionScoped
public class VerSalonesHasServiciosController implements Serializable{
    
    private Salon salon;

    public VerSalonesHasServiciosController() {
    }
    
    @PostConstruct
    public void init(){
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
    
    public String verSalon(Salon i){
    this.salon = i;
    return "/app/administrador/salones/ver.xhtml?faces-redirect=true";
    }
    
    
}
