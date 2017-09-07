/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.tienda.controller;

import com.mapris.login.controller.SessionController;
import com.mapris.modelo.dao.ProductosFacadeLocal;
import com.mapris.modelo.entitie.Productos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



/**
 *
 * @author andres
 */
@Named(value = "listarProductosController")
@ViewScoped
public class ListarProductosController implements Serializable{
    
    @Inject
    private SessionController sessionController;
    
    @EJB
    private ProductosFacadeLocal productosFacade;

    private List<Productos> productos;

    private Productos productoSeleccionado;
    
    
    public ListarProductosController() {
        
    }
    
    @PostConstruct 
    public void init() {
        cargarArchivoIdioma();
        listarProductos();
         
    }

    public ProductosFacadeLocal getProductosFacade() {
        return productosFacade;
    }

    public void setProductosFacade(ProductosFacadeLocal productosFacade) {
        this.productosFacade = productosFacade;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public Productos getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Productos productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }
    
    

    private Properties cargarArchivoIdioma(){
        try{
        Properties properties = new Properties();
        if (sessionController.getSeleccionarLenguaje()== null){
            sessionController.setSeleccionarLenguaje(new Locale("es"));
            properties.load(ListarProductosController.class.getClassLoader().getResourceAsStream("com/mapris/languages/Tienda.properties"));
        }
        else{        
            
            if(sessionController.getSeleccionarLenguaje().toString().substring(0,2).equals("es")){
            sessionController.setSeleccionarLenguaje(new Locale("es"));
            properties.load(ListarProductosController.class.getClassLoader().getResourceAsStream("com/mapris/languages/Tienda.properties"));
            
            if(sessionController.getSeleccionarLenguaje().toString().substring(0, 2).equals("en")){
            sessionController.setSeleccionarLenguaje(new Locale("en"));
            properties.load(ListarProductosController.class.getClassLoader().getResourceAsStream("com/mapris/languages/Tienda_en.properties"));
            
                
            }
        }
            
        
        }
         return properties;   
        }catch( FileNotFoundException e ){
        
        } catch (IOException ex) {
            Logger.getLogger(ListarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private void listarProductos(){
        
    }
}
