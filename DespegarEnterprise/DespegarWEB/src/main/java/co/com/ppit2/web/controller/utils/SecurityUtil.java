package co.com.ppit2.web.controller.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Clase encargada de activar las transacciones de seguridad
 *
 * Historial:
 *
 * Fecha: 2015-05-01
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
public class SecurityUtil {

    /**
     * Return the current Authentication object.
     */
    public static User getUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            try {
                Object p = auth.getPrincipal();
                if (p instanceof User) {
                    return (User) p;
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return null;
    }

    /**
     * Return true if the authenticated principal is granted ALL of the roles
     * specified in authorities.
     *
     * @param authorities A comma separated list of roles which the user must
     * have been granted ALL.
     * @return true true if the authenticated principal is granted authorities
     * of ALL the specified roles.
     */
    public static boolean isAllGranted(String authorities) {
        if (null == authorities || "".equals(authorities)) {
            return false;
        }
        final ArrayList<String> granted = getPrincipalAuthorities();
        boolean isAllGranted = granted.containsAll(parseAuthorities(authorities));
        return isAllGranted;
    }

    /**
     * Return true if the authenticated principal is granted ANY of the roles
     * specified in authorities.
     *
     * @param authorities A comma separated list of roles which the user must
     * have been granted ANY.
     * @return true true if the authenticated principal is granted authorities
     * of ALL the specified roles.
     */
    public static boolean isAnyGranted(String authorities) {

        boolean isAnyGranted = false;
        if (null == authorities || "".equals(authorities)) {
            return false;
        }
        ArrayList<String> granted = getPrincipalAuthorities();
        granted = parseAuthorities(granted.get(0));

        final ArrayList<String> grantedRequered = parseAuthorities(authorities);

        for (String grantedReq : grantedRequered) {
            if (granted.contains(grantedReq)) {
                isAnyGranted = true;
                break;
            }
        }

        return isAnyGranted;
    }

    private static ArrayList<String> getPrincipalAuthorities() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        ArrayList<String> rolesActuales = new ArrayList<String>();
//        Collection<GrantedAuthority> granted = currentUser.getAuthorities(); // Original
        Collection<GrantedAuthority> granted = (Collection<GrantedAuthority>) currentUser.getAuthorities();
        for (GrantedAuthority grantedAuthority : granted) {
            rolesActuales.add(grantedAuthority.getAuthority());
        }
        return rolesActuales;
    }

    private static ArrayList<String> parseAuthorities(String authorizationsString) {
        final ArrayList<String> required = new ArrayList<String>();
        final String[] roles = authorizationsString.split(",");

        for (int i = 0; i < roles.length; i++) {
            String role = roles[i].trim();
            required.add(role);
        }
        return required;
    }

    /**
     * test if current user principal contains all given authorities
     *
     * @param authorities the roles will be checked
     */
    public static void assertAll(String... authorities) {

        if (null == authorities || authorities.length == 0) {
            return;
        }

        final ArrayList<GrantedAuthority> required = new ArrayList<GrantedAuthority>();
        for (String auth : authorities) {
            required.add(new SimpleGrantedAuthority(auth));
        }

        ArrayList<String> granted = getPrincipalAuthorities();
        if (!granted.containsAll(required)) {
            Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
            throw new AccessDeniedException("The current principal doesn't has enough authority. Authentication: "
                    + (currentUser == null ? "" : currentUser.getName()));
        }
    }
}
