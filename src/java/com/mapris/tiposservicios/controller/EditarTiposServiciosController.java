/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mapris.tiposservicios.controller;


import com.mapris.modelo.dao.TipoServicioFacadeLocal;
import com.mapris.modelo.entitie.TipoServicio;
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
@Named(value = "editarTipoServicioController")
@SessionScoped
public class EditarTiposServiciosController implements Serializable {
        
    @EJB
    private TipoServicioFacadeLocal tpfc;
    
    private TipoServicio tipoServicioSeleccionado;
    

    
    @PostConstruct
    public void init(){
   
   tipoServicioSeleccionado = new TipoServicio();
 
    }

    public EditarTiposServiciosController() {
    }

    public TipoServicio getTipoServicioSeleccionado() {
        return tipoServicioSeleccionado;
    }

    public void setTipoServicioSeleccionado(TipoServicio tipoServicioSeleccionado) {
        this.tipoServicioSeleccionado = tipoServicioSeleccionado;
    }

    

    public void actualizarDatos() {
       try {

           
            tpfc.edit(tipoServicioSeleccionado);
            MessageUtil.enviarMensajeInformacion("form-editar", "Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos del rutina", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(TipoServicio tp){
        setTipoServicioSeleccionado(tp);
        return "/app/administrador/tipos/editar.xhtml?faces-redirect=true";
    }
    
      
    

}
