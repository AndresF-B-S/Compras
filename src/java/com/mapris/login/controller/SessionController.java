/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.login.controller;

import com.mapris.controller.rules.SessionRule;
import com.mapris.modelo.entitie.Permiso;
import com.mapris.modelo.entitie.Rol;
import com.mapris.modelo.entitie.Usuario;


import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ismael
 */
@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {
  
    @EJB
    private SessionRule sr;
    
    private String clave;
    private String documento;
    private Rol rolSeleccionado;
    private Usuario usuario;
    private Locale seleccionarLenguaje = new Locale("es");
    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }
    
    @PostConstruct
    public void init(){
        
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Locale idiomaUsuario = ec.getRequestLocale();
        boolean support = false;
        for (Locale l : getSupportLanguages()) {
            if(l.getLanguage().equals(idiomaUsuario.getLanguage())){
                support = true; 
                break;
            }
        }
        seleccionarLenguaje = (support) ? idiomaUsuario: new Locale("es");
    }
    
    
    
    public void setClave(String clave) {
        this.clave = clave;
    }
     public String getClave() {
        return clave;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Locale getSeleccionarLenguaje() {
        return seleccionarLenguaje;
    }

    public void setSeleccionarLenguaje(Locale seleccionarLenguaje) {
        this.seleccionarLenguaje = seleccionarLenguaje;
    }
    
      public List<Locale> getSupportLanguages(){
        List<Locale> idiomas = new ArrayList<>();
        Iterator<Locale> it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        while(it.hasNext()){
            idiomas.add(it.next());
        }
        return idiomas;
    }
    
    public String iniciarSesion(){
        String urlDestino = "";
        usuario = sr.iniciar(documento, clave);
        if(usuario != null){
            rolSeleccionado = sr.validarRol(usuario);
            if(rolSeleccionado != null){
                urlDestino = "/app/index.xhtml?faces-redirect=true";
            } else{
                usuario = null;
            }
        }
        return urlDestino;
    }

    public String cerrarSesion(){
        
        usuario = null;
        clave = null;
        documento = null;
        rolSeleccionado = null;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
   
      public Boolean inicioSesion(){
        return (usuario != null);
    }
     
    public String cambiarIdioma(Locale idioma){
        if(idioma != null){
            this.seleccionarLenguaje = idioma;
            FacesContext.getCurrentInstance().getViewRoot().setLocale(seleccionarLenguaje);
        }
        return "";
    }
     
     public Boolean tienePermiso(String urlRecurso){
        if(urlRecurso.endsWith("app/index.xhtml")){
            return true;
        }
        for (Permiso p : rolSeleccionado.getPermisos()) {
            if(p.getUrl() != null && urlRecurso.endsWith(p.getUrl())){
                return true;
            }
        }
        return false;
     }
    
    @PreDestroy
    public void perDestroy(){
        cerrarSesion();
    }
    
    
    
}
