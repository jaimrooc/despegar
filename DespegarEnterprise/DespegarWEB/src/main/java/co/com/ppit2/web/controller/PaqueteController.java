package co.com.ppit2.web.controller;

import co.com.ppit2.model.dto.DtoAccionesGUI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.ppit2.web.controller.utils.WrapUtil;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

/**
 * @author Jhovanny Andres Suarez Arenas
 * @version 1.0
 *
 * Controlador para la gestion el login
 *
 * Historial:
 *
 * Fecha: 2015-08-07
 *
 * Inic: JASA
 *
 * Descripcion: Codigo Inicial
 */
@Controller
public class PaqueteController {

    // Objeto que manipula las interacciones de la GUI
    private DtoAccionesGUI dtoAccionesGUI;

    private ArrayList<String> destinosVuelos;

    private boolean mostrarVueloMasHotel;
    private boolean mostrarVueloMasHotelMasCarro;
    private boolean mostrarVueloMasCarro;
    private boolean mostrarHotelMasCarro;
    private String origen;
    private String destino;
    private String donde;
    private String horaInicioAlquiler;
    private String horaFinAlquiler;
    private Date fechaPartida;
    private Date fechaRegreso;
    private Date fechaAlquiler;
    private Date fechaFinAlquiler;
    private int habitaciones;
    private int adultos;
    private int menores;

    // Mesaje general
    private WrapUtil<String> msg;

