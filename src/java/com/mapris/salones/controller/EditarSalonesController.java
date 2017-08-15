/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.salones.controller;


import com.mapris.modelo.dao.SalonFacadeLocal;
import com.mapris.modelo.entitie.Salon;
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
@Named(value = "editarSalonesController")
@SessionScoped
public class EditarSalonesController implements Serializable {

    @EJB
    private SalonFacadeLocal sfl;
    
    private Salon salonSeleccionado;

   
    
    @PostConstruct
    public void init(){
    salonSeleccionado = new Salon();
    }

    public EditarSalonesController() {
    }

    public Salon getSalonSeleccionado() {
        return salonSeleccionado;
    }

    public void setSalonSeleccionado(Salon salonSeleccionado) {
        this.salonSeleccionado = salonSeleccionado;
    }

   public void actualizarDatos() {
        try {
            
       
            salonSeleccionado.setSalon(salonSeleccionado.getSalon());
            salonSeleccionado.setDescripcion(salonSeleccionado.getDescripcion());
            salonSeleccionado.setEstado(salonSeleccionado.getEstado());
//       
            
            sfl.edit(salonSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos de la inscripción", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(Salon i){
        setSalonSeleccionado(i);
        return "/app/administrador/salones/editar.xhtml?faces-redirect=true";
    }
    
   

    
    
      
    

}
