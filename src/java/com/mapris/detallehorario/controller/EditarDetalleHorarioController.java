/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mapris.detallehorario.controller;


import com.mapris.modelo.dao.DetalleHorarioFacadeLocal;
import com.mapris.modelo.entitie.DetalleHorario;
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
@Named(value = "editarDetalleHorarioController")
@SessionScoped
public class EditarDetalleHorarioController implements Serializable {

    @EJB
    private DetalleHorarioFacadeLocal dhfl;
    
    private DetalleHorario detalleHorarioSeleccionado;
    
    
    @PostConstruct
    public void init(){
    
    detalleHorarioSeleccionado= new DetalleHorario();   
   
    }

    public EditarDetalleHorarioController() {
    }

    public DetalleHorario getDetalleHorarioSeleccionado() {
        return detalleHorarioSeleccionado;
    }

    public void setDetalleHorarioSeleccionado(DetalleHorario detalleHorarioSeleccionado) {
        this.detalleHorarioSeleccionado = detalleHorarioSeleccionado;
    }
    
    

   

    public void actualizarDatos() {
        try {
            
            
           
            
            dhfl.edit(detalleHorarioSeleccionado);
            MessageUtil.enviarMensajeInformacion("form-editar", "Actualizacion exitosa", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos del calendario","");
        }

    }
    
    public String preModificar(DetalleHorario dh){
        setDetalleHorarioSeleccionado(dh);
        return "/app/administrador/calendario/editar.xhtml?faces-redirect=true";
    }
    
      
    

}
