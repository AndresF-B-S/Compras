/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.entitie;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "inscripciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i")
    , @NamedQuery(name = "Inscripcion.findByIdInscripcion", query = "SELECT i FROM Inscripcion i WHERE i.idInscripcion = :idInscripcion")
    , @NamedQuery(name = "Inscripcion.findByEstado", query = "SELECT i FROM Inscripcion i WHERE i.estado = :estado")})
public class Inscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inscripcion")
    private Integer idInscripcion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "fk_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso idCursos;
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "fk_id_usuario")
    @ManyToOne(optional = false)
    private Cliente idUsuario;

    public Inscripcion() {
    }

    public Inscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Integer getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Curso getFkIdCurso() {
        return idCursos;
    }

    public void setFkIdCurso(Curso idCursos) {
        this.idCursos = idCursos;
    }

    public Cliente getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Cliente idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInscripcion != null ? idInscripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.idInscripcion == null && other.idInscripcion != null) || (this.idInscripcion != null && !this.idInscripcion.equals(other.idInscripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.Inscripcion[ idInscripcion=" + idInscripcion + " ]";
    }
    
}
