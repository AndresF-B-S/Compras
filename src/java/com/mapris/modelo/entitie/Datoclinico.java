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
@Table(name = "datosclinicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datoclinico.findAll", query = "SELECT d FROM Datoclinico d")
    , @NamedQuery(name = "Datoclinico.findByIdDatosClinicos", query = "SELECT d FROM Datoclinico d WHERE d.idDatosClinicos = :idDatosClinicos")
    , @NamedQuery(name = "Datoclinico.findByFkUsuario", query = "SELECT d FROM Datoclinico d WHERE d.idUsuarios = :idUsuarios")
    , @NamedQuery(name = "Datoclinico.findByTipoSangre", query = "SELECT d FROM Datoclinico d WHERE d.tipoSangre = :tipoSangre")
    , @NamedQuery(name = "Datoclinico.findByAlergias", query = "SELECT d FROM Datoclinico d WHERE d.alergias = :alergias")
    , @NamedQuery(name = "Datoclinico.findByUrl", query = "SELECT d FROM Datoclinico d WHERE d.url = :url")})
public class Datoclinico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_clinicos")
    private Integer idDatosClinicos;
    
    @Column(name = "tipo_sangre")
    private String tipoSangre;
   
    @Column(name = "alergias")
    private String alergias;
    @Column(name = "url")
    private String url;
    @Column(name = "patologia")
    private String patologia;
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "fk_id_usuario")
    @ManyToOne(optional = false)
    private Cliente idUsuarios;

    public Datoclinico() {
    }

    public Datoclinico(Integer idDatosClinicos) {
        this.idDatosClinicos = idDatosClinicos;
    }

    public Integer getIdDatosClinicos() {
        return idDatosClinicos;
    }

    public void setIdDatosClinicos(Integer idDatosClinicos) {
        this.idDatosClinicos = idDatosClinicos;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }
    

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Cliente getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Cliente idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosClinicos != null ? idDatosClinicos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datoclinico)) {
            return false;
        }
        Datoclinico other = (Datoclinico) object;
        if ((this.idDatosClinicos == null && other.idDatosClinicos != null) || (this.idDatosClinicos != null && !this.idDatosClinicos.equals(other.idDatosClinicos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mapris.modelo.entitie.Datoclinico[ idDatosClinicos=" + idDatosClinicos + " ]";
    }
    
}
