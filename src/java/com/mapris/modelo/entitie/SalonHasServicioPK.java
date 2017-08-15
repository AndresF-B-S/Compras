/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.entitie;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ruben
 */
@Embeddable
public class SalonHasServicioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "salones_id_salones")
    private int salonesIdSalones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "servicios_id_servicio")
    private int serviciosIdServicio;

    public SalonHasServicioPK() {
    }

    public SalonHasServicioPK(int salonesIdSalones, int serviciosIdServicio) {
        this.salonesIdSalones = salonesIdSalones;
        this.serviciosIdServicio = serviciosIdServicio;
    }

    public int getSalonesIdSalones() {
        return salonesIdSalones;
    }

    public void setSalonesIdSalones(int salonesIdSalones) {
        this.salonesIdSalones = salonesIdSalones;
    }

    public int getServiciosIdServicio() {
        return serviciosIdServicio;
    }

    public void setServiciosIdServicio(int serviciosIdServicio) {
        this.serviciosIdServicio = serviciosIdServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) salonesIdSalones;
        hash += (int) serviciosIdServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalonHasServicioPK)) {
            return false;
        }
        SalonHasServicioPK other = (SalonHasServicioPK) object;
        if (this.salonesIdSalones != other.salonesIdSalones) {
            return false;
        }
        if (this.serviciosIdServicio != other.serviciosIdServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.SalonHasServicioPK[ salonesIdSalones=" + salonesIdSalones + ", serviciosIdServicio=" + serviciosIdServicio + " ]";
    }
    
}
