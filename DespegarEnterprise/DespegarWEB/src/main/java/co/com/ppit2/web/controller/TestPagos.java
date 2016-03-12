/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.web.controller;

import co.edu.polijic.pagos.modelos.TipoPago;
import java.math.BigDecimal;

/**
 *
 * @author root
 */
public class TestPagos {

    TipoPago tipoPago;
    int tarjetaOrigen;
    int tarjetaDestino;
    BigDecimal valor;
    int nmcuotaspago;

    public TestPagos() {
        TipoPago tp = new TipoPago();
        tp.setCdtipopago(1);
        tp.setDsdescripcion("Credito");
        this.tipoPago = tp;
        this.tarjetaOrigen = 12345;
        this.tarjetaDestino = 54321;
        this.valor = new BigDecimal(1000.0);
        this.nmcuotaspago = 2;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getTarjetaOrigen() {
        return tarjetaOrigen;
    }

    public void setTarjetaOrigen(int tarjetaOrigen) {
        this.tarjetaOrigen = tarjetaOrigen;
    }

    public int getTarjetaDestino() {
        return tarjetaDestino;
    }

    public void setTarjetaDestino(int tarjetaDestino) {
        this.tarjetaDestino = tarjetaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getNmcuotaspago() {
        return nmcuotaspago;
    }

    public void setNmcuotaspago(int nmcuotaspago) {
        this.nmcuotaspago = nmcuotaspago;
    }

}
