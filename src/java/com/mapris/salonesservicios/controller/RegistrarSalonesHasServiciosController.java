/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.salonesservicios.controller;


import com.mapris.modelo.dao.SalonHasServicioFacadeLocal;
import com.mapris.modelo.entitie.Salon;


import com.mapris.modelo.entitie.SalonHasServicio;
import com.mapris.modelo.entitie.SalonHasServicioPK;
import com.mapris.modelo.entitie.Servicio;
import com.mapris.util.MessageUtil;
import java.io.Serializable;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "registrarSalonesHasServiciosController")
@SessionScoped
public class RegistrarSalonesHasServiciosController implements Serializable{

    @EJB
    private SalonHasServicioFacadeLocal sfl;


    private SalonHasServicio nshs;
    
    private Salon salon;
    
    private Servicio servicio;
            
    private SalonHasServicioPK shspk;


    public RegistrarSalonesHasServiciosController() {
    }

    @PostConstruct
    public void init() {
        nshs = new SalonHasServicio ();
        salon = new Salon();
        servicio = new Servicio();
        shspk = new SalonHasServicioPK();

    }

    public SalonHasServicio  getNuevoSalon() {
        return nshs;
    }

    public void setNuevoSalon(SalonHasServicio  nshs) {
        this.nshs = nshs;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    

    public void registrar() {
          
        if (nshs != null) {
        
        
        try{       
                   shspk.setSalonesIdSalones(getSalon().getIdSalones());
                   shspk.setServiciosIdServicio(getServicio().getIdServicio());
                   nshs.setSalonHasServicioPK(shspk);
                   nshs.setSalon(salon);
                   nshs.setServicio(servicio);
                   nshs.setEstado("Vinculado");
                
                
                sfl.create(nshs);
               
                MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio", "El salón se ha creado con exito");
                init();
                
                
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Salon: " + getSalon().getIdSalones() );
                System.out.println("Servicio: " + getServicio().getIdServicio());
            }
        } else {
            MessageUtil.enviarMensajeInformacionGlobal( "Error al registrar el salón", "No se pudo registrar el salón");
        }
    }

}
