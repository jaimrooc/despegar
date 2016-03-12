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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaro
 */
@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findByIdentificacion", query = "SELECT p FROM Personas p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Personas.findByPrimerApellido", query = "SELECT p FROM Personas p WHERE p.primerApellido = :primerApellido"),
    @NamedQuery(name = "Personas.findBySegundoApellido", query = "SELECT p FROM Personas p WHERE p.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "Personas.findByCelular", query = "SELECT p FROM Personas p WHERE p.celular = :celular"),
    @NamedQuery(name = "Personas.findByEmail", query = "SELECT p FROM Personas p WHERE p.email = :email"),
    @NamedQuery(name = "Personas.findByEstado", query = "SELECT p FROM Personas p WHERE p.estado = :estado"),
    @NamedQuery(name = "Personas.findByFechaNacimiento", query = "SELECT p FROM Personas p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Personas.findByNombre", query = "SELECT p FROM Personas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personas.findByPassword", query = "SELECT p FROM Personas p WHERE p.password = :password"),
    @NamedQuery(name = "Personas.findByTelefono", query = "SELECT p FROM Personas p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Personas.findByTipoDocumento", query = "SELECT p FROM Personas p WHERE p.tipoDocumento = :tipoDocumento")})
public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "celular")
    private String celular;
    @Column(name = "email")
    private String email;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "password")
    private String password;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historyTransp")
    private Collection<Historialtransportes> historialtransportesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historyPago")
    private Collection<Historialpagos> historialpagosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historyHotel")
    private Collection<Historialhoteles> historialhotelesCollection;
    @Transient
    private String repassword;
    @Column(name = "admin")
    private boolean admin;

    public Personas() {
    }

    public Personas(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Collection<Historialtransportes> getHistorialtransportesCollection() {
        return historialtransportesCollection;
    }

    public void setHistorialtransportesCollection(Collection<Historialtransportes> historialtransportesCollection) {
        this.historialtransportesCollection = historialtransportesCollection;
    }

    @XmlTransient
    public Collection<Historialpagos> getHistorialpagosCollection() {
        return historialpagosCollection;
    }

    public void setHistorialpagosCollection(Collection<Historialpagos> historialpagosCollection) {
        this.historialpagosCollection = historialpagosCollection;
    }

    @XmlTransient
    public Collection<Historialhoteles> getHistorialhotelesCollection() {
        return historialhotelesCollection;
    }

    public void setHistorialhotelesCollection(Collection<Historialhoteles> historialhotelesCollection) {
        this.historialhotelesCollection = historialhotelesCollection;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getFullName() {
        return nombre + " " + primerApellido + " " + segundoApellido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificacion != null ? identificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.identificacion == null && other.identificacion != null) || (this.identificacion != null && !this.identificacion.equals(other.identificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ppit2.model.Personas[ identificacion=" + identificacion + " ]";
    }

}
