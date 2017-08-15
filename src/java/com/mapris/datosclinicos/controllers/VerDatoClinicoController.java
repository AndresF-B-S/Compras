/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.datosclinicos.controllers;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.dao.DatoclinicoFacadeLocal;
import com.mapris.modelo.entitie.Cliente;
import com.mapris.modelo.entitie.Datoclinico;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verDatoClinicoController")
@RequestScoped
public class VerDatoClinicoController {
    
    @Inject
    private SessionController sc;
    
    
    @EJB
    private DatoclinicoFacadeLocal dcfl;
    
    private Datoclinico datoClinico;
    
    private Datoclinico datoClinicoCliente;
    
   

    public VerDatoClinicoController() {
            
    }
    
    @PostConstruct
    public void init(){
      
       ver();
        
    }
    
    public void ver()
    {
        
        this.datoClinicoCliente = sc.getUsuario().getCliente().getDatosClinicos().get(0);
    }
    public Datoclinico getDatoclinico() {
        return datoClinico;
    }

    public void setDatoclinico(Datoclinico datoClinico) {
        this.datoClinico = datoClinico;
    }

    public Datoclinico getDatoClinicoCliente() {
       
       
        return datoClinicoCliente;
    }

    public void setDatoClinicoCliente(Datoclinico datoClinicoCliente) {
         
         this.datoClinicoCliente = datoClinicoCliente;
    }

    
    
    
    
    
    
    public String verDatoclinico(Datoclinico i){
    this.datoClinico = i;
   
    return "/app/administrador/datoClinicos/ver.xhtml?faces-redirect=true";
    }
    
    
    
    
    
    
    
    public void eliminarDatoclinico(){
        if (datoClinico != null) {
            
            dcfl.remove(datoClinico);
            MessageUtil.enviarMensajeInformacionGlobal("Se elimino satisfactoriamente", "Se elimino satisfactoriamente el dato clinico");
            
        }else{
        
            MessageUtil.enviarMensajeErrorGlobal("No se pudo eliminar", "No se pudo elimnar el dato clinico");
        }
        
    }
    
    
}
