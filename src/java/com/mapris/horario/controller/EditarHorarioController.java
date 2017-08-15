/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mapris.horario.controller;


import com.mapris.modelo.dao.HorarioFacadeLocal;
import com.mapris.modelo.entitie.Horario;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "editarCalendarioController")
@SessionScoped
public class EditarHorarioController implements Serializable {
    
       @EJB
       private HorarioFacadeLocal hfl;
                
       private Horario   horarioSeleccionado;

    
    
    @PostConstruct
    public void init(){
    horarioSeleccionado = new Horario();
   
    }

    public EditarHorarioController() {
    }

    public Horario getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    public void setHorarioSeleccionado(Horario horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;
    }

    
    
   

    public void actualizarDatos() {
        try {
            
            
            
            hfl.edit(horarioSeleccionado);
            MessageUtil.enviarMensajeInformacion("form-editar", "Actualizacion exitosa", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos del horario", "");
        }

    }
    
    public String preModificar(Horario h){
        setHorarioSeleccionado(h);
        return "/app/administrador/calendario/editar.xhtml?faces-redirect=true";
    }
    
      
    

}
