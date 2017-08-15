/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.detallehorario.controller;



import com.mapris.modelo.dao.DetalleHorarioFacadeLocal;
import com.mapris.modelo.entitie.DetalleHorario;
import com.mapris.util.MessageUtil;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "registroDetalleHorarioController")
@RequestScoped
public class RegistrarDetalleHorarioController {
    
    @EJB
    private DetalleHorarioFacadeLocal dhfl;
    
    private DetalleHorario nuevoDetalleHorario;
    

    public RegistrarDetalleHorarioController() {
    }
    
    
    @PostConstruct
    public void  init(){
        
        nuevoDetalleHorario = new DetalleHorario();
        
    }

    public DetalleHorario getNuevoDetalleHorario() {
        return nuevoDetalleHorario;
    }

    public void setNuevoDetalleHorario(DetalleHorario nuevoDetalleHorario) {
        this.nuevoDetalleHorario = nuevoDetalleHorario;
    }
    
    

    
    
    public void registrar(){
        
        if (nuevoDetalleHorario != null) {
     
           dhfl.create(nuevoDetalleHorario);
            MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio del calendario", "");
            
            init();
            
        } else{
            MessageUtil.enviarMensajeErrorGlobal("Registro fallido del calendario ", "");
        }
    }
    
    
    
    
    
    
    
    
}
