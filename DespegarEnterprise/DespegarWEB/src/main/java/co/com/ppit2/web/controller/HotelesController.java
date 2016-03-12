/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.web.controller;

import co.com.ppit2.model.Habitacion;
import co.com.ppit2.model.Hotel;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jhovannyandres
 */

@Controller
public class HotelesController {
   private ArrayList<Hotel> hoteles;
   private ArrayList<Habitacion> habitaciones;
   private Hotel hotelSeleccionado;
   
    @RequestMapping(value = "/hoteles.do", method = RequestMethod.GET)
    public String hoteles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
            return "hoteles/hoteles";
        }
        return "hoteles/hoteles";
    }
    
    @Init
    public void init(){
        cargarHabitaciones();
        cargarHoteles();
        
    }

    public void cargarHabitaciones(){
        habitaciones = new ArrayList<Habitacion>();
        Date fechaFin = new Date(5, 8, 2015);
        Date fechaInicio = new Date(25, 7, 2015);
        for(int i=0;i<=10;i++ ){
            Habitacion habitacion = new Habitacion();
            habitacion.setEstadoHabitacion("No disponible");
            habitacion.setFechaFinHospedaje(fechaFin);
            habitacion.setFechaInicioHospedaje(fechaInicio);
            habitacion.setNumeroHabitacion("00"+i);
            habitaciones.add(habitacion);
        }   
        
        for(int i=10;i<=20;i++ ){
            Habitacion habitacion = new Habitacion();
            habitacion.setEstadoHabitacion("Disponible");
            habitacion.setFechaFinHospedaje(null);
            habitacion.setFechaInicioHospedaje(null);
            habitacion.setNumeroHabitacion("00"+i);
            habitaciones.add(habitacion);
        } 
    }
    
    
    public void cargarHoteles(){
        hoteles = new ArrayList<Hotel>();
        
        Hotel hotel = new Hotel();
        hotel.setHabitaciones(habitaciones);
        hotel.setNombreHotel("Cartagena plaza");
        hotel.setRutaIma("/resources/images/cartagenaPlaza.png");
        hotel.setUbicacionHotel("Cartagena");
        hotel.setPrecio("$ 200.000");
        hoteles.add(hotel);
        
        Hotel hotel2 = new Hotel();
        hotel2.setHabitaciones(habitaciones);
        hotel2.setNombreHotel("San Andres plaza");
        hotel2.setRutaIma("/resources/images/San Andres.png");
        hotel2.setUbicacionHotel("San Andres");
        hotel2.setPrecio("$ 500.000");
        hoteles.add(hotel2);
        
        Hotel hotel3 = new Hotel();
        hotel3.setHabitaciones(habitaciones);
        hotel3.setNombreHotel("Confort Cartagena");
        hotel3.setRutaIma("/resources/images/hotel3.jpg");
        hotel3.setUbicacionHotel("Cartagena");
        hotel3.setPrecio("$1 500.000");
        hoteles.add(hotel3);
    }

    public ArrayList<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(ArrayList<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @NotifyChange("*")
    public Hotel getHotelSeleccionado() {
        return hotelSeleccionado;
    }

    public void setHotelSeleccionado(Hotel hotelSeleccionado) {
        this.hotelSeleccionado = hotelSeleccionado;
    }    
}
