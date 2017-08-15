package com.mapris.aplazamiento.controllers;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.dao.AplazamientoFacadeLocal;
import com.mapris.modelo.dao.ClienteFacadeLocal;

import com.mapris.modelo.dao.InscripcionFacadeLocal;
import com.mapris.modelo.entitie.Aplazamiento;
import com.mapris.modelo.entitie.Inscripcion;
import javax.inject.Named;
import com.mapris.util.Fecha;
import com.mapris.util.MessageUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.*;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author andres
 */
@Named(value = "registrarAplazamientoController")
@SessionScoped
public class RegistrarAplazamientoController implements Serializable {

    @Inject
    private SessionController sessionController;
    @EJB
    private InscripcionFacadeLocal inscripcionFacadeLocal;
    @EJB
    private AplazamientoFacadeLocal aplazamientoFacadeLocal;

    private Inscripcion inscripcionSeleccionado;
   

    private Aplazamiento nuevoAplazamiento;

    public RegistrarAplazamientoController() {
    }

    @PostConstruct
    public void init() {
        nuevoAplazamiento = new Aplazamiento();

    }

    public Inscripcion getInscripcionSeleccionado() {
        return inscripcionSeleccionado;
    }

    public void setInscripcionSeleccionado(Inscripcion inscripcionSeleccionado) {
        this.inscripcionSeleccionado = inscripcionSeleccionado;
    }

    public Aplazamiento getNuevoAplazamiento() {
        return nuevoAplazamiento;
    }

    public void setNuevoAplazamiento(Aplazamiento nuevoAplazamiento) {
        this.nuevoAplazamiento = nuevoAplazamiento;
    }

    /**
     * Creates a new instance of RegistrarSesionController
     */
    

    public Boolean existeInscripcionSeleccionado() {
        if (sessionController.getUsuario().getCliente().getInscripciones() != null ) {
            return true;
        } else {
            return false;
        }
    }

    public List<Inscripcion> getInscripcionesCliente() {
        return sessionController.getUsuario().getCliente().getInscripciones();
    }

    public void registrar() {

        if (existeInscripcionSeleccionado() == true) {
            
       
        if (nuevoAplazamiento != null) {
            try {
                
                inscripcionSeleccionado.setEstado("Aplazado");
                nuevoAplazamiento.setidUsuario(sessionController.getUsuario().getIdUsuario());
                aplazamientoFacadeLocal.create(nuevoAplazamiento);
                inscripcionFacadeLocal.edit(inscripcionSeleccionado);
                init();
                MessageUtil.enviarMensajeInformacion("form-registrarAplazamiento", "Registro satisfactorio", "");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error aplazamiento registro");
                MessageUtil.enviarMensajeError("form-registrarAplazamiento", "Error al registrar el aplazamiento en la Base de Datos ", "");
            }
        } else {
            System.out.println("NULO aplazamiento registro");
            MessageUtil.enviarMensajeError("form-registrarAplazamiento", "no se han diligenciado los campos ", "");
        }
    } else {
        
      MessageUtil.enviarMensajeError("form-registrarAplazamiento", "No se puede completar el aplazamiento ", " Debido a que no esta inscrito a ningun servicio");

        
        
        }
  }

}
