/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mapris.cursos.controllers;


import com.mapris.modelo.dao.CursoFacadeLocal;
import com.mapris.modelo.entitie.Curso;
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
@Named(value = "editarCursosController")
@SessionScoped
public class EditarCursosController implements Serializable {

    @EJB
    private CursoFacadeLocal cfl;
    
    private Curso cursoSeleccionado;
    
    
    @PostConstruct
    public void init(){
    
    cursoSeleccionado= new Curso();   
   
    }

    public EditarCursosController() {
    }

    public Curso getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(Curso cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }
    
    

   

    public void actualizarDatos() {
        try {
            
            
           
            
            cfl.edit(cursoSeleccionado);
            MessageUtil.enviarMensajeInformacion("form-editar", "Actualizacion exitosa", "los datos fueron actualizados");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al modificar los datos del curso","");
        }

    }
    
    public String preModificar(Curso c){
        setCursoSeleccionado(c);
        return "/app/administrador/cursos/editar.xhtml?faces-redirect=true";
    }
    
    
    
    public void cambioDeEstado(Curso c) {
        try {
            if (c.getEstado().equalsIgnoreCase("En proceso")) {
                c.setEstado("Finalizado");

            } else {
                c.setEstado("En proceso");
            }
            cfl.edit(c);
            MessageUtil.enviarMensajeInformacion("form-data-table-usuarios", "Actualización", "Se ha cambiado el estado del curso.");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("Error al cambiar el estado del curso", "");
        }

    }

    public String getIconUsuarioBloqueo(Curso c) {
        return (c.getEstado().equalsIgnoreCase("En proceso")) ? "repeat" : "remove";
    }
      
    

}
