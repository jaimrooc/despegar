package co.com.ppit2.web.controller.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

//import co.com.cen.model.enums.Enum_i18n;
//import co.com.cen.model.perfil.User;
//import co.com.cen.model.perfil.UserSession;
import co.com.ppit2.model.enums.Enum_i18n;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 * 
 * Manejador de la sesion del usuario
 *
 * Historial:
 *
 * Fecha: 2015-05-01
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
public class Sesion {

    /**
     * *
     * Para cambiar el Locale (i18n) al entorno de visualizacion.
     *
     * @param localeValue determinacion del idioma del sitio web. <br/>(i.e.
     * 'en','es').
     */
    public static void setLocale(String localeValue) {
        Locale prefer_locale = new Locale(localeValue);
        org.zkoss.zk.ui.Session session = Sessions.getCurrent();
        session.setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, prefer_locale);
        try {
            Clients.reloadMessages(prefer_locale);
            org.zkoss.util.Locales.setThreadLocal(prefer_locale);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * *
     * Get local language from session, by default the language code is
     * <code>(es) Spanish</code>
     *
     * @return local language.
     */
    public static String getLanguage() {
        String lang = org.zkoss.util.Locales.getThreadLocal().getLanguage();
        if (lang == null || lang.isEmpty()) {
            return Enum_i18n.ES.toString();
        } else {
            return lang;
        }
    }

    /**
     * *
     * Redirecciona a la direccion especificada en el parametro <code>uri</code>
     *
     * @param uri donde se ha de redireccionar la pagina.
     */
    public static void redirect(String uri) {
        Executions.getCurrent().sendRedirect("/" + uri);
    }

    /**
     * *
     * Redirecciona a la direccion especificada en el parametro <code>uri</code>
     *
     * @param uri donde se ha de redireccionar la pagina.
     * @param contextPath
     * @param response who redirect to page indicated for <code>uri</code>
     */
    public static void redirect(String uri, String contextPath, HttpServletResponse response) {
        try {
            response.sendRedirect(contextPath + "/" + uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * *
     * Redirecciona el sitio al login lo que elimina la sesion actual.
     */
    public static void signout() {
        redirect("j_spring_security_logout");
    }

    /**
     * *
     * Refresca la pagina por la url actual.
     */
    public static void refresh() {
        redirect("");
    }

    /**
     * *
     * Invalida la sesion actual
     */
    public static void removeSessionAttributes() {
        SecurityContextHolder.clearContext();
        Map<String, Object> map = Sessions.getCurrent().getAttributes();
        for (String ob : map.keySet()) {
            Sessions.getCurrent().removeAttribute(ob);
        }
    }

    /**
     * *
     * Activa la sesion actual
     */
    public static void nuevaSesion() {
        Sessions.getCurrent(true);
    }

    public static void goForgotPassword() {
        redirect("forgotpwd.do");
    }

    public static void retrievePassword() {
        redirect("forgotpwd.do?motivo=passwordExpired");
    }

    public static void redirectForPasswordExpiration() {
        removeSessionAttributes();
        SecurityContextHolder.createEmptyContext();
        redirect("forgotpwd.do?motivo=passwordExpired");
    }

//	General Functions
    public static boolean msgAssert(String msg, String title) {
        int r = Messagebox.show(msg, title, Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION);
        return (r == Messagebox.OK);
    }//End-Function(msgAssert)

    public static void showMessage(String msg) {
        Messagebox.show(msg, "", Messagebox.OK, Messagebox.INFORMATION);
    }//End-Function(msgAssert)

//    public static UserSession getNewUserSession(User username, String userState, Integer attempt_number) {
//        UserSession us = new UserSession();
//        us.setAttempt_number(attempt_number);
//        us.setIpaddress(Executions.getCurrent().getRemoteAddr());
//        us.setUser_agent(Executions.getCurrent().getUserAgent());
//        us.setUsername(username);
//        us.setUserState(userState);
//        return us;
//    }

    @SuppressWarnings("rawtypes")
    public static void cleaningSessionObjects(Class ControllerName) {

        System.out.println("---------------------------> metodo: cleaningSessionObjects() <---------------------------");
//		if ( TreeProfileSecurityRolController.class.equals(ControllerName) ) {
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_PROFILE_SECURITY_ROL);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_VIEW_EDITAR_ROL);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_PROFILE_SELECT);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_PROFILE_PERMISSION);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_INSERT_PROFILE_SECURITY_ROL);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_UPDATE_PROFILE_SECURITY_ROL);
//		}
//		if ( ProfileSecurityController.class.equals(ControllerName) ) {
//			
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_ASOCIADO);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_VIEW_EDITAR_ASOCIADO);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_PROFILE_SELECT_ASOCIADO);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_PROFILE_PERMISSION_ASOCIADO);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_CUSTOMER_CHECK_ALL);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_PROFIL_CHECK_ALL);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_PROFILE_SELECT_CUSOMER);
//			 Sessions.getCurrent().removeAttribute(Constants.SESSION_CUSTOMER_SECURITY);
//		}
//		if ( ClienteController.class.equals(ControllerName) ) {
//			 Sessions.getCurrent().removeAttribute("CLIENTE");
//		}

    }

    public static boolean isAutenticated(Authentication authentication) {
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isAutenticated(authentication);
    }

    public static String getUserAuthenticated() {
        if (!isAuthenticated()) {
            return "";
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name;
    }

    /**
     * Logs mistake caused somewhere in the system.
     *
     * @param clase	where is caused mistake
     * @param msg	Message of mistake
     * @param throwable	Object thrown in the system
     */
    public static void logMistake(Class<?> clase, Object msg, Throwable throwable) {
        Logger logger = Logger.getLogger(clase);
        logger.error(msg, throwable);
    }

    /**
     * Logs mistake caused somewhere in the system.
     *
     * @param clase	where is caused mistake
     * @param msg	Message of mistake
     */
    public static void logMistake(Class<?> clase, Object msg) {
        Logger logger = Logger.getLogger(clase);
        logger.error(msg);
    }

    /**
     * Logs mistake caused somewhere in the system.
     *
     * @param clase	where is caused mistake
     * @param throwable	Object thrown in the system
     */
    public static void logMistake(Class<?> clase, Throwable throwable) {
        Logger logger = Logger.getLogger(clase);
        logger.error(throwable.getMessage(), throwable);
    }

    /**
     * Logs info caused somewhere in the system.
     *
     * @param clase	where is caused mistake
     * @param msg	Message of info
     * @param throwable	Object thrown in the system
     */
    public static void logInfo(Class<?> clase, Object msg, Throwable throwable) {
        Logger logger = Logger.getLogger(clase);
        logger.info(msg, throwable);
    }

    /**
     * Logs info caused somewhere in the system.
     *
     * @param clase	where is caused mistake
     * @param msg	Message of info
     */
    public static void logInfo(Class<?> clase, Object msg) {
        Logger logger = Logger.getLogger(clase);
        logger.info(msg);
    }

    /**
     * Logs info caused somewhere in the system.
     *
     * @param clase	where is caused mistake
     * @param throwable	Object thrown in the system
     */
    public static void logInfo(Class<?> clase, Throwable throwable) {
        Logger logger = Logger.getLogger(clase);
        logger.info(throwable.getMessage(), throwable);
    }
}
