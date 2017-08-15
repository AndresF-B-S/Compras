/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.aplazamiento.controllers;

import com.mapris.modelo.dao.AplazamientoFacadeLocal;
import com.mapris.modelo.dao.InscripcionFacadeLocal;
import com.mapris.modelo.entitie.Aplazamiento;
import com.mapris.modelo.entitie.Inscripcion;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */

@Named(value = "listarAplazamientoController")
@ViewScoped
public class ListarAplazamientoController implements Serializable{
    
    @EJB
    private AplazamientoFacadeLocal aplazamientoFacadeLocal;
    @EJB
    private InscripcionFacadeLocal ifc;
    
    private List<Aplazamiento> aplazamientos;
    
    private Inscripcion inscripcionSeleccionada;
    
    private Aplazamiento aplazamientoSeleccionado;

    public ListarAplazamientoController() {
    }

    @PostConstruct
    public void init(){
        recargarAplazamientos();
    }
    
    public void recargarAplazamientos(){
        aplazamientos = aplazamientoFacadeLocal.findAll();
    }
    
    public void eliminarAplazamiento(){
    
        try {
            
            this.inscripcionSeleccionada = aplazamientoSeleccionado.getCliente().getInscripciones().get(0);
            inscripcionSeleccionada.setEstado("Activa");
            ifc.edit(inscripcionSeleccionada);
            aplazamientoFacadeLocal.remove(aplazamientoSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Se elimino exitosamente el aplazamiento", "El aplazamiento del cliente se elimino exitosamente");

        } catch (Exception e) {
            e.getStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("No se pudo eliminar el aplazamiento", "El aplazamiento del cliente no se pudo eliminar");
        }
    }
    
    public List<Aplazamiento> getAplazamientos() {
        return aplazamientos;
    }

    
    
    public Aplazamiento getAplazamientoSeleccionado() {
        return aplazamientoSeleccionado;
    }

    public void setAplazamientoSeleccionado(Aplazamiento aplazamientoSeleccionado) {
        this.aplazamientoSeleccionado = aplazamientoSeleccionado;
    }
    
    
    
    
    
}
