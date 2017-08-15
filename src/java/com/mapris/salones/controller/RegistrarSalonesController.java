/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.salones.controller;


import com.mapris.modelo.dao.SalonFacadeLocal;


import com.mapris.modelo.entitie.Salon;
import com.mapris.util.MessageUtil;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "registrarSalonesController")
@RequestScoped
public class RegistrarSalonesController {

    @EJB
    private SalonFacadeLocal salonFacadeLocal;


    private Salon nuevoSalon;


    public RegistrarSalonesController() {
    }

    @PostConstruct
    public void init() {
        nuevoSalon = new Salon();

    }

    public Salon getNuevoSalon() {
        return nuevoSalon;
    }

    public void setNuevoSalon(Salon nuevoSalon) {
        this.nuevoSalon = nuevoSalon;
    }

    public void registrar() {
        if (nuevoSalon != null) {
        
        
        try{
                
                nuevoSalon.setIdSalones(nuevoSalon.getIdSalones());
               
              
                nuevoSalon.setSalon(nuevoSalon.getSalon());
                nuevoSalon.setDescripcion(nuevoSalon.getDescripcion());
                nuevoSalon.setEstado(nuevoSalon.getEstado());
                
                
                
                salonFacadeLocal.create(nuevoSalon);
               
                MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio", "El salón se ha creado con exito");
                init();
                
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            MessageUtil.enviarMensajeInformacionGlobal( "Error al registrar el salón", "No se pudo registrar el salón");
        }
    }

}
