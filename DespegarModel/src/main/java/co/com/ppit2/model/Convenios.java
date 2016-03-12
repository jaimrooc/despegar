/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaro
 */
@Entity
@Table(name = "convenios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convenios.findAll", query = "SELECT c FROM Convenios c"),
    @NamedQuery(name = "Convenios.findById", query = "SELECT c FROM Convenios c WHERE c.id = :id"),
    @NamedQuery(name = "Convenios.findByPorcentaje", query = "SELECT c FROM Convenios c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "Convenios.findByTipoEntidad", query = "SELECT c FROM Convenios c WHERE c.tipoEntidad = :tipoEntidad")})
public class Convenios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_convenios", sequenceName = "seq_convenios", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_convenios")
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje")
    private Double porcentaje;
    @Column(name = "tipo_entidad")
    private String tipoEntidad;
    @Transient
    private boolean editarRegistro;
    @Column(name = "descripcion")
    private String descripcion;

    public Convenios() {
    }

    public Convenios(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Convenios)) {
            return false;
        }
        Convenios other = (Convenios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean isEditarRegistro() {
        return editarRegistro;
    }

    public void setEditarRegistro(boolean editarRegistro) {
        this.editarRegistro = editarRegistro;
    }

    @Override
    public String toString() {
        return "co.com.ppit2.model.Convenios[ id=" + id + " ]";
    }

}