    @RequestMapping(value = "/paquetes.do", method = RequestMethod.GET)
    public String ingresoPersonas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
            return "paquetes/paquetes";
        }
        return "paquetes/paquetes";
    }

    @Init
    public void init() {
        donde = origen = "";
        msg = new WrapUtil();
        destinosVuelos = new ArrayList<String>();
        dtoAccionesGUI = new DtoAccionesGUI();
        mostrarVueloMasHotel = true;
        mostrarVueloMasHotelMasCarro = mostrarVueloMasCarro
                = mostrarHotelMasCarro = false;
        habitaciones = 1;
        adultos = 2;
        menores = 0;
        cargarVuelos();

    }

    @Command()
    @NotifyChange("*")
    public void vueloMasHotel() {
        mostrarVueloMasHotel = true;
        mostrarVueloMasHotelMasCarro = false;
        mostrarVueloMasCarro = false;
        mostrarHotelMasCarro = false;
        msg = new WrapUtil();
    }

    @Command()
    @NotifyChange("*")
    public void vueloMasHotelMasCarro() {
        mostrarVueloMasHotelMasCarro = true;
        mostrarVueloMasCarro = false;
        mostrarHotelMasCarro = false;
        mostrarVueloMasHotel = false;
        msg = new WrapUtil();
    }

    @Command()
    @NotifyChange("*")
    public void mostrarVueloMasCarro() {
        mostrarVueloMasCarro = true;
        mostrarHotelMasCarro = false;
        mostrarVueloMasHotel = false;
        mostrarVueloMasHotelMasCarro = false;
        msg = new WrapUtil();
    }

    @Command()
    @NotifyChange("*")
    public void mostrarHotelMasCarro() {
        mostrarHotelMasCarro = true;
        mostrarVueloMasHotel = false;
        mostrarVueloMasHotelMasCarro = false;
        mostrarVueloMasCarro = false;
        msg = new WrapUtil();
    }

    @Command()
    @NotifyChange("*")
    public void buscarPaquetes() {
        if (!validarDatos()) {

        }
    }

    public boolean validarDatos() {
        boolean datosFaltantes = false;
        if (!origen.equals("")) {
            if (origen.equals(destino)) {
                msg.verMensaje("El destino debe ser diferente del origen",
                        WrapUtil.ERROR);
                destino = "";
                return true;
            }
        }
        if (mostrarHotelMasCarro) {
            if (fechaAlquiler == null || fechaFinAlquiler == null) {
                datosFaltantes = true;
            } else {
                if (horaInicioAlquiler == null || horaFinAlquiler == null) {
                    datosFaltantes = true;
                } else {
                    if (fechaFinAlquiler.compareTo(fechaAlquiler) == 0) {
                        if (horaInicioAlquiler.equals(horaFinAlquiler)) {
                            msg.verMensaje("La finalización debe ser posterior al inicio por lo menos 1 hora",
                                    WrapUtil.ERROR);
                            return true;
                        }
                    }
                }

            }
        }

        if (fechaPartida == null || fechaRegreso == null
                || donde == null || destino == null || origen == null) {
            datosFaltantes = true;
        }
        if (datosFaltantes) {
            msg.verMensaje("Todos los datos son obligatorios",
                    WrapUtil.ERROR);
        } else {
            msg = new WrapUtil<String>();
        }
        return datosFaltantes;
    }

    @Command()
    @NotifyChange("*")
    public void validarFechaPartida() {
        Date currentDate = new Date();
        if (fechaPartida != null && fechaPartida.compareTo(currentDate) < 0) {
            fechaPartida = null;
        }
    }

    @Command()
    @NotifyChange("*")
    public void validarAlquiler() {
        Date currentDate = new Date();
        if (fechaAlquiler != null && fechaAlquiler.compareTo(currentDate) < 0) {
            fechaAlquiler = null;
        }
    }

    @Command()
    @NotifyChange("*")
    public void validarFechaDevolucion() {
        Date currentDate = new Date();
        if (fechaFinAlquiler != null && fechaAlquiler.compareTo(currentDate) <= 0) {
            fechaFinAlquiler = null;
        }
        if (fechaAlquiler != null && fechaFinAlquiler != null
                && fechaFinAlquiler.compareTo(fechaAlquiler) <= 0) {
            fechaAlquiler = null;
        }
        if (fechaAlquiler == null) {
            fechaFinAlquiler = null;
        }
    }

    @Command()
    @NotifyChange("*")
    public void validarFechaRegreso() {
        Date currentDate = new Date();
        if (fechaRegreso != null && fechaRegreso.compareTo(currentDate) <= 0) {
            fechaRegreso = null;
        }
        if (fechaPartida != null && fechaRegreso != null
                && fechaRegreso.compareTo(fechaPartida) <= 0) {
            fechaRegreso = null;
        }
        if (fechaPartida == null) {
            fechaRegreso = null;
        }
    }

    public void cargarVuelos() {
        destinosVuelos.add("Medellín, Antioquia");
        destinosVuelos.add("Bogotá, Cundinamarca");
        destinosVuelos.add("Cali, Valle del Cauca");
        destinosVuelos.add("Ibagué,Tolima");
        destinosVuelos.add("Barranquilla, Atlantico");
        destinosVuelos.add("Cartagena, Bolivar");
        destinosVuelos.add("Santamarta, Bolivar");
    }

    public WrapUtil<String> getMsg() {
        return msg;
    }

    public void setMsg(WrapUtil<String> msg) {
        this.msg = msg;
    }

    public boolean isMostrarVueloMasHotel() {
        return mostrarVueloMasHotel;
    }

    public void setMostrarVueloMasHotel(boolean mostrarVueloMasHotel) {
        this.mostrarVueloMasHotel = mostrarVueloMasHotel;
    }

    public boolean isMostrarVueloMasHotelMasCarro() {
        return mostrarVueloMasHotelMasCarro;
    }

    public void setMostrarVueloMasHotelMasCarro(boolean mostrarVueloMasHotelMasCarro) {
        this.mostrarVueloMasHotelMasCarro = mostrarVueloMasHotelMasCarro;
    }

    public boolean isMostrarVueloMasCarro() {
        return mostrarVueloMasCarro;
    }

    public void setMostrarVueloMasCarro(boolean mostrarVueloMasCarro) {
        this.mostrarVueloMasCarro = mostrarVueloMasCarro;
    }

    public boolean isMostrarHotelMasCarro() {
        return mostrarHotelMasCarro;
    }

    public void setMostrarHotelMasCarro(boolean mostrarHotelMasCarro) {
        this.mostrarHotelMasCarro = mostrarHotelMasCarro;
    }

    public DtoAccionesGUI getDtoAccionesGUI() {
        return dtoAccionesGUI;
    }

    public void setDtoAccionesGUI(DtoAccionesGUI dtoAccionesGUI) {
        this.dtoAccionesGUI = dtoAccionesGUI;
    }

    public String getDonde() {
        return donde;
    }

    public void setDonde(String donde) {
        this.donde = donde;
    }

    public Date getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(Date fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public Date getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Date fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getMenores() {
        return menores;
    }

    public void setMenores(int menores) {
        this.menores = menores;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHoraInicioAlquiler() {
        return horaInicioAlquiler;
    }

    public void setHoraInicioAlquiler(String horaInicioAlquiler) {
        this.horaInicioAlquiler = horaInicioAlquiler;
    }

    public String getHoraFinAlquiler() {
        return horaFinAlquiler;
    }

    public void setHoraFinAlquiler(String horaFinAlquiler) {
        this.horaFinAlquiler = horaFinAlquiler;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getFechaFinAlquiler() {
        return fechaFinAlquiler;
    }

    public void setFechaFinAlquiler(Date fechaFinAlquiler) {
        this.fechaFinAlquiler = fechaFinAlquiler;
    }

    public ArrayList<String> getDestinosVuelos() {
        return destinosVuelos;
    }

    public void setDestinosVuelos(ArrayList<String> destinosVuelos) {
        this.destinosVuelos = destinosVuelos;
    }

}
