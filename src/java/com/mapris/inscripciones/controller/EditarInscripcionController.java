/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.inscripciones.controller;


import com.mapris.modelo.dao.InscripcionFacadeLocal;
import com.mapris.modelo.entitie.Inscripcion;
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
@Named(value = "editarInscripcionController")
@SessionScoped
public class EditarInscripcionController implements Serializable {

   @EJB
   private InscripcionFacadeLocal ifl;
   
   private Inscripcion inscripcionSelecionada;
   
    
    @PostConstruct
    public void init(){
    inscripcionSelecionada = new Inscripcion();
    }

    public EditarInscripcionController() {
    }

    public Inscripcion getInscripcionSelecionada() {
        return inscripcionSelecionada;
    }

    public void setInscripcionSelecionada(Inscripcion inscripcionSelecionada) {
        this.inscripcionSelecionada = inscripcionSelecionada;
    }
    
    

 
   public void actualizarDatos() {
        try {
            
           
//       
            
            ifl.edit(inscripcionSelecionada);
            MessageUtil.enviarMensajeInformacion("form-editar", "Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos de la inscripción", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(Inscripcion i){
        this.inscripcionSelecionada = i;
        return "/app/administrador/inscripciones/editar.xhtml?faces-redirect=true";
    }
    
     public void cambioDeEstado(Inscripcion i) {
        try {
            if (i.getEstado().equalsIgnoreCase("Pendiente")) {
                
                i.setEstado("Activa");

            } else {
                i.setEstado("Pendiente");
            }
            ifl.edit(i);
            MessageUtil.enviarMensajeInformacionGlobal("Actualización", "La inscripción se ha activado o desactivado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al activar la inscripción", e.getStackTrace().toString());
        }

    }

    public String getIconUsuarioBloqueo(Inscripcion i) {
        return (i.getEstado().equalsIgnoreCase("Pendiente")) ? "cog" : "check";
    }
    
      
    

}
