/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.empresas.controller;

import com.mapris.modelo.dao.EmpresaFacadeLocal;
import com.mapris.modelo.entitie.Empresa;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "editarEmpresaController")
@SessionScoped
public class EditarEmpresaController implements Serializable {

    @EJB
    private EmpresaFacadeLocal efl;
    
    private Empresa empresaSelecionada;
    
    @PostConstruct
    public void init(){
    //Instancia una nueva empresa, para que pueda modificar la seleccionada
    empresaSelecionada = new Empresa();
    }

    public EditarEmpresaController() {
    }

    public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }

    public void actualizarDatos() {
        try {
            
            
            empresaSelecionada.setNombre(empresaSelecionada.getNombre());
            empresaSelecionada.setIdEmpresa(empresaSelecionada.getIdEmpresa());
            empresaSelecionada.setDireccionP(empresaSelecionada.getDireccionP());
            empresaSelecionada.setDireccionO(empresaSelecionada.getDireccionO());
            empresaSelecionada.setTelefonoF(empresaSelecionada.getTelefonoF());
            empresaSelecionada.setTelefonoC(empresaSelecionada.getTelefonoC());
            //Datos modificados
            
            efl.edit(empresaSelecionada);//Codigo de modificación de la empresa
            MessageUtil.enviarMensajeInformacion("form-editar", "Actualizacion", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos de la empresa", e.getStackTrace().toString());
        }

    }
    
    public String preModificar(Empresa e){
        
        setEmpresaSelecionada(e);//Datos de la empresa que se selecciono
        return "/app/administrador/empresa/editar.xhtml?faces-redirect=true";
    }
    
      
    

}
