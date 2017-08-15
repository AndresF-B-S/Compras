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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "listarHorarioController")
@ViewScoped
public class ListarHorarioController implements Serializable{
//    cdi 

        @EJB
        private HorarioFacadeLocal hfl;
                
        private Horario   horarioSeleccionado;
    
        private List<Horario>   horarios;
    
    public ListarHorarioController() {
    }

    @PostConstruct
    public void init() {
        recargarHorario();

    }

    private void recargarHorario() {
       horarios = hfl.findAll();
    }
    
    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Horario getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    public void setHorarioSeleccionado(Horario horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;
    }
    
     
   
    
    public void versionMojarra(){
    
    Package p = FacesContext.class.getPackage();
     System.out.println(p.getImplementationTitle() + " " + p.getImplementationVersion());
    }
    
    public void eliminarHorario(){
        
      
          try {
            hfl.remove(horarioSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("El horario fue eliminado con exito", "El horario fue eliminado sin problemas");

        } catch (Exception e) {
            
            e.getStackTrace();
          MessageUtil.enviarMensajeInformacionGlobal("El horario no se pudo eliminar", "Hubo un error al intentar eliminar el horario");

        }
            
     
      
    }
    


}
