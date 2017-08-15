/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.horario.controller;


import com.mapris.modelo.dao.HorarioFacadeLocal;
import com.mapris.modelo.entitie.Horario;
import com.mapris.modelo.entitie.Rol;

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
@Named(value = "registroHorarioController")
@RequestScoped
public class RegistrarHorarioController {
    
    
    @EJB
    private HorarioFacadeLocal hfl;
                
    private Horario  horarioNuevo;

    public RegistrarHorarioController() {
    }
    
    
    @PostConstruct
    public void  init(){
        
        horarioNuevo = new Horario();
    }

    public Horario getHorarioNuevo() {
        return horarioNuevo;
    }

    public void setHorarioNuevo(Horario horarioNuevo) {
        this.horarioNuevo = horarioNuevo;
    }

    
    
    
    
    public void registrar(){
    
        if (horarioNuevo != null) {
     
            hfl.create(horarioNuevo);
            MessageUtil.enviarMensajeInformacion("form-registro", "Registro satisfactorio del horario", "Se registro correctamente el horario");
            
            init();
            
        } else{
            MessageUtil.enviarMensajeError("form-regitro", "Registro fallido", "No se pudo registrar el horario");
        }
    }
    
    
    
    
    
    
    
    
}
