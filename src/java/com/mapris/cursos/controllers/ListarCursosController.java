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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "listarCursosController")
@ViewScoped
public class ListarCursosController implements Serializable
{

      @EJB
      private CursoFacadeLocal cfl;
      
      private List<Curso> cursos;
      
      private Curso   cursoSeleccionado;
   

    public ListarCursosController() {
    }

    @PostConstruct
    public void init() {
       recargarCursos();
    }
    
    private void recargarCursos(){
     cursos = cfl.findAll();
    
    }
    
    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(Curso cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }
    
    
    public void eliminarCurso(){
        
        if (cursoSeleccionado.getEstado().equalsIgnoreCase("En proceso")) {
        
            
        MessageUtil.enviarMensajeErrorGlobal("No se puede eliminar un curso en proceso", "Por favor cambie el estado del curso para poder eliminarlo");

        
        }else{
        
        try {
            cfl.remove(cursoSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Se ha eliminado satisfactoriamente el curso", "");
            
        } catch (Exception e) {
            e.getStackTrace();
            MessageUtil.enviarMensajeErrorGlobal("No se ha podido eliminar el curso", "Ha ocurrido un problema");
            
        }
        
        
        
        }
        
    }
   
    
    
    }


