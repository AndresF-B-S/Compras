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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdUsuario", query = "SELECT c FROM Cliente c WHERE c.idUsuario = :idUsuario")
    , @NamedQuery(name = "Cliente.findByEstado", query = "SELECT c FROM Cliente c WHERE c.estado = :estado")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Inscripcion> inscripciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarios")
    private List<Datoclinico> datosClinicos;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Aplazamiento aplazamiento;
    @JoinColumn(name = "fk_id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresas;
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cliente(Integer idUsuario, String estado) {
        this.idUsuario = idUsuario;
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @XmlTransient
    public List<Datoclinico> getDatosClinicos() {
        return datosClinicos;
    }

    public void setDatosClinicos(List<Datoclinico> datosClinicos) {
        this.datosClinicos = datosClinicos;
    }

    public Aplazamiento getAplazamiento() {
        return aplazamiento;
    }

    public void setAplazamiento(Aplazamiento aplazamiento) {
        this.aplazamiento = aplazamiento;
    }

    public Empresa getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(Empresa idEmpresas) {
        this.idEmpresas = idEmpresas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.Cliente[ idUsuario=" + idUsuario + " ]";
    }
    
}
