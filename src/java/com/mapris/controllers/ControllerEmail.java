/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.controllers;


import com.mapris.util.EnviarMail;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;


/**
 *
 * @author Ruben
 */
@Named(value = "controllerEmail")
@RequestScoped
public class ControllerEmail{
    
    private String nombresyAp;
    private String telefono;
    private String email;
    private String asunto;
    private String mensaje;
 
    /**
     * Creates a new instance of ControllerEmail
     */
    public ControllerEmail() {
    }
    

    public String getNombresyAp() {
        return nombresyAp;
    }

    public void setNombresyAp(String nombresyAp) {
        this.nombresyAp = nombresyAp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void enviarMensaje(){
    
      EnviarMail enviarEmail = new EnviarMail("helpmaternityclub@gmail.com", "mapris12345678", "aplicationmaternitymapris@gmail.com", nombresyAp, telefono, email, asunto, mensaje);
      enviarEmail.enviarBasic();
      enviarEmail.enviarAnswer();
        
    
    
    
    
    }
}
