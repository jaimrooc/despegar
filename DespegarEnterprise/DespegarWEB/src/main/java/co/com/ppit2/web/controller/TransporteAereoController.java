package co.com.ppit2.web.controller;

import co.com.ppit2.model.Personas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.com.ppit2.web.controller.utils.WrapUtil;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Controlador para la gestion el login
 *
 * Historial:
 *
 * Fecha: 2015-05-01
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
@Controller
public class TransporteAereoController {

    // Visualizacion de botones
    private boolean mostrarBtnNuevo;
    private boolean mostrarBtnGuardar;
    private boolean mostrarBtnEditar;
    private boolean mostrarBtnEliminar;

    // Objeto con atributos de la persona, y su backup
    private Personas persona;
    private Personas personaBackup;

    // Mesaje general
    private WrapUtil<String> msg;

    @RequestMapping(value = "/transAereo.do", method = RequestMethod.GET)
    public String ingresoPersonas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
            System.out.println("Ingreso en personas");
            return "transportes/transAereo";
        }
        System.out.println("NO INGRESO en personas");
        return "transportes/transAereo";
    }

    @Init
    public void metodoPrincipal() {
        defaultValues();
    }

    /**
     * Metodo encargado de resetear los valores actuales a valores por defecto
     */
    private void defaultValues() {
        msg = new WrapUtil<String>();
        persona = new Personas();
    }

    public WrapUtil<String> getMsg() {
        return msg;
    }

    public void setMsg(WrapUtil<String> msg) {
        this.msg = msg;
    }

}
