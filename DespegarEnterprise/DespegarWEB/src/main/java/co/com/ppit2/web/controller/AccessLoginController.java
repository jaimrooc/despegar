package co.com.ppit2.web.controller;

import co.com.ppit2.despegarejb.sessionsBeans.PersonasSessionBean;
import co.com.ppit2.model.Personas;
import co.com.ppit2.web.controller.utils.Constants;
import co.com.ppit2.web.controller.utils.WrapUtil;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Controlador para las configuraciones de acceso del login
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
public class AccessLoginController {

    private WrapUtil<String> msg = new WrapUtil<String>();
    private Personas persona = new Personas();

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String perfil_Inicio() {
        return "contenido/login";
    }

    @RequestMapping(value = "/loginfailed.do", method = RequestMethod.GET)
    public String loginerror() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated()) {
            return "redirect:/home.do";
        }
        return "login";
    }

    @Command
    @NotifyChange("*")
    public void login() {
        msg.clear();

        Sessions.getCurrent().removeAttribute(Constants.USER_SESSION);

        // Evalua si los campos se encuentran vacios
        if (persona == null || persona.getIdentificacion() == null || persona.getIdentificacion().isEmpty()
                || persona.getPassword() == null || persona.getPassword().isEmpty()) {
            msg.verMensaje(Labels.getLabel("login.error.missing.data"), null);
            return;
        }

        // Evalua existencia del usuario
        Personas personaLogin = null;
        try {
            personaLogin = new PersonasSessionBean().getPersonaLogin(this.persona);
        } catch (Exception ex) {
        }
        if (personaLogin == null || personaLogin.getIdentificacion() == null
                || personaLogin.getIdentificacion().trim().isEmpty()) {
            msg.verMensaje(Labels.getLabel("login.error.user.not.found"), null);
            return;
        }

        // Usuario correcto, se anexa a la session
        Sessions.getCurrent().setAttribute(Constants.USER_SESSION, personaLogin);
        Executions.sendRedirect("/home.do");
    }

    public WrapUtil<String> getMsg() {
        return msg;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
}
