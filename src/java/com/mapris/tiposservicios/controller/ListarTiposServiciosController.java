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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "listarTipoServicioController")
@ViewScoped
public class ListarTiposServiciosController implements Serializable{
//    cdi 
        
     @EJB
    private TipoServicioFacadeLocal tpfc;

    private List<TipoServicio> tipoServicio;

    private TipoServicio tipoServicioSeleccionado;

    
    
    public ListarTiposServiciosController() {
    }

    @PostConstruct
    public void init() {
        recargarTipoServicio();
    }
    
    private void recargarTipoServicio(){
        tipoServicio = tpfc.findAll();
    }

    public List<TipoServicio> getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(List<TipoServicio> tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public TipoServicio getTipoServicioSeleccionado() {
        return tipoServicioSeleccionado;
    }

    public void setTipoServicioSeleccionado(TipoServicio tipoServicioSeleccionado) {
        this.tipoServicioSeleccionado = tipoServicioSeleccionado;
    }
    
    

    
  
    
    
    
    public void eliminarTipoServicio(){
        

          try {
            tpfc.remove(tipoServicioSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("El tipo de servicio se ha eliminado con exito", "La rutina se ha eliminado correctamente");
            recargarTipoServicio();
            
        } catch (Exception e) {
            
            MessageUtil.enviarMensajeErrorGlobal("El tipo de servicio no se pudo eliminar", "");

        }
      
    }
    


}
