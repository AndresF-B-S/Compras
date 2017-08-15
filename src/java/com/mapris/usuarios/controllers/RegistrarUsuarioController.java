/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.usuarios.controllers;

import com.mapris.modelo.dao.EstadoFacadeLocal;
import com.mapris.modelo.dao.RolFacadeLocal;
import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.modelo.entitie.Rol;

import com.mapris.modelo.entitie.Usuario;
import com.mapris.util.MessageUtil;
import java.util.ArrayList;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "registroController")
@RequestScoped
public class RegistrarUsuarioController {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    private RolFacadeLocal rolFacedaLocal;
    @EJB
    private EstadoFacadeLocal estadoFacadeLocal;

    private Usuario nuevoUsuario;
    private Calendar hoy;

    public RegistrarUsuarioController() {
    }

    @PostConstruct
    public void init() {
        nuevoUsuario = new Usuario();
        this.hoy = Calendar.getInstance();
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public void registrar() {
        if (nuevoUsuario != null) {
            try {
                
                 Calendar cal = Calendar.getInstance();
                 cal.setTime(nuevoUsuario.getFechaNaci());
                 
                
                if (cal.get(Calendar.YEAR) >= this.hoy.get(Calendar.YEAR) ) {
                    
                    nuevoUsuario=null;
                    System.out.println("No paso 1");
                    MessageUtil.enviarMensajeErrorGlobal("No se puede registrar", "La fecha introducida es igual o supera el año actual.");
                    
                }else if(cal.get(Calendar.YEAR) > (this.hoy.get(Calendar.YEAR)-18)   ){
                    
                    System.out.println("" + (this.hoy.get(Calendar.YEAR)-18) );
                    nuevoUsuario=null;
                   System.out.println("No paso 2");
                    MessageUtil.enviarMensajeErrorGlobal("No se puede registrar", "Tu fecha de nacimiento indica que eres menor de edad");
                    
                
                } else{
                
                nuevoUsuario.setRoles(new ArrayList<Rol>());
                nuevoUsuario.getRoles().add(rolFacedaLocal.find(2));
                nuevoUsuario.setIdEstados(estadoFacadeLocal.find(2));
                
                
                usuarioFacadeLocal.create(nuevoUsuario);
               System.out.println("Paso ome 3");
                MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio", "El usuario se ha creado con exito");
                init();
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            MessageUtil.enviarMensajeInformacionGlobal( "Error al registrar el usuario", "No se pudo registrar el usuario");
        }
    }

}
