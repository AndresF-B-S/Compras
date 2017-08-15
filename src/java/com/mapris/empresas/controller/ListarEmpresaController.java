
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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Ruben
 */
@Named(value = "listarEmpresaController")
@ViewScoped
public class ListarEmpresaController implements Serializable{
//    Code Dependention Injection

    @EJB
    private EmpresaFacadeLocal empresaFacadeLocal;

    private List<Empresa> empresa;

    private Empresa empresaSeleccionada;

    
    
    
    public ListarEmpresaController() {
    }

    @PostConstruct
    public void init() {
        recargarEmpresas();
    }
    
    private void recargarEmpresas(){
        empresa = empresaFacadeLocal.findAll();
    }
    
    public void eliminarEmpresa(){
        
         //Si una empresa tiene asociada mas de 5 clientes no puede eliminarse *Bussiness Rules*     
        if(empresaSeleccionada.getClientes().size() >= 5){
            MessageUtil.enviarMensajeErrorGlobal("Error al eliminar", "No se puede eliminar una empresa que tiene asociados a mas de 5 clientes");
        } else{
            empresaFacadeLocal.remove(empresaSeleccionada);
            MessageUtil.enviarMensajeInformacionGlobal("Empresa eliminada con exito", "La empresa se ha eliminado correctamente");
            recargarEmpresas();
        }
      
    }
        
    
    
    

    public EmpresaFacadeLocal getEmpresaFacadeLocal() {
        return empresaFacadeLocal;
    }

    public void setEmpresaFacadeLocal(EmpresaFacadeLocal empresaFacadeLocal) {
        this.empresaFacadeLocal = empresaFacadeLocal;
    }

    public List<Empresa> getEmpresa() {
        return empresa;
    }

    public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }


    public Empresa getEmpresaSeleccionada() {
        return this.empresaSeleccionada;
    }


}
