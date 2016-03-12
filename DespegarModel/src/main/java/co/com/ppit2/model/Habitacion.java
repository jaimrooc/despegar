/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.model;

import java.util.Date;

/**
 *
 * @author jhovannyandres
 */
public class Habitacion {
    String numeroHabitacion;
    String estadoHabitacion;
    Date fechaInicioHospedaje;
    Date fechaFinHospedaje;

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(String estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public Date getFechaInicioHospedaje() {
        return fechaInicioHospedaje;
    }

    public void setFechaInicioHospedaje(Date fechaInicioHospedaje) {
        this.fechaInicioHospedaje = fechaInicioHospedaje;
    }

    public Date getFechaFinHospedaje() {
        return fechaFinHospedaje;
    }

    public void setFechaFinHospedaje(Date fechaFinHospedaje) {
        this.fechaFinHospedaje = fechaFinHospedaje;
    }
    
    
}
