/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.model;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaro
 */
@Entity
@Table(name = "historialpagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialpagos.findAll", query = "SELECT h FROM Historialpagos h"),
    @NamedQuery(name = "Historialpagos.findByCodigo", query = "SELECT h FROM Historialpagos h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "Historialpagos.findByFecha", query = "SELECT h FROM Historialpagos h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Historialpagos.findByIdTransaccionPago", query = "SELECT h FROM Historialpagos h WHERE h.idTransaccionPago = :idTransaccionPago"),
    @NamedQuery(name = "Historialpagos.findByPagoHotel", query = "SELECT h FROM Historialpagos h WHERE h.pagoHotel = :pagoHotel"),
    @NamedQuery(name = "Historialpagos.findByPagoTransporte", query = "SELECT h FROM Historialpagos h WHERE h.pagoTransporte = :pagoTransporte")})
public class Historialpagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "id_transaccion_pago")
    private String idTransaccionPago;
    @Column(name = "pago_hotel")
    private String pagoHotel;
    @Column(name = "pago_transporte")
    private String pagoTransporte;
    @JoinColumn(name = "history_pago", referencedColumnName = "identificacion")
    @ManyToOne(optional = false)
    private Personas historyPago;

    public Historialpagos() {
    }

    public Historialpagos(String codigo) {
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

    public String getIdTransaccionPago() {
        return idTransaccionPago;
    }

    public void setIdTransaccionPago(String idTransaccionPago) {
        this.idTransaccionPago = idTransaccionPago;
    }

    public String getPagoHotel() {
        return pagoHotel;
    }

    public void setPagoHotel(String pagoHotel) {
        this.pagoHotel = pagoHotel;
    }

    public String getPagoTransporte() {
        return pagoTransporte;
    }

    public void setPagoTransporte(String pagoTransporte) {
        this.pagoTransporte = pagoTransporte;
    }

    public Personas getHistoryPago() {
        return historyPago;
    }

    public void setHistoryPago(Personas historyPago) {
        this.historyPago = historyPago;
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
        if (!(object instanceof Historialpagos)) {
            return false;
        }
        Historialpagos other = (Historialpagos) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ppit2.model.Historialpagos[ codigo=" + codigo + " ]";
    }
    
}
