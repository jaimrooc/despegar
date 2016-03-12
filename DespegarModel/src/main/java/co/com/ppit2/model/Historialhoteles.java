/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaro
 */
@Entity
@Table(name = "historialhoteles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialhoteles.findAll", query = "SELECT h FROM Historialhoteles h"),
    @NamedQuery(name = "Historialhoteles.findByCodigo", query = "SELECT h FROM Historialhoteles h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "Historialhoteles.findByFecha", query = "SELECT h FROM Historialhoteles h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Historialhoteles.findByIdHotel", query = "SELECT h FROM Historialhoteles h WHERE h.idHotel = :idHotel"),
    @NamedQuery(name = "Historialhoteles.findByNombreHotel", query = "SELECT h FROM Historialhoteles h WHERE h.nombreHotel = :nombreHotel"),
    @NamedQuery(name = "Historialhoteles.findByTelefono", query = "SELECT h FROM Historialhoteles h WHERE h.telefono = :telefono"),
    @NamedQuery(name = "Historialhoteles.findByValorTotalReserva", query = "SELECT h FROM Historialhoteles h WHERE h.valorTotalReserva = :valorTotalReserva")})
public class Historialhoteles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "id_hotel")
    private String idHotel;
    @Column(name = "nombre_hotel")
    private String nombreHotel;
    @Column(name = "telefono")
    private String telefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total_reserva")
    private BigDecimal valorTotalReserva;
    @JoinColumn(name = "history_hotel", referencedColumnName = "identificacion")
    @ManyToOne(optional = false)
    private Personas historyHotel;

    public Historialhoteles() {
    }

    public Historialhoteles(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getValorTotalReserva() {
        return valorTotalReserva;
    }

    public void setValorTotalReserva(BigDecimal valorTotalReserva) {
        this.valorTotalReserva = valorTotalReserva;
    }

    public Personas getHistoryHotel() {
        return historyHotel;
    }

    public void setHistoryHotel(Personas historyHotel) {
        this.historyHotel = historyHotel;
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
        if (!(object instanceof Historialhoteles)) {
            return false;
        }
        Historialhoteles other = (Historialhoteles) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ppit2.model.Historialhoteles[ codigo=" + codigo + " ]";
    }
    
}
