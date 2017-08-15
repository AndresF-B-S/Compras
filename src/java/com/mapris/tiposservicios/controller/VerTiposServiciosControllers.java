/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.tiposservicios.controller;


import com.mapris.modelo.entitie.TipoServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verTipoServiciosController")
@SessionScoped
public class VerTiposServiciosControllers implements Serializable{
    
    private TipoServicio tipoServicioSeleccionado;

    public VerTiposServiciosControllers() {
    }
    
    @PostConstruct
    public void init(){
    }

    
    public String verTipoServicio(TipoServicio tp){
     this.tipoServicioSeleccionado = tp;
    return "/app/administrador/tipos/ver.xhtml?faces-redirect=true";
    }
    
    
}
