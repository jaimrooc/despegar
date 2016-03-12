package co.com.ppit2.web.controller.security;

import co.com.ppit2.web.controller.utils.Constants;
import java.util.Hashtable;

import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

//import co.com.cen.utils.Constants;
/**
 * @author	Jaime Andres Rojas Ocampo
 * @version 1.0 Controlador visualizacion de datos, segun perfil
 *
 * Historial:
 *
 * Fecha	Inic	Descripcion ------------	----
 * ----------------------------------------------- 2014-2-12	JARO	Codigo Inicial
 *
 */
@Controller
public class MenuSecurityController {

    private Hashtable<?, ?> hasMenuPermission;
    private boolean permissionClases;
    private boolean permissionContenidoDatos;
    private boolean permissionDisciplinas;

    private boolean userLogin;

    @Init
    public void init() {
        hasMenuPermission = (Hashtable<?, ?>) Sessions.getCurrent().getAttribute(Constants.SESSION_MENU_PERMISSION);
        if (hasMenuPermission == null) {
//			Sesion.signout();	TODO (Por evaluar)
            userLogin = false;
            anonymousUser();
        } else {
            userLogin = true;
            userLogin();
        }
    }

    private void userLogin() {
        permissionClases = formatearDisabled((String) hasMenuPermission.get(Constants.MODULO_CLASES));
        permissionContenidoDatos = formatearDisabled((String) hasMenuPermission.get(Constants.MODULO_CONTENIDO_DATOS));
        permissionDisciplinas = formatearDisabled((String) hasMenuPermission.get(Constants.MODULO_DISCIPLINAS));
    }

    private boolean formatearDisabled(String b) {
        boolean estado = Boolean.parseBoolean(b);
        return (estado == true) ? false : true;
    }

    private void anonymousUser() {
        permissionClases = false;
        permissionContenidoDatos = false;
        permissionDisciplinas = false;
    }

    public Hashtable<?, ?> getHasMenuPermission() {
        return hasMenuPermission;
    }

    public void setHasMenuPermission(Hashtable<?, ?> hasMenuPermission) {
        this.hasMenuPermission = hasMenuPermission;
    }

    public boolean isPermissionClases() {
        return permissionClases;
    }

    public void setPermissionClases(boolean permissionClases) {
        this.permissionClases = permissionClases;
    }

    public boolean isPermissionContenidoDatos() {
        return permissionContenidoDatos;
    }

    public void setPermissionContenidoDatos(boolean permissionContenidoDatos) {
        this.permissionContenidoDatos = permissionContenidoDatos;
    }

    public boolean isPermissionDisciplinas() {
        return permissionDisciplinas;
    }

    public void setPermissionDisciplinas(boolean permissionDisciplinas) {
        this.permissionDisciplinas = permissionDisciplinas;
    }

    public boolean isUserLogin() {
        return userLogin;
    }

    public void setUserLogin(boolean userLogin) {
        this.userLogin = userLogin;
    }
}
