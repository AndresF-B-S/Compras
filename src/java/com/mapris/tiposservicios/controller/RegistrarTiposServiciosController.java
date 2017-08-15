/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.tiposservicios.controller;



import com.mapris.modelo.dao.TipoServicioFacadeLocal;
import com.mapris.modelo.entitie.TipoServicio;
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
@Named(value = "registroTipoServicioController")
@RequestScoped
public class RegistrarTiposServiciosController {
    
    @EJB 
    private TipoServicioFacadeLocal tpfl;
    
    private TipoServicio tipoServicioNuevo;
    
    

    public RegistrarTiposServiciosController() {
    }
    
    
    @PostConstruct
    public void  init(){
        tipoServicioNuevo= new TipoServicio();
        
    }

    public TipoServicio getTipoServicioNuevo() {
        return tipoServicioNuevo;
    }

    public void setTipoServicioNuevo(TipoServicio tipoServicioNuevo) {
        this.tipoServicioNuevo = tipoServicioNuevo;
    }

    
    
    public void registrar(){
     if (tipoServicioNuevo != null) {
    
            tpfl.create(tipoServicioNuevo);
            MessageUtil.enviarMensajeInformacion("form-registro", "Registro satisfactorio", "Tipo de servicio registrado");
            
            init();
            
        } else{
            MessageUtil.enviarMensajeError("form-regitro", "No se pudo registrar ", "Registro de tipo de servicio fallido");
        }
    }
    
    
    
    
    
    
    
    
}
