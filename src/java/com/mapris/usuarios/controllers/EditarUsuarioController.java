/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.usuarios.controllers;

import com.mapris.modelo.dao.EstadoFacadeLocal;
import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.modelo.entitie.Usuario;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "editarUsuarioController")
@SessionScoped
public class EditarUsuarioController implements Serializable {

    @EJB
    
    private EstadoFacadeLocal efl;
            
    @EJB
    private UsuarioFacadeLocal ufl;

    private Usuario usuarioSelecionado;

    @PostConstruct
    public void init() {
        usuarioSelecionado = new Usuario();
    }

    public EditarUsuarioController() {
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public void actualizarDatos() {
        try {



            ufl.edit(usuarioSelecionado);
            MessageUtil.enviarMensajeInformacion("form-editar", "Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos del usuario", e.getStackTrace().toString());
        }

    }

    public String preModificar(Usuario u) {
        setUsuarioSelecionado(u);
        return "/app/usuarios/editar.xhtml?faces-redirect=true";
    }

    public void cambioDeEstado(Usuario u) {
        try {
            if (u.getIdEstados().getIdEstados() == 1) {
                u.setIdEstados(efl.find(2));

            } else {
                u.setIdEstados(efl.find(1));
            }
            ufl.edit(u);
            MessageUtil.enviarMensajeInformacion("form-data-table-usuarios", "Actualización", "Se ha cambiado el estado del usuario.");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Errcambiar el estado del usuario", e.getStackTrace().toString());
        }

    }

    public String getIconUsuarioBloqueo(Usuario u) {
        return (u.getIdEstados().getIdEstados() == 2) ? "lock" : "unlock";
    }

}
