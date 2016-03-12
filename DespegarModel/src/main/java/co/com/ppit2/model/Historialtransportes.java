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
@Table(name = "historialtransportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialtransportes.findAll", query = "SELECT h FROM Historialtransportes h"),
    @NamedQuery(name = "Historialtransportes.findByCodigo", query = "SELECT h FROM Historialtransportes h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "Historialtransportes.findByFecha", query = "SELECT h FROM Historialtransportes h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Historialtransportes.findByTipoTransporte", query = "SELECT h FROM Historialtransportes h WHERE h.tipoTransporte = :tipoTransporte"),
    @NamedQuery(name = "Historialtransportes.findByValorTotalTransporte", query = "SELECT h FROM Historialtransportes h WHERE h.valorTotalTransporte = :valorTotalTransporte")})
public class Historialtransportes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "tipo_transporte")
    private String tipoTransporte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total_transporte")
    private BigDecimal valorTotalTransporte;
    @JoinColumn(name = "history_transp", referencedColumnName = "identificacion")
    @ManyToOne(optional = false)
    private Personas historyTransp;
    @JoinColumn(name = "datos_tran_terr", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Datostranporteterrestre datosTranTerr;
    @JoinColumn(name = "datos_tran_aero", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Datostranporteaereo datosTranAero;

    public Historialtransportes() {
    }

    public Historialtransportes(String codigo) {
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

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public BigDecimal getValorTotalTransporte() {
        return valorTotalTransporte;
    }

    public void setValorTotalTransporte(BigDecimal valorTotalTransporte) {
        this.valorTotalTransporte = valorTotalTransporte;
    }

    public Personas getHistoryTransp() {
        return historyTransp;
    }

    public void setHistoryTransp(Personas historyTransp) {
        this.historyTransp = historyTransp;
    }

    public Datostranporteterrestre getDatosTranTerr() {
        return datosTranTerr;
    }

    public void setDatosTranTerr(Datostranporteterrestre datosTranTerr) {
        this.datosTranTerr = datosTranTerr;
    }

    public Datostranporteaereo getDatosTranAero() {
        return datosTranAero;
    }

    public void setDatosTranAero(Datostranporteaereo datosTranAero) {
        this.datosTranAero = datosTranAero;
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
        if (!(object instanceof Historialtransportes)) {
            return false;
        }
        Historialtransportes other = (Historialtransportes) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ppit2.model.Historialtransportes[ codigo=" + codigo + " ]";
    }
    
}
