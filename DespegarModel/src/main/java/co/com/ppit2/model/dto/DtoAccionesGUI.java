/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.model.dto;

/**
 *
 * @author jaro
 */
public class DtoAccionesGUI {

    // Referencias a botones
    private boolean nuevoFormulario;
    private boolean guardar;
    private boolean editar;
    private boolean eliminar;
    // Referencias a vistas en formulario
    private boolean lista;
    private boolean crear;
    
    private void reset() {
        nuevoFormulario = false;
        guardar = false;
        editar = false;
        eliminar = false;
        lista = false;
        crear = false;
    }
    
    
    public void accionListarFormulario() {
        reset();
        lista = true;
    }
    
    public void accionIngresarRegistro() {
        reset();
        nuevoFormulario = true;
    }

    public boolean isNuevoFormulario() {
        return nuevoFormulario;
    }

    public void setNuevoFormulario(boolean nuevoFormulario) {
        this.nuevoFormulario = nuevoFormulario;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public boolean isLista() {
        return lista;
    }

    public void setLista(boolean lista) {
        this.lista = lista;
    }

    public boolean isCrear() {
        return crear;
    }

    public void setCrear(boolean crear) {
        this.crear = crear;
    }

}
