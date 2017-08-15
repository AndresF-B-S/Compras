package com.mapris.util;

import java.util.Date;

/**
 *
 * @author andres
 */
public class Fecha {
 
   private Date fecha;
   
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   
    public Date getFechaActual() {
        Date ahora= new Date();
        return ahora;
    }
    
}
