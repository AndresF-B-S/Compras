/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.modelo.entitie;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "detalle_horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleHorario.findAll", query = "SELECT d FROM DetalleHorario d")
    , @NamedQuery(name = "DetalleHorario.findByIddetalleHorario", query = "SELECT d FROM DetalleHorario d WHERE d.iddetalleHorario = :iddetalleHorario")
    , @NamedQuery(name = "DetalleHorario.findByHoraInicio", query = "SELECT d FROM DetalleHorario d WHERE d.horaInicio = :horaInicio")
    , @NamedQuery(name = "DetalleHorario.findByDuracion", query = "SELECT d FROM DetalleHorario d WHERE d.duracion = :duracion")
    , @NamedQuery(name = "DetalleHorario.findByDiaSemana", query = "SELECT d FROM DetalleHorario d WHERE d.diaSemana = :diaSemana")})
public class DetalleHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddetalle_horario")
    private Integer iddetalleHorario;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "dia_semana")
    private Integer diaSemana;
    @JoinColumn(name = "horarios_id_horario", referencedColumnName = "id_horario")
    @ManyToOne(optional = false)
    private Horario idHorarios;

    public DetalleHorario() {
    }

    public DetalleHorario(Integer iddetalleHorario) {
        this.iddetalleHorario = iddetalleHorario;
    }

    public Integer getIddetalleHorario() {
        return iddetalleHorario;
    }

    public void setIddetalleHorario(Integer iddetalleHorario) {
        this.iddetalleHorario = iddetalleHorario;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Horario getIdHorarios() {
        return idHorarios;
    }

    public void setIdHorarios(Horario idHorarios) {
        this.idHorarios = idHorarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleHorario != null ? iddetalleHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleHorario)) {
            return false;
        }
        DetalleHorario other = (DetalleHorario) object;
        if ((this.iddetalleHorario == null && other.iddetalleHorario != null) || (this.iddetalleHorario != null && !this.iddetalleHorario.equals(other.iddetalleHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.DetalleHorario[ iddetalleHorario=" + iddetalleHorario + " ]";
    }
    
}
