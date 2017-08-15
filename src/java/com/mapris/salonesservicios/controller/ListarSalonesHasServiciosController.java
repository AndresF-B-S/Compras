
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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "listarSalonesHasServiciosController")
@ViewScoped
public class ListarSalonesHasServiciosController implements Serializable{
//    Code Dependention Injection


    @EJB
    private SalonHasServicioFacadeLocal sfl;

    private List<SalonHasServicio> salonesHasServicios;

    private SalonHasServicio salonesHasServiciosSelecionados;
   

    
    
    
    public ListarSalonesHasServiciosController() {
    }

    @PostConstruct
    public void init() {
        recargarSalones();
       
    }
    
    private void recargarSalones(){
        salonesHasServicios = sfl.findAll();
    }
    
     public SalonHasServicioFacadeLocal getSalonHasServicioFacadeLocal() {
        return sfl;
    }

    public void setSalonHasServicioFacadeLocal(SalonHasServicioFacadeLocal sfl) {
        this.sfl = sfl;
    }

    public List<SalonHasServicio> getSalones() {
        return salonesHasServicios;
    }

    public void setSalonSeleccionado(SalonHasServicio salonesHasServiciosSelecionados) {
        this.salonesHasServiciosSelecionados = salonesHasServiciosSelecionados;
    }


    public SalonHasServicio getSalonSeleccionado() {
        return this.salonesHasServiciosSelecionados;
    }
    
    public void eliminarSalonHasServicios(){
     
        
        try {
            sfl.remove(salonesHasServiciosSelecionados);
            MessageUtil.enviarMensajeInformacionGlobal("Eliminación correcta","Se elimino correctamente las salonesHasServicios ");
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("Error al eliminar las salonesHasServicios","No se puede eliminar las salonesHasServicios");
        }
        
    }
        
    
    
    

   


}
