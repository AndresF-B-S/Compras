
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.inscripciones.controller;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.dao.InscripcionFacadeLocal;
import com.mapris.modelo.entitie.Inscripcion;

import com.mapris.util.MessageUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "listarInscripcionController")
@ViewScoped
public class ListarInscripcionController implements Serializable {
//    Code Dependention Injection

    @Inject
    private SessionController sessionController;

    @EJB
    private InscripcionFacadeLocal ifl;

    private List<Inscripcion> inscripciones;

    private Inscripcion inscripcionSeleccionado;

    private Calendar hoy;

    public ListarInscripcionController() {
    }

    @PostConstruct
    public void init() {
        recargarInscripcions();
        this.hoy = Calendar.getInstance();
    }

    
    
    
    private void recargarInscripcions(){
       inscripciones = ifl.findAll();}


    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public Inscripcion getInscripcionSeleccionado() {
        return inscripcionSeleccionado;
    }

    public void setInscripcionSeleccionado(Inscripcion inscripcionSeleccionado) {
        this.inscripcionSeleccionado = inscripcionSeleccionado;
    }

  

    
    
  
    public void eliminarInscripcion() {
        System.out.println("DIA=" + this.hoy.get(Calendar.DAY_OF_MONTH) + "MES: " + (this.hoy.get(Calendar.MONTH) + 1) + "AÑO" + this.hoy.get(Calendar.YEAR));

        Calendar cal = Calendar.getInstance();
        cal.setTime(inscripcionSeleccionado.getFkIdCurso().getHorarios().get(0).getFechaInicio());
        System.out.println("DIA= " + cal.get(Calendar.DAY_OF_MONTH) + " MES: " + (cal.get(Calendar.MONTH) + 1) + " AÑO " + cal.get(Calendar.YEAR));

        //Si la fecha de hoy coincide con la fecha de la cita a eliminar no se podría eliminar porque entra en vigencia *Bussiness Rules*
        if (cal.get(Calendar.DAY_OF_MONTH) == this.hoy.get(Calendar.DAY_OF_MONTH)
                && (cal.get(Calendar.MONTH) + 1) == (this.hoy.get(Calendar.MONTH) + 1)
                && cal.get(Calendar.YEAR) == this.hoy.get(Calendar.YEAR)) {
            MessageUtil.enviarMensajeError(null, "Error al eliminar", "No se puede eliminar una inscripción que entra el mismo día de vigencia");
        } else {
//            inscripcionFacadeLocal.remove(inscripcionSeleccionado);
            recargarInscripcions();
        }

    }

}
