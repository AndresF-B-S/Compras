/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.quartz.schedule;

import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.modelo.entitie.Empresa;
import com.mapris.modelo.entitie.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Ruben
 */
@Named
@RequestScoped
public class EnviarMensajesQuartzController {
    
    @EJB
    private UsuarioFacadeLocal ufl;
    private List<Usuario> usuariosEmail;

    public EnviarMensajesQuartzController() {
    }
    
    @PostConstruct
    public void init(){
        usuariosEmail = ufl.findAll();
        
    }
    
    public void email(){
        
        try {
            InternetAddress[] address = new InternetAddress[usuariosEmail.size()];
            
            for (int i = 0; i < usuariosEmail.size(); i++) {
                
                address[i] = new InternetAddress(usuariosEmail.get(i).getCorreoElectronico());
                
            }
        } catch (AddressException addressException) {
        }
        
    
    }
    

   
    
    
    
}
