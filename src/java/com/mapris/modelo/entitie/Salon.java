/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.entitie;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "salones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salon.findAll", query = "SELECT s FROM Salon s")
    , @NamedQuery(name = "Salon.findByIdSalones", query = "SELECT s FROM Salon s WHERE s.idSalones = :idSalones")
    , @NamedQuery(name = "Salon.findBySalon", query = "SELECT s FROM Salon s WHERE s.salon = :salon")
    , @NamedQuery(name = "Salon.findByEstado", query = "SELECT s FROM Salon s WHERE s.estado = :estado")})
public class Salon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_salones")
    private Integer idSalones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "salon")
    private String salon;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salon")
    private List<SalonHasServicio> salonesHasServicios;

    public Salon() {
    }

    public Salon(Integer idSalones) {
        this.idSalones = idSalones;
    }

    public Salon(Integer idSalones, String salon) {
        this.idSalones = idSalones;
        this.salon = salon;
    }

    public Integer getIdSalones() {
        return idSalones;
    }

    public void setIdSalones(Integer idSalones) {
        this.idSalones = idSalones;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<SalonHasServicio> getSalonesHasServicios() {
        return salonesHasServicios;
    }

    public void setSalonesHasServicios(List<SalonHasServicio> salonesHasServicios) {
        this.salonesHasServicios = salonesHasServicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalones != null ? idSalones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salon)) {
            return false;
        }
        Salon other = (Salon) object;
        if ((this.idSalones == null && other.idSalones != null) || (this.idSalones != null && !this.idSalones.equals(other.idSalones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.Salon[ idSalones=" + idSalones + " ]";
    }
    
}
