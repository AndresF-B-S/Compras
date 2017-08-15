
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.clientes.controllers;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.dao.ClienteFacadeLocal;
import com.mapris.modelo.entitie.Cliente;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "listarClienteController")
@ViewScoped
public class ListarClienteController implements Serializable{
//    Code Dependention Injection

    @EJB
    private ClienteFacadeLocal clienteFacadeLocal;

    private List<Cliente> clientes;

    private Cliente clienteSeleccionado;
  

    
    
    
    public ListarClienteController() {
    }

    @PostConstruct
    public void init() {
        recargarClientes();
       
    }
    
    private void recargarClientes(){
        clientes = clienteFacadeLocal.findAll();
    }
    
     public ClienteFacadeLocal getClienteFacadeLocal() {
        return clienteFacadeLocal;
    }

    public void setClienteFacadeLocal(ClienteFacadeLocal clienteFacadeLocal) {
        this.clienteFacadeLocal = clienteFacadeLocal;
    }

    public List<Cliente> getClientees() {
        return clientes;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }


    public Cliente getClienteSeleccionado() {
        return this.clienteSeleccionado;
    }
    
    public void eliminarCliente(){
      
        
    }
    
        
    
    
    

   


}
