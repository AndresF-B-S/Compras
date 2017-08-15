/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.controllers;

import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.modelo.entitie.Usuario;
import com.mapris.util.EnviarMailContrasena;
import com.mapris.util.MessageUtil;
import java.util.Random;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "controllerEmailContrasena")
@RequestScoped
public class ControllerEmailContrasena {

    @EJB
    private UsuarioFacadeLocal ufl;
    private String cedula;
    private String email;
    private Usuario usuarioBuscar;
    private String asuntoA;
    private String contenidoA;
    private String asuntoR;
    private String contenidoR;

    /**
     * Creates a new instance of ControllerEmailContrasena
     */
    public ControllerEmailContrasena() {
        usuarioBuscar = new Usuario();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario getUsuarioBuscar() {
        return usuarioBuscar;
    }

    public void setUsuarioBuscar(Usuario usuarioBuscar) {
        this.usuarioBuscar = usuarioBuscar;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAsuntoA() {
        return asuntoA;
    }

    public void setAsuntoA(String asuntoA) {
        this.asuntoA = asuntoA;
    }

    public String getContenidoA() {
        return contenidoA;
    }

    public void setContenidoA(String contenidoA) {
        this.contenidoA = contenidoA;
    }

    public String getAsuntoR() {
        return asuntoR;
    }

    public void setAsuntoR(String asuntoR) {
        this.asuntoR = asuntoR;
    }

    public String getContenidoR() {
        return contenidoR;
    }

    public void setContenidoR(String contenidoR) {
        this.contenidoR = contenidoR;
    }
    
     public String getClave(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

    public void enviarMensaje() {

        usuarioBuscar.setCedula(getCedula());

        if (ufl.find(usuarioBuscar.getCedula()) != null) {
            
            
            usuarioBuscar = ufl.find(usuarioBuscar.getCedula());
            
            System.out.println("" + usuarioBuscar.getCedula() + " " + usuarioBuscar.getNombres());
            System.out.println("" + getEmail());
            
            usuarioBuscar.setClave(getClave(8));

            ufl.edit(usuarioBuscar);
            
             this.asuntoR = "Recuperación de contraseña ";
            this.contenidoR = "<h1>Hola " + usuarioBuscar.getNombres() + " " + usuarioBuscar.getApellidos() 
            + "</h1>" + "</br><p>Haz solicitado un cambio de contraseña, nosotros te hemos generado una automatica, recuerda que la puedes cambiar cuando ingreses al sistema en cualquier momento" + "</p>" +
           "</br><p> Tu nueva contraseña es: "+ usuarioBuscar.getClave()+"</br>" + "<p><b>Este correo se genero automaticamente, por favor no responder este mensaje.</b></p>";
            
            this.asuntoA = "Un usuario ha hecho una recuperación de contraseña";
            this.contenidoA = "<h1> Hola administradora</h1></br>" + "<p> Un usuario de nuestro sistema ha hecho una recuperación de contraseña, recuerda que si hay mas de 5 emails de recuperación iguales al día con el mismo usuario, algo raro puede estar pasando</p>"+
                    "</br><p> El usuario que recupero y cambio su contraseña fue: " + "</br>" +usuarioBuscar.getCedula() + "</br>" + usuarioBuscar.getNombres() + " " + usuarioBuscar.getApellidos();
            
            
            EnviarMailContrasena mail = new EnviarMailContrasena("helpmaternityclub@gmail.com", "mapris12345678", "aplicationmaternitymapris@gmail.com");
           mail.enviarAnswer(this.asuntoR, this.contenidoR, getEmail());
            mail.enviarBasic(this.asuntoA, this.contenidoA);
            
            MessageUtil.enviarMensajeInformacionGlobal("El usuario existe", "Se ha enviado a su correo electronico una contraseña nueva");

        } else {

            usuarioBuscar = null;
            MessageUtil.enviarMensajeErrorGlobal("Error", "El usuario con ese número de documento no esta registrado");
        }
        
        


}
    
   
}
