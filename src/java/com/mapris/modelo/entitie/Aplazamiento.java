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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "aplazamientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aplazamiento.findAll", query = "SELECT a FROM Aplazamiento a")
    , @NamedQuery(name = "Aplazamiento.findByidUsuario", query = "SELECT a FROM Aplazamiento a WHERE a.idUsuario = :idUsuario")
    , @NamedQuery(name = "Aplazamiento.findByInicio", query = "SELECT a FROM Aplazamiento a WHERE a.inicio = :inicio")
    , @NamedQuery(name = "Aplazamiento.findByFin", query = "SELECT a FROM Aplazamiento a WHERE a.fin = :fin")})
public class Aplazamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_id_usuario")
    private Integer idUsuario;
    @Lob
    @Size(max = 65535)
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "fk_id_usuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Cliente cliente;

    public Aplazamiento() {
    }

    public Aplazamiento(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getidUsuario() {
        return idUsuario;
    }

    public void setidUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof Aplazamiento)) {
            return false;
        }
        Aplazamiento other = (Aplazamiento) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.Aplazamiento[ idUsuario=" + idUsuario + " ]";
    }
    
}
