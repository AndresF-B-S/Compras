/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.inscripciones.controller;

import com.mapris.modelo.dao.InscripcionFacadeLocal;
import com.mapris.modelo.entitie.Inscripcion;
import com.mapris.util.MessageUtil;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "registroInscripcionController")
@RequestScoped
public class RegistrarInscripcionController {

    @EJB
    private InscripcionFacadeLocal inscripcionFacadeLocal;

    private Inscripcion nuevoInscripcion;

    public RegistrarInscripcionController() {
    }

    @PostConstruct
    public void init() {
        nuevoInscripcion = new Inscripcion();

    }

    public Inscripcion getNuevoInscripcion() {
        return nuevoInscripcion;
    }

    public void setNuevoInscripcion(Inscripcion nuevoInscripcion) {
        this.nuevoInscripcion = nuevoInscripcion;
    }

    public void registrar() {
        if (nuevoInscripcion != null) {
            try {

                nuevoInscripcion.setEstado("Activa");


                inscripcionFacadeLocal.create(nuevoInscripcion);

                MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio", "El usuario se ha creado con exito");
                init();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            MessageUtil.enviarMensajeInformacionGlobal("Error al registrar el usuario", "No se pudo registrar el usuario");
        }
    }

}
