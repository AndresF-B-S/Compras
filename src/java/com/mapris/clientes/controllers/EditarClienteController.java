/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.clientes.controllers;

import com.mapris.modelo.dao.ClienteFacadeLocal;
import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.modelo.entitie.Cliente;
import com.mapris.modelo.entitie.Usuario;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "editarClienteController")
@SessionScoped
public class EditarClienteController implements Serializable {

    @EJB
    private ClienteFacadeLocal cfl;
    @EJB
    private UsuarioFacadeLocal ufl;
    
    private Cliente clienteSelecionado;
    private Usuario usuarioSeleccionado;
   
    
    @PostConstruct
    public void init(){
    clienteSelecionado = new Cliente();
    usuarioSeleccionado = new Usuario();
    }

    public EditarClienteController() {
    }

    public Cliente getClienteSelecionada() {
        return clienteSelecionado;
    }

    public void setClienteSelecionada(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    

   public void actualizarDatos() {
        try {
            
            
            
            
//        
            cfl.edit(clienteSelecionado);


            
            MessageUtil.enviarMensajeInformacionGlobal("Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos de la inscripción", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(Cliente i){
        setClienteSelecionada(i);
     
        return "/app/administrador/clientes/editar.xhtml?faces-redirect=true";
    }
    
   

    
    
      
    

}
