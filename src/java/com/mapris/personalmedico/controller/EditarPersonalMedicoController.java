/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.personalmedico.controller;

import com.mapris.modelo.dao.PersonalmedicoFacadeLocal;
import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.modelo.entitie.Personalmedico;
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
@Named(value = "editarPersonalMedicoController")
@SessionScoped
public class EditarPersonalMedicoController implements Serializable {

    @EJB
    private PersonalmedicoFacadeLocal pmfl;
    @EJB
    private UsuarioFacadeLocal ufl;
    
    private Personalmedico personalSeleccionado;
    private Usuario usuarioSeleccionado;
   
    
    @PostConstruct
    public void init(){
    personalSeleccionado = new Personalmedico();
    usuarioSeleccionado = new Usuario();
    }

    public EditarPersonalMedicoController() {
    }

    public Personalmedico getPersonalmedicoSelecionada() {
        return personalSeleccionado;
    }

    public void setPersonalmedicoSelecionada(Personalmedico personalSeleccionado) {
        this.personalSeleccionado = personalSeleccionado;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    

   public void actualizarDatos() {
        try {
            
            
            
            
            personalSeleccionado.setCargo(personalSeleccionado.getCargo());
            personalSeleccionado.setPerfilProfesional(personalSeleccionado.getPerfilProfesional());
//       
            
            pmfl.edit(personalSeleccionado);
            ufl.edit(usuarioSeleccionado);
            
            MessageUtil.enviarMensajeInformacionGlobal("Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos de la inscripción", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(Personalmedico i, Usuario u){
        setPersonalmedicoSelecionada(i);
        setUsuarioSeleccionado(u);
        return "/app/administrador/personal/editar.xhtml?faces-redirect=true";
    }
    
   

    
    
      
    

}
