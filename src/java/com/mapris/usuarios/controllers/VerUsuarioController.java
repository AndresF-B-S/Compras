/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.usuarios.controllers;

import com.mapris.modelo.entitie.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value="verUsuarioControoller")
@SessionScoped
public class VerUsuarioController implements Serializable{
    
    private Usuario usuario;

    public VerUsuarioController() {
    }
    
    @PostConstruct
    public void init(){
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String verUsuario(Usuario u){
    this.usuario = u;
    return "/app/usuarios/ver.xhtml?faces-redirect=true";
    }
    
    
}
