/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.entitie;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "salones_has_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalonHasServicio.findAll", query = "SELECT s FROM SalonHasServicio s")
    , @NamedQuery(name = "SalonHasServicio.findBySalonesIdSalones", query = "SELECT s FROM SalonHasServicio s WHERE s.salonHasServicioPK.salonesIdSalones = :salonesIdSalones")
    , @NamedQuery(name = "SalonHasServicio.findByServiciosIdServicio", query = "SELECT s FROM SalonHasServicio s WHERE s.salonHasServicioPK.serviciosIdServicio = :serviciosIdServicio")
    , @NamedQuery(name = "SalonHasServicio.findByEstado", query = "SELECT s FROM SalonHasServicio s WHERE s.estado = :estado")})
public class SalonHasServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SalonHasServicioPK salonHasServicioPK;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "salones_id_salones", referencedColumnName = "id_salones", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Salon salon;
    @JoinColumn(name = "servicios_id_servicio", referencedColumnName = "id_servicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Servicio servicio;

    public SalonHasServicio() {
    }

    public SalonHasServicio(SalonHasServicioPK salonHasServicioPK) {
        this.salonHasServicioPK = salonHasServicioPK;
    }

    public SalonHasServicio(int salonesIdSalones, int serviciosIdServicio) {
        this.salonHasServicioPK = new SalonHasServicioPK(salonesIdSalones, serviciosIdServicio);
    }

    public SalonHasServicioPK getSalonHasServicioPK() {
        return salonHasServicioPK;
    }

    public void setSalonHasServicioPK(SalonHasServicioPK salonHasServicioPK) {
        this.salonHasServicioPK = salonHasServicioPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salonHasServicioPK != null ? salonHasServicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalonHasServicio)) {
            return false;
        }
        SalonHasServicio other = (SalonHasServicio) object;
        if ((this.salonHasServicioPK == null && other.salonHasServicioPK != null) || (this.salonHasServicioPK != null && !this.salonHasServicioPK.equals(other.salonHasServicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.SalonHasServicio[ salonHasServicioPK=" + salonHasServicioPK + " ]";
    }
    
}
