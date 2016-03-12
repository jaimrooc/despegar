/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.model;

import java.util.ArrayList;

/**
 *
 * @author jhovannyandres
 */
public class Hotel {
    
    String nombreHotel;
    String ubicacionHotel;
    ArrayList<Habitacion> habitaciones;
    String rutaIma;
    String precio;

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getUbicacionHotel() {
        return ubicacionHotel;
    }

    public void setUbicacionHotel(String ubicacionHotel) {
        this.ubicacionHotel = ubicacionHotel;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getRutaIma() {
        return rutaIma;
    }

    public void setRutaIma(String rutaIma) {
        this.rutaIma = rutaIma;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    
}
