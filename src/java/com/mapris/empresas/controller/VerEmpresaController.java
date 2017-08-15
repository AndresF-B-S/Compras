/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.empresas.controller;

import com.mapris.modelo.entitie.Empresa;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verEmpresaController")
@SessionScoped
public class VerEmpresaController implements Serializable{
    
    private Empresa empresa;

    public VerEmpresaController() {
    }
    
    @PostConstruct
    public void init(){
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public String verEmpresa(Empresa e){
    this.empresa = e;
    return "/app/administrador/empresa/ver.xhtml?faces-redirect=true";
    }
    
    
}
