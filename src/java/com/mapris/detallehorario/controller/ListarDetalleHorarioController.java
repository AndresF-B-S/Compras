package com.mapris.detallehorario.controller;

import com.mapris.modelo.dao.DetalleHorarioFacadeLocal;
import com.mapris.modelo.entitie.DetalleHorario;
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
@Named(value = "listarDetalleHorarioController")
@ViewScoped
public class ListarDetalleHorarioController implements Serializable {
//    Code Dependention Injection
    
    @EJB
    private DetalleHorarioFacadeLocal dhfl;
    
    private List<DetalleHorario> detalleHorario;
    
    private DetalleHorario detalleHorarioSeleccionado;
  

    public ListarDetalleHorarioController() {
    }

    @PostConstruct
    public void init() {
        recargarDetalleHorario();

    }

    private void recargarDetalleHorario() {
        detalleHorario = dhfl.findAll();
    }

    public List<DetalleHorario> getDetalleHorario() {
        return detalleHorario;
    }

    public void setDetalleHorario(List<DetalleHorario> detalleHorario) {
        this.detalleHorario = detalleHorario;
    }

    public DetalleHorario getDetalleHorarioSeleccionado() {
        return detalleHorarioSeleccionado;
    }

    public void setDetalleHorarioSeleccionado(DetalleHorario detalleHorarioSeleccionado) {
        this.detalleHorarioSeleccionado = detalleHorarioSeleccionado;
    }
    
    
    


    public void eliminarDetalleHorario() {

        try {
            dhfl.remove(detalleHorarioSeleccionado);
            MessageUtil.enviarMensajeInformacionGlobal("Eliminación correcta", "Se elimino correctamente el calendario");
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("Error al eliminar el calendario", "No se puede eliminar el calendario");
        }

    }

}
