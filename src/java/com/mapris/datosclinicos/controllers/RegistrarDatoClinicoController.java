/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.datosclinicos.controllers;

import com.mapris.archivos.controller.CargarArchivosController;
import com.mapris.login.controller.SessionController;
import com.mapris.modelo.dao.EstadoFacadeLocal;
import com.mapris.modelo.dao.ClienteFacadeLocal;
import com.mapris.modelo.dao.RolFacadeLocal;
import com.mapris.modelo.dao.DatoclinicoFacadeLocal;
import com.mapris.modelo.dao.UsuarioFacadeLocal;

import com.mapris.modelo.entitie.Cliente;
import com.mapris.modelo.entitie.Rol;
import com.mapris.modelo.entitie.Datoclinico;
import com.mapris.util.MessageUtil;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author SMEGS
 */
@Named(value = "registroDatoClinicoController")
@RequestScoped
public class RegistrarDatoClinicoController {

    @Inject
    CargarArchivosController ca;

    @Inject
    private SessionController sc;

    @EJB
    private DatoclinicoFacadeLocal dcfl;

    @EJB
    private UsuarioFacadeLocal ufl;

    private Datoclinico nuevoDatoclinico;

    private List<Datoclinico> hayDato;

    public RegistrarDatoClinicoController() {
    }

    @PostConstruct
    public void init() {

        recargarHayDato();
        nuevoDatoclinico = new Datoclinico();

    }

    public void recargarHayDato() {
        hayDato = dcfl.buscarDato(sc.getUsuario().getCliente());
    }

    public Datoclinico getNuevoDatoclinico() {
        return nuevoDatoclinico;
    }

    public void setNuevoDatoclinico(Datoclinico nuevoDatoclinico) {
        this.nuevoDatoclinico = nuevoDatoclinico;
    }

    public void registrar() {

        if (hayDato.isEmpty() == false) {

            MessageUtil.enviarMensajeErrorGlobal("Error al registrar un dato clinico", "Ya tienes un dato clinico registrado. Eliminalo primero, para poder registrar uno nuevo");
            System.out.println("Datos Clinicos: " + hayDato.isEmpty());
        } else {

            if (nuevoDatoclinico != null) {

                try {
                    System.out.println("Datos Clinicos: " + hayDato.size());
                    nuevoDatoclinico.setIdUsuarios(sc.getUsuario().getCliente());

                    ca.uploadProfileClinicalData();
                    nuevoDatoclinico.setUrl(ca.getRuta());

                    dcfl.create(nuevoDatoclinico);

                    MessageUtil.enviarMensajeInformacionGlobal("Registro satisfactorio", "El cliente se ha creado con exito");
                    init();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("id cliente: " + sc.getUsuario().getCliente());

                }
            } else {
                MessageUtil.enviarMensajeInformacionGlobal("Error al registrar el usuario", "No se pudo registrar el cliente");
            }

        }

    }

}
