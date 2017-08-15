
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.personalmedico.controller;


import com.mapris.modelo.dao.PersonalmedicoFacadeLocal;
import com.mapris.modelo.entitie.Personalmedico;
import com.mapris.modelo.entitie.Usuario;
import com.mapris.util.MessageUtil;
import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;


/**
 *
 * @author Ruben
 */
@Named(value = "listarPersonalMedicoController")
@ViewScoped
public class ListarPersonalMedicoController implements Serializable{
//    Code Dependention Injection


    @EJB
    private PersonalmedicoFacadeLocal personalMedicoFacadeLocal;

    private List<Personalmedico> personalMedico;

    private Personalmedico personalMedicoSeleccionado;
    private Usuario usuarioSeleccionado;
  

    
    
    
    public ListarPersonalMedicoController() {
    }

    @PostConstruct
    public void init() {
        recargarPersonalmedicoes();
       
    }
    
    private void recargarPersonalmedicoes(){
       
        personalMedico = personalMedicoFacadeLocal.findAll();
    }
   
    
     public PersonalmedicoFacadeLocal getPersonalmedicoFacadeLocal() {
        return personalMedicoFacadeLocal;
    }

    public void setPersonalmedicoFacadeLocal(PersonalmedicoFacadeLocal personalMedicoFacadeLocal) {
        this.personalMedicoFacadeLocal = personalMedicoFacadeLocal;
    }

    public List<Personalmedico> getPersonalmedicoes() {
        return personalMedico;
    }

    public void setPersonalmedicoSeleccionado(Personalmedico personalMedicoSeleccionado) {
        this.personalMedicoSeleccionado = personalMedicoSeleccionado;
    }


    public Personalmedico getPersonalmedicoSeleccionado() {
        return this.personalMedicoSeleccionado;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    
    
    public void eliminarPersonalmedico(){
     
        
        try {
            if (personalMedicoSeleccionado.getUsuario().getIdEstados().getNombre().equalsIgnoreCase("Activo")) {
             MessageUtil.enviarMensajeErrorGlobal("Error al eliminar el Personal Medico","No se puede eliminar un personal medico activo");
             
            }else{
            personalMedicoFacadeLocal.remove(personalMedicoSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Eliminación correcta","Se elimino correctamente el Medico ");
            }
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("Error al eliminar las Personal Medico","No se puede eliminar las Personal Medico");
                    }
        
    }
        
    
    
    

   


}
