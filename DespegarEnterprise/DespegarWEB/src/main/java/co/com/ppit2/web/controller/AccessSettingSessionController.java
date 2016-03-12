package co.com.ppit2.web.controller;

import co.com.ppit2.web.controller.utils.Constants;
import co.com.ppit2.web.controller.utils.Sesion;
import co.com.ppit2.web.controller.utils.WrapUtil;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Controlador para las configuraciones de acceso
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
public class AccessSettingSessionController {

    @RequestMapping(value = "/settingSession.do", method = RequestMethod.GET)
    public String perfil_Inicio() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated()) {
            return "contenido/settingSession";
        }
        return "redirect:/home.do";
    }

    // Class attributes
    private WrapUtil<String> msg;

    @Init
    public void init() {
        this.load();
    }

    @SuppressWarnings({"unused", "null"})
    private void load() {
        msg = new WrapUtil<String>();

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication == null
                || (authentication instanceof AnonymousAuthenticationToken)
                || !authentication.isAuthenticated()) {
            Sesion.signout();
            return;
        }

        String currentUsername = authentication.getName();

        org.zkoss.zk.ui.Session sesion = Sessions.getCurrent();

        sesion.setAttribute(Constants.SESSION_USERNAME, currentUsername);

        Sesion.redirect("home.do");

    }

    public WrapUtil<String> getMsg() {
        return msg;
    }
}
