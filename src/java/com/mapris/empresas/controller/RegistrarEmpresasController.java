/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.empresas.controller;


import com.mapris.modelo.dao.EmpresaFacadeLocal;


import com.mapris.modelo.entitie.Empresa;
import com.mapris.util.MessageUtil;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "registrarEmpresasController")
@RequestScoped
public class RegistrarEmpresasController {

    @EJB
    private EmpresaFacadeLocal empresaFacadeLocal;


    private Empresa nuevaEmpresa;


    public RegistrarEmpresasController() {
    }

    @PostConstruct
    public void init() {
        nuevaEmpresa = new Empresa();

    }

    public Empresa getNuevoEmpresa() {
        return nuevaEmpresa;
    }

    public void setNuevoEmpresa(Empresa nuevaEmpresa) {
        this.nuevaEmpresa = nuevaEmpresa;
    }

    public void registrar() {
        
        Date hoy = new Date();
        if (nuevaEmpresa != null) {
        
        
        try{
                
                nuevaEmpresa.setIdEmpresa(Integer.BYTES);
                nuevaEmpresa.setNit(nuevaEmpresa.getNit());
               
                nuevaEmpresa.setNombre(nuevaEmpresa.getNombre());
                nuevaEmpresa.setDireccionP(nuevaEmpresa.getDireccionP());
                nuevaEmpresa.setTelefonoC(nuevaEmpresa.getTelefonoC());
                nuevaEmpresa.setTelefonoF(nuevaEmpresa.getTelefonoF());
                
                
                empresaFacadeLocal.create(nuevaEmpresa);
               
                MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio", "La empresa se ha creada con exito");
                init();
                
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            MessageUtil.enviarMensajeInformacionGlobal( "Error al registrar el salón", "No se pudo registrar la empresa");
        }
    }

}
