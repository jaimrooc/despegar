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
@Table(name = "datostranporteterrestre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datostranporteterrestre.findAll", query = "SELECT d FROM Datostranporteterrestre d"),
    @NamedQuery(name = "Datostranporteterrestre.findByCodigo", query = "SELECT d FROM Datostranporteterrestre d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "Datostranporteterrestre.findByCodigoViaje", query = "SELECT d FROM Datostranporteterrestre d WHERE d.codigoViaje = :codigoViaje"),
    @NamedQuery(name = "Datostranporteterrestre.findByDestino", query = "SELECT d FROM Datostranporteterrestre d WHERE d.destino = :destino"),
    @NamedQuery(name = "Datostranporteterrestre.findByEmpresaTransporte", query = "SELECT d FROM Datostranporteterrestre d WHERE d.empresaTransporte = :empresaTransporte"),
    @NamedQuery(name = "Datostranporteterrestre.findByFechaViaje", query = "SELECT d FROM Datostranporteterrestre d WHERE d.fechaViaje = :fechaViaje"),
    @NamedQuery(name = "Datostranporteterrestre.findByOrigen", query = "SELECT d FROM Datostranporteterrestre d WHERE d.origen = :origen"),
    @NamedQuery(name = "Datostranporteterrestre.findByTelefonoConcato", query = "SELECT d FROM Datostranporteterrestre d WHERE d.telefonoConcato = :telefonoConcato")})
public class Datostranporteterrestre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "codigo_viaje")
    private String codigoViaje;
    @Column(name = "destino")
    private String destino;
    @Column(name = "empresa_transporte")
    private String empresaTransporte;
    @Column(name = "fecha_viaje")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaViaje;
    @Column(name = "origen")
    private String origen;
    @Column(name = "telefono_concato")
    private String telefonoConcato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datosTranTerr")
    private Collection<Historialtransportes> historialtransportesCollection;

    public Datostranporteterrestre() {
    }

    public Datostranporteterrestre(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoViaje() {
        return codigoViaje;
    }

    public void setCodigoViaje(String codigoViaje) {
        this.codigoViaje = codigoViaje;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEmpresaTransporte() {
        return empresaTransporte;
    }

    public void setEmpresaTransporte(String empresaTransporte) {
        this.empresaTransporte = empresaTransporte;
    }

    public Date getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getTelefonoConcato() {
        return telefonoConcato;
    }

    public void setTelefonoConcato(String telefonoConcato) {
        this.telefonoConcato = telefonoConcato;
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
        if (!(object instanceof Datostranporteterrestre)) {
            return false;
        }
        Datostranporteterrestre other = (Datostranporteterrestre) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ppit2.model.Datostranporteterrestre[ codigo=" + codigo + " ]";
    }
    
}
