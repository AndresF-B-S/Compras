/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.usuarios.controllers;

import com.mapris.modelo.dao.PermisoFacadeLocal;
import com.mapris.modelo.dao.RolFacadeLocal;
import com.mapris.modelo.entitie.Permiso;
import com.mapris.modelo.entitie.Rol;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "permisosRolesController")
@ViewScoped
public class PermisosRolesController implements Serializable {

    @EJB
    private RolFacadeLocal rolFacadeLocal;

    @EJB
    private PermisoFacadeLocal permisoFacadeLocal;

    private List<Permiso> permisos;

    private Permiso permisoSeleccionado;

    private List<Rol> roles;

    private Rol rolSelected;

    public Rol getRolSelected() {
        return rolSelected;
    }

    @PostConstruct
    public void init() {
        reloadRoles();
    }

    public void reloadRoles() {

        roles = rolFacadeLocal.findAll();
    }

    public void setRolSelected(Rol rolSelected) {
        this.rolSelected = rolSelected;
    }

    public List<Rol> getRoles() {
        return roles;
    }

}
