/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.perfil.controller;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Ruben
 */
@Named(value = "modificarPerfilController")
@ViewScoped
public class ModificarPerfilController implements Serializable{

    @Inject
    private SessionController sc;
    @EJB
    private UsuarioFacadeLocal ufl;
    /**
     * Creates a new instance of ModificarPerfilController
     */
    public ModificarPerfilController() {
    }
    
    @PostConstruct
    private void init(){
    
    }

    public SessionController getSc() {
        return sc;
    }

    public void setSc(SessionController sc) {
        this.sc = sc;
    }

    public UsuarioFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuarioFacadeLocal ufl) {
        this.ufl = ufl;
    }
    
    
    public void actualizarPerfil(){
    
    
        try {
            System.out.println("Nuevo nombre: " + getSc().getUsuario().getNombres());
            System.out.println("Nuevo apellido: " + getSc().getUsuario().getApellidos());
            ufl.edit(getSc().getUsuario());
            MessageUtil.enviarMensajeInformacionGlobal("Se ha actualizado con exito", "Su información personal ha sido actualizada correctamente");
        } catch (Exception e) {
           MessageUtil.enviarMensajeErrorGlobal("No se ha podido actualizar", "Su información personal no ha sido actualizada correctamente");

        }
    
    
    
    }
    
}
