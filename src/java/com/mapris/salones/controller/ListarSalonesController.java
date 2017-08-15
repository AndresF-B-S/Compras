
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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "listarSalonesController")
@ViewScoped
public class ListarSalonesController implements Serializable{
//    Code Dependention Injection


    @EJB
    private SalonFacadeLocal sfl;

    private List<Salon> salones;

    private Salon salonesSelecionados;
   

    
    
    
    public ListarSalonesController() {
    }

    @PostConstruct
    public void init() {
        recargarSalones();
       
    }
    
    private void recargarSalones(){
        salones = sfl.findAll();
    }
    
     public SalonFacadeLocal getSalonFacadeLocal() {
        return sfl;
    }

    public void setSalonFacadeLocal(SalonFacadeLocal sfl) {
        this.sfl = sfl;
    }

    public List<Salon> getSalones() {
        return salones;
    }

    public void setSalonSeleccionado(Salon salonesSelecionados) {
        this.salonesSelecionados = salonesSelecionados;
    }


    public Salon getSalonSeleccionado() {
        return this.salonesSelecionados;
    }
    
    public void eliminarSalon(){
     
        
        try {
            sfl.remove(salonesSelecionados);
            MessageUtil.enviarMensajeInformacionGlobal("Eliminación correcta","Se elimino correctamente las salones ");
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("Error al eliminar las salones","No se puede eliminar las salones");
        }
        
    }
        
    
    
    

   


}
