/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.clientes.controllers;

import com.mapris.modelo.entitie.Cliente;
import com.mapris.modelo.entitie.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verClienteController")
@SessionScoped
public class VerClienteController implements Serializable{
    
    private Cliente cliente;
   

    public VerClienteController() {
            
    }
    
    @PostConstruct
    public void init(){
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    
    public String verCliente(Cliente i){
    this.cliente = i;
   
    return "/app/administrador/clientes/ver.xhtml?faces-redirect=true";
    }
    
    
}
