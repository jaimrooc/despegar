/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.web.controller.utils;

import co.com.ppit2.model.Convenios;
import co.com.ppit2.model.Personas;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Clase encargada de filtrar las listar de formularios
 * estaticos
 *
 * Historial:
 *
 * Fecha: 2015-06-12
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
public class FunnelFilter {
    
    public static List<Convenios> convenios(List<Convenios> listaOrignal, Convenios datosFiltro) {
        List<Convenios> listaFiltrada = new ArrayList<Convenios>(listaOrignal);

        String id = datosFiltro.getId() == null ? "" : datosFiltro.getId() + "";
        String tipoEntidad = datosFiltro.getTipoEntidad() == null ? "" : datosFiltro.getTipoEntidad().toLowerCase();
        String porcentaje = datosFiltro.getPorcentaje() == null ? "" : datosFiltro.getPorcentaje() + "";
        
        if (id == null && tipoEntidad == null && porcentaje == null) {
            return listaFiltrada;
        }

        for (Iterator<Convenios> i = listaFiltrada.iterator(); i.hasNext();) {
            Convenios tmp = i.next();

            if (!(tmp.getId() +  "").contains(id)) {
                i.remove();
            } else if (!(tmp.getPorcentaje() +  "").contains(porcentaje)) {
                i.remove();
            } else if (!(tmp.getTipoEntidad().toLowerCase() +  "").contains(tipoEntidad.toLowerCase())) {
                i.remove();
            }
        }
        return listaFiltrada;
    }
    
    public static List<Personas> personas(List<Personas> listaOrignal, Personas datosFiltro) {
        List<Personas> listaFiltrada = new ArrayList<Personas>(listaOrignal);

        String id = datosFiltro.getIdentificacion() == null ? "" : datosFiltro.getIdentificacion() + "";
        String nombre = datosFiltro.getFullName() == null ? "" : datosFiltro.getFullName().toLowerCase();
        
        if (id == null && nombre == null) {
            return listaFiltrada;
        }

        for (Iterator<Personas> i = listaFiltrada.iterator(); i.hasNext();) {
            Personas tmp = i.next();

            if (!(tmp.getIdentificacion() +  "").contains(id)) {
                i.remove();
            } else if (!(tmp.getFullName().toLowerCase() +  "").contains(nombre.toLowerCase())) {
                i.remove();
            }
        }
        return listaFiltrada;
    }
}
