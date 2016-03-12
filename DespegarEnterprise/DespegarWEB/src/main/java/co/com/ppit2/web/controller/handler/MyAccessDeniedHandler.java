/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.web.controller.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Manejador de acceso y negacion de la sesion
 *
 * Historial:
 *
 * Fecha: 2014-1-23 
 * 
 * Inic: JARO 
 * 
 * Descripcion: Codigo Inicial
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private String accessDeniedUrl;

    public MyAccessDeniedHandler() {
    }

    public MyAccessDeniedHandler(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }

    public void handle(HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        String uri = request.getRequestURI();
        String cPath = request.getContextPath();
        int longCPath = cPath.length();
        String pagSolicitada = uri.substring(longCPath);

        response.sendRedirect(accessDeniedUrl);
        request.getSession().setAttribute("pagSolicitada", pagSolicitada);

    }

    public String getAccessDeniedUrl() {
        return accessDeniedUrl;
    }

    public void setAccessDeniedUrl(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }
}
