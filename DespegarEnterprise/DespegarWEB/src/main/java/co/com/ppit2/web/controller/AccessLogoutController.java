package co.com.ppit2.web.controller;

import co.com.ppit2.web.controller.utils.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Controlador para las configuraciones de acceso del logout
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
public class AccessLogoutController {

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return "contenido/logout";
    }

    @Init
    public void init() {
        try {
            Sessions.getCurrent().removeAttribute(Constants.USER_SESSION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Executions.sendRedirect("/home.do");
    }
}
