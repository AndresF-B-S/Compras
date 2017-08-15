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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")
    , @NamedQuery(name = "Servicio.findByIdServicio", query = "SELECT s FROM Servicio s WHERE s.idServicio = :idServicio")
    , @NamedQuery(name = "Servicio.findByNombre", query = "SELECT s FROM Servicio s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Servicio.findByValor", query = "SELECT s FROM Servicio s WHERE s.valor = :valor")
    , @NamedQuery(name = "Servicio.findBySesiones", query = "SELECT s FROM Servicio s WHERE s.sesiones = :sesiones")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicio")
    private Integer idServicio;
    @Size(max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Lob
    @Size(max = 65535)
    @Column(name = "objetivo")
    private String objetivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sesiones")
    private int sesiones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicios")
    private List<Curso> cursos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio")
    private List<SalonHasServicio> salonesHasServicios;
    @OneToMany(mappedBy = "subServicios")
    private List<Servicio> servicios;
    @JoinColumn(name = "fk_sub_servicios", referencedColumnName = "id_servicio")
    @ManyToOne
    private Servicio subServicios;
    @JoinColumn(name = "fk_tipo_servicio", referencedColumnName = "id_tipo_servicio")
    @ManyToOne(optional = false)
    private TipoServicio tiposServicios;

    public Servicio() {
    }

    public Servicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(Integer idServicio, int sesiones) {
        this.idServicio = idServicio;
        this.sesiones = sesiones;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public int getSesiones() {
        return sesiones;
    }

    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }

    @XmlTransient
    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @XmlTransient
    public List<SalonHasServicio> getSalonesHasServicios() {
        return salonesHasServicios;
    }

    public void setSalonesHasServicios(List<SalonHasServicio> salonesHasServicios) {
        this.salonesHasServicios = salonesHasServicios;
    }

    @XmlTransient
    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Servicio getSubServicios() {
        return subServicios;
    }

    public void setSubServicios(Servicio subServicios) {
        this.subServicios = subServicios;
    }

    public TipoServicio getTiposServicios() {
        return tiposServicios;
    }

    public void setTiposServicios(TipoServicio tiposServicios) {
        this.tiposServicios = tiposServicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.Servicio[ idServicio=" + idServicio + " ]";
    }
    
}
