/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.personalmedico.controller;


import com.mapris.modelo.dao.EstadoFacadeLocal;
import com.mapris.modelo.dao.PersonalmedicoFacadeLocal;
import com.mapris.modelo.dao.RolFacadeLocal;
import com.mapris.modelo.dao.UsuarioFacadeLocal;


import com.mapris.modelo.entitie.Personalmedico;
import com.mapris.modelo.entitie.Rol;
import com.mapris.modelo.entitie.Usuario;
import com.mapris.util.MessageUtil;
import java.util.ArrayList;

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
@Named(value = "registroPersonalMedicoController")
@RequestScoped
public class RegistrarPersonalMedicoController {

    @EJB
    private UsuarioFacadeLocal  usuarioFacadeLocal;
    
    @EJB
    private PersonalmedicoFacadeLocal personalMedicoFacadeLocal;
    
    @EJB
    private RolFacadeLocal rolFacedaLocal;
    @EJB
    private EstadoFacadeLocal estadoFacadeLocal;


    private Personalmedico nuevoPersonalMedico;
    
    private Usuario nuevoUsuario;
   

    public RegistrarPersonalMedicoController() {
    }

    @PostConstruct
    public void init() {
        nuevoPersonalMedico = new Personalmedico();
        nuevoUsuario = new Usuario();

    }

    public Personalmedico getNuevoPersonalmedico() {
        return nuevoPersonalMedico;
    }

    public void setNuevoPersonalmedico(Personalmedico nuevoPersonalMedico) {
        this.nuevoPersonalMedico = nuevoPersonalMedico;
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }
    
    public void registrar() {
       Date hoy = new Date();
        if (nuevoUsuario != null && nuevoPersonalMedico != null) {
        
        
        try{
                
                
               
                nuevoUsuario.setCedula(nuevoUsuario.getCedula());
                nuevoUsuario.setFechaRegistro(hoy);
                nuevoUsuario.setRoles(new ArrayList<Rol>());
                nuevoUsuario.getRoles().add(rolFacedaLocal.find(3));
                nuevoUsuario.setIdEstados(estadoFacadeLocal.find(1));
                usuarioFacadeLocal.create(nuevoUsuario);
                
                Usuario user = usuarioFacadeLocal.buscarIdUsuario(nuevoUsuario.getCedula());
                
                nuevoPersonalMedico.setIdUsuario(user.getIdUsuario());
                nuevoPersonalMedico.setPerfilProfesional(nuevoPersonalMedico.getPerfilProfesional());
                nuevoPersonalMedico.setCargo(nuevoPersonalMedico.getCargo());
            
                personalMedicoFacadeLocal.create(nuevoPersonalMedico);
                MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio", "El personal medico se ha creado con exito");
                init();
                
                
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No se puedo registrar" + e.getMessage());
                System.out.println("cedula: " + nuevoUsuario.getIdUsuario() + "Id Usuario " + nuevoPersonalMedico.getUsuario().getIdUsuario());

            }
        } else {
            MessageUtil.enviarMensajeInformacionGlobal( "Error al registrar el usuario", "No se pudo registrar el personal medico");
        }
    }

}
