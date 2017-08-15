
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.servicios.controller;

import com.mapris.modelo.dao.ServicioFacadeLocal;
import com.mapris.modelo.entitie.Servicio;
import com.mapris.util.MessageUtil;
import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author KoLachito
 */
@Named(value = "listarServicioController")
@ViewScoped
public class ListarServicioController implements Serializable {
//    Code Dependention Injection

    @EJB
    private ServicioFacadeLocal servicioFacadeL;

    private List<Servicio> servicios;

    private Servicio servicioSeleccionado;

    public ListarServicioController() {
    }

    @PostConstruct
    public void init() {
        recargarServicios();

    }

    private void recargarServicios() {
        servicios = servicioFacadeL.findAll();
    }

    public ServicioFacadeLocal getServicioFacadeLocal() {
        return servicioFacadeL;
    }

    public void setServicioFacadeLocal(ServicioFacadeLocal servicioFacadeLocal) {
        this.servicioFacadeL = servicioFacadeLocal;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicioSeleccionado(Servicio servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

    public Servicio getServicioSeleccionado() {
        return this.servicioSeleccionado;
    }

    public void eliminarServicio() {

        try {
            servicioFacadeL.remove(servicioSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Eliminación correcta", "Se elimino correctamente las sesiones ");
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("Error al eliminar las sesiones", "No se puede eliminar las sesiones");
        }

    }

}
