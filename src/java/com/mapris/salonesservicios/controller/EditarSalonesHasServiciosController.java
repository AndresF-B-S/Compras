/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.salonesservicios.controller;


import com.mapris.modelo.dao.SalonHasServicioFacadeLocal;
import com.mapris.modelo.entitie.SalonHasServicio;
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
@Named(value = "editarSalonesHasServiciosController")
@SessionScoped
public class EditarSalonesHasServiciosController implements Serializable {

    @EJB
    private SalonHasServicioFacadeLocal sfl;
    
    private SalonHasServicio salonSeleccionado;

   
    
    @PostConstruct
    public void init(){
    salonSeleccionado = new SalonHasServicio();
    }

    public EditarSalonesHasServiciosController() {
    }

    public SalonHasServicio getSalonHasServicioSeleccionado() {
        return salonSeleccionado;
    }

    public void setSalonHasServicioSeleccionado(SalonHasServicio salonSeleccionado) {
        this.salonSeleccionado = salonSeleccionado;
    }

   public void actualizarDatos() {
        try {
            
       
           salonSeleccionado.getSalonHasServicioPK().setSalonesIdSalones(salonSeleccionado.getSalon().getIdSalones());
           salonSeleccionado.getSalonHasServicioPK().setServiciosIdServicio(salonSeleccionado.getServicio().getIdServicio());
            
            sfl.edit(salonSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos de la inscripción", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(SalonHasServicio i){
        setSalonHasServicioSeleccionado(i);
        return "/app/administrador/salones-servicios/editar.xhtml?faces-redirect=true";
    }
    
   

    
    
      
    

}
