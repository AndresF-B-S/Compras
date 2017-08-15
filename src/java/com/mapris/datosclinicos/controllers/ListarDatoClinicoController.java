
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.datosclinicos.controllers;

import com.mapris.modelo.dao.DatoclinicoFacadeLocal;
import com.mapris.modelo.entitie.Datoclinico;
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
@Named(value = "listarDatoClinicoController")
@ViewScoped
public class ListarDatoClinicoController implements Serializable{
//    Code Dependention Injection

    @EJB
    private DatoclinicoFacadeLocal dcfl;

    private List<Datoclinico> datoClinico;

    private Datoclinico datoClinicoSeleccionado;
  

    
    
    
    public ListarDatoClinicoController() {
    }

    @PostConstruct
    public void init() {
        recargarDatoclinicos();
       
    }
    
    private void recargarDatoclinicos(){
        datoClinico = dcfl.findAll();
    }
    
     public DatoclinicoFacadeLocal getDatoclinicoFacadeLocal() {
        return dcfl;
    }

    public void setDatoclinicoFacadeLocal(DatoclinicoFacadeLocal dcfl) {
        this.dcfl = dcfl;
    }

    public List<Datoclinico> getDatoclinicoes() {
        return datoClinico;
    }

    public void setDatoclinicoSeleccionado(Datoclinico datoClinicoSeleccionado) {
        this.datoClinicoSeleccionado = datoClinicoSeleccionado;
    }


    public Datoclinico getDatoclinicoSeleccionado() {
        return this.datoClinicoSeleccionado;
    }
    
    public void eliminarDatoclinico(){
        if (datoClinicoSeleccionado != null) {
            
            dcfl.remove(datoClinicoSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Se elimino satisfactoriamente", "Se elimino satisfactoriamente el dato clinico");
            
        }else{
        
            MessageUtil.enviarMensajeErrorGlobal("No se pudo eliminar", "No se pudo elimnar el dato clinico");
        }
        
    }
        
    
    
    

   


}
