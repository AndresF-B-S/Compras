/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.personalmedico.controller;

import com.mapris.modelo.entitie.Personalmedico;
import com.mapris.modelo.entitie.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value="verPersonalMedicoController")
@SessionScoped
public class VerPersonalMedicoController implements Serializable{
    
    private Personalmedico personalMedico;
   

    public VerPersonalMedicoController() {
    }
    
    @PostConstruct
    public void init(){
    }

    public Personalmedico getPersonalmedico() {
        return personalMedico;
    }

    public void setPersonalmedico(Personalmedico personalMedico) {
        this.personalMedico = personalMedico;
    }

    
    
    
    public String verPersonalmedico(Personalmedico i){
    this.personalMedico = i;
   
    return "/app/administrador/personal/ver.xhtml?faces-redirect=true";
    }
    
    
}
