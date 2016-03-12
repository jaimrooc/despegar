/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaro
 */
@Entity
@Table(name = "datostranporteaereo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datostranporteaereo.findAll", query = "SELECT d FROM Datostranporteaereo d"),
    @NamedQuery(name = "Datostranporteaereo.findByCodigo", query = "SELECT d FROM Datostranporteaereo d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "Datostranporteaereo.findByCodigoVuelo", query = "SELECT d FROM Datostranporteaereo d WHERE d.codigoVuelo = :codigoVuelo"),
    @NamedQuery(name = "Datostranporteaereo.findByDestino", query = "SELECT d FROM Datostranporteaereo d WHERE d.destino = :destino"),
    @NamedQuery(name = "Datostranporteaereo.findByFechaVuelo", query = "SELECT d FROM Datostranporteaereo d WHERE d.fechaVuelo = :fechaVuelo"),
    @NamedQuery(name = "Datostranporteaereo.findByNombreAerolinea", query = "SELECT d FROM Datostranporteaereo d WHERE d.nombreAerolinea = :nombreAerolinea"),
    @NamedQuery(name = "Datostranporteaereo.findByNumeroEscalas", query = "SELECT d FROM Datostranporteaereo d WHERE d.numeroEscalas = :numeroEscalas"),
    @NamedQuery(name = "Datostranporteaereo.findByOrigen", query = "SELECT d FROM Datostranporteaereo d WHERE d.origen = :origen"),
    @NamedQuery(name = "Datostranporteaereo.findByTelefonoContacto", query = "SELECT d FROM Datostranporteaereo d WHERE d.telefonoContacto = :telefonoContacto")})
public class Datostranporteaereo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "codigo_vuelo")
    private String codigoVuelo;
    @Column(name = "destino")
    private String destino;
    @Column(name = "fecha_vuelo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVuelo;
    @Column(name = "nombre_aerolinea")
    private String nombreAerolinea;
    @Column(name = "numero_escalas")
    private Integer numeroEscalas;
    @Column(name = "origen")
    private String origen;
    @Column(name = "telefono_contacto")
    private String telefonoContacto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datosTranAero")
    private Collection<Historialtransportes> historialtransportesCollection;

    public Datostranporteaereo() {
    }

    public Datostranporteaereo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    public Integer getNumeroEscalas() {
        return numeroEscalas;
    }

    public void setNumeroEscalas(Integer numeroEscalas) {
        this.numeroEscalas = numeroEscalas;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    @XmlTransient
    public Collection<Historialtransportes> getHistorialtransportesCollection() {
        return historialtransportesCollection;
    }

    public void setHistorialtransportesCollection(Collection<Historialtransportes> historialtransportesCollection) {
        this.historialtransportesCollection = historialtransportesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datostranporteaereo)) {
            return false;
        }
        Datostranporteaereo other = (Datostranporteaereo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ppit2.model.Datostranporteaereo[ codigo=" + codigo + " ]";
    }
    
}
