/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.servicios.controller;

import com.mapris.modelo.dao.ServicioFacadeLocal;
import com.mapris.modelo.entitie.Servicio;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "editarServicioController")
@SessionScoped
public class EditarServicioController implements Serializable {

    @EJB
    private ServicioFacadeLocal sfl;
    
    private Servicio servicioSeleccionado;
   
    
    @PostConstruct
    public void init(){
    servicioSeleccionado = new Servicio();
    }

    public EditarServicioController() {
    }

    public Servicio getServicioSelecionada() {
        return servicioSeleccionado;
    }

    public void setServicioSelecionada(Servicio servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

   public void actualizarDatos() {
        try {
            
            
            
           
//       
            
            sfl.edit(servicioSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos de la inscripción", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(Servicio i){
        setServicioSelecionada(i);
        return "/app/administrador/servicios/editar.xhtml?faces-redirect=true";
    }
    
   

    
    
      
    

}
