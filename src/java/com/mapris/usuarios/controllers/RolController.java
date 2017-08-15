/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.usuarios.controllers;

import com.mapris.modelo.dao.RolFacadeLocal;
import com.mapris.modelo.entitie.Rol;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "rolContoller")
@RequestScoped
public class RolController {
    
   @EJB
   private RolFacadeLocal rolFacadeLocal;
   
   private List<Rol> roles ;

   
   @PostConstruct
   public void init(){
        roles = rolFacadeLocal.findAll();
   }
   
    public RolController() {
    }

    public List<Rol> getRoles() {
        return roles;
    }

    
   
   
    
}
